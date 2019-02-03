import java.util.HashSet;
import java.util.Scanner;

class Reversi {
    static void game(Board b){
        Scanner scan = new Scanner(System.in);
        Point move = new Point(-1, -1);
        Players players = new Players("Black", "White");
        System.out.println(players.getPlayer() + " Moves first");

        boolean skip;
        String input;

        while(true){
            skip = false;

            HashSet<Point> placeableLocations = b.getPlaceableLocations(
                    players.getPlayer().charAt(0),
                    players.getOpponent().charAt(0));

            b.showPlaceableLocations(placeableLocations,
                    players.getPlayer().charAt(0),
                    players.getOpponent().charAt(0));
            if (checkScore(
                    b,
                    b.getPlaceableLocations('B', 'W'),
                    b.getPlaceableLocations('W', 'B')))
                break;

            if(placeableLocations.isEmpty()){
                System.out.println(players.getPlayer() + " needs to skip... Passing to " + players.getOpponent());
                skip = true;
            }

            if(!skip){
                System.out.println("Place move (" + players.getPlayer() + "): ");
                input = scan.next();
                move.setY(b.coordinateX(input.charAt(0)));
                move.setX(Integer.parseInt(input.charAt(1)+"")-1);

                while(!placeableLocations.contains(move)){
                    System.out.println("Invalid move!\n\nPlace move (" + players.getPlayer() + "): ");
                    input = scan.next();
                    move.setY(b.coordinateX(input.charAt(0)));
                    move.setX(Integer.parseInt((input.charAt(1)+""))-1);
                }
                b.placeMove(move, players.getPlayer().charAt(0), players.getOpponent().charAt(0));
                b.updateScores();
                System.out.println("\n" + players.getPlayer() + ": "+b.getBScore()+" " + players.getOpponent() + ": "+b.getWScore());
            }

            players.changeRoles(players.getPlayer(), players.getOpponent());

        }
    }
    private static boolean checkScore(Board b, HashSet<Point> blackPlaceableLocations, HashSet<Point> whitePlaceableLocations) {
        int result = b.gameResult(whitePlaceableLocations, blackPlaceableLocations);

        if(result == 0){System.out.println("It is a draw.");
            return true;
        }
        else if(result==1){
            System.out.println("White wins: "+b.getWScore()+":"+b.getBScore());
            return true;
        }
        else if(result==-1){
            System.out.println("Black wins: "+b.getBScore()+":"+b.getWScore());
            return true;
        }
        return false;
    }
}
import java.util.HashSet;
import java.util.Scanner;

class Reversi {
    static void game(Board b){
        Scanner scan = new Scanner(System.in);
        Point move = new Point(-1, -1);
        Turn turn = new Turn("Black", "White");
        System.out.println(turn.getActivePlayer() + " Moves first");

        boolean skip;
        String input;

        while(true){
            skip = false;

            HashSet<Point> placeableLocations = b.getPlaceableLocations(
                    turn.getActivePlayer().charAt(0),
                    turn.getInactivePlayer().charAt(0));

            b.showPlaceableLocations(placeableLocations,
                    turn.getActivePlayer().charAt(0),
                    turn.getInactivePlayer().charAt(0));
            if (checkScore(
                    b,
                    b.getPlaceableLocations('B', 'W'),
                    b.getPlaceableLocations('W', 'B')))
                break;

            if(placeableLocations.isEmpty()){
                System.out.println(turn.getActivePlayer() + " needs to skip... Passing to " + turn.getInactivePlayer());
                skip = true;
            }

            if(!skip){
                System.out.println("Place move (" + turn.getActivePlayer() + "): ");
                input = scan.next();
                move.setY(b.coordinateX(input.charAt(0)));
                move.setX(Integer.parseInt(input.charAt(1)+"")-1);

                while(!placeableLocations.contains(move)){
                    System.out.println("Invalid move!\n\nPlace move (" + turn.getActivePlayer() + "): ");
                    input = scan.next();
                    move.setY(b.coordinateX(input.charAt(0)));
                    move.setX(Integer.parseInt((input.charAt(1)+""))-1);
                }
                b.placeMove(move, turn.getActivePlayer().charAt(0), turn.getInactivePlayer().charAt(0));
                b.updateScores();
                System.out.println("\n" + turn.getActivePlayer() + ": "+b.getBScore()+" " + turn.getInactivePlayer() + ": "+b.getWScore());
            }

            turn.changeRoles(turn.getActivePlayer(), turn.getInactivePlayer());

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
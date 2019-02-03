import java.util.HashSet;
import java.util.Scanner;

class Reversi {
    static void game(Board b){
        Scanner scan = new Scanner(System.in);
        Board.Point move = b.new Point(-1, -1);
        System.out.println("Black Moves first");

        boolean skip;
        String input;

        while(true){
            skip = false;

            HashSet<Board.Point> blackPlaceableLocations = b.getPlaceableLocations('B', 'W');
            HashSet<Board.Point> whitePlaceableLocations = b.getPlaceableLocations('W', 'B');

            b.showPlaceableLocations(blackPlaceableLocations, 'B', 'W');
            if (checkScore(b, blackPlaceableLocations, whitePlaceableLocations)) break;

            if(blackPlaceableLocations.isEmpty()){
                System.out.println("Black needs to skip... Passing to white");
                skip = true;
            }

            if(!skip){
                System.out.println("Place move (Black): ");
                input = scan.next();
                move.y = b.coordinateX(input.charAt(0));
                move.x = (Integer.parseInt(input.charAt(1)+"")-1);

                while(!blackPlaceableLocations.contains(move)){
                    System.out.println("Invalid move!\n\nPlace move (Black): ");
                    input = scan.next();
                    move.y = b.coordinateX(input.charAt(0));
                    move.x = Integer.parseInt((input.charAt(1)+""))-1;
                }
                b.placeMove(move, 'B', 'W');
                b.updateScores();
                System.out.println("\nBlack: "+b.BScore+" White: "+b.WScore);
            }
            skip = false;

            whitePlaceableLocations = b.getPlaceableLocations('W', 'B');
            blackPlaceableLocations = b.getPlaceableLocations('B', 'W');

            b.showPlaceableLocations(whitePlaceableLocations, 'W', 'B');
            if (checkScore(b, blackPlaceableLocations, whitePlaceableLocations)) break;

            if(whitePlaceableLocations.isEmpty()){
                System.out.println("White needs to skip... Passing to Black");
                skip = true;
            }

            if(!skip){
                System.out.println("Place move (White): ");
                input = scan.next();
                move.y = b.coordinateX(input.charAt(0));
                move.x = (Integer.parseInt(input.charAt(1)+"")-1);

                while(!whitePlaceableLocations.contains(move)){
                    System.out.println("Invalid move!\n\nPlace move (White): ");
                    input = scan.next();
                    move.y = b.coordinateX(input.charAt(0));
                    move.x = (Integer.parseInt(input.charAt(1)+"")-1);
                }
                b.placeMove(move, 'W', 'B');
                b.updateScores();
                System.out.println("\nWhite: "+b.WScore+" Black: "+b.BScore);
            }
        }
    }

    private static boolean checkScore(Board b, HashSet<Board.Point> blackPlaceableLocations, HashSet<Board.Point> whitePlaceableLocations) {
        int result = b.gameResult(whitePlaceableLocations, blackPlaceableLocations);

        if(result == 0){System.out.println("It is a draw.");
            return true;
        }
        else if(result==1){
            System.out.println("White wins: "+b.WScore+":"+b.BScore);
            return true;
        }
        else if(result==-1){
            System.out.println("Black wins: "+b.BScore+":"+b.WScore);
            return true;
        }
        return false;
    }
}
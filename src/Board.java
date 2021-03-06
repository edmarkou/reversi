import java.util.HashSet;
import java.util.Set;

class Board {

    private char[][] board;

    public int getWScore() {
        return WScore;
    }

    public int getBScore() {
        return BScore;
    }

    private int WScore, BScore;
    private int remaining;
    private final char[] boardX = new char[]{'A','B','C','D','E','F','G','H'};


    Board(){
        board = new char[][]{
                {'_','_','_','_','_','_','_','_',},
                {'_','_','_','_','_','_','_','_',},
                {'_','_','_','_','_','_','_','_',},
                {'_','_','_','W','B','_','_','_',},
                {'_','_','_','B','W','_','_','_',},
                {'_','_','_','_','_','_','_','_',},
                {'_','_','_','_','_','_','_','_',},
                {'_','_','_','_','_','_','_','_',},
        };
    }

    private void findPlaceableLocations(char player, char opponent, HashSet<Point> placeablePositions) {
        for(int i=0;i<8;++i) {
            for(int j=0;j<8;++j) {
                if(board[i][j] == opponent) {
                    int I = i, J = j;
                    if(i-1>=0 && j-1>=0 && board[i-1][j-1] == '_') {
                        i++;
                        j++;
                        while(i<7 && j<7 && board[i][j] == opponent) {
                            i++;
                            j++;
                        }
                        if(i<=7 && j<=7 && board[i][j] == player)
                            placeablePositions.add(new Point(I-1, J-1));
                    }
                    i=I;
                    j=J;
                    if(i-1>=0 && board[i-1][j] == '_'){
                        i++;
                        while(i<7 && board[i][j] == opponent)
                            i++;
                        if(i<=7 && board[i][j] == player)
                            placeablePositions.add(new Point(I-1, J));
                    }
                    i=I;
                    if(i-1>=0 && j+1<=7 && board[i-1][j+1] == '_'){
                        i++;
                        j--;
                        while(i<7 && j>0 && board[i][j] == opponent) {
                            i++;
                            j--;
                        }
                        if(i<=7 && j>=0 && board[i][j] == player)
                            placeablePositions.add(new Point(I-1, J+1));
                    }
                    i=I;
                    j=J;
                    if(j-1>=0 && board[i][j-1] == '_'){
                        j++;
                        while(j<7 && board[i][j] == opponent)
                            j++;
                        if(j<=7 && board[i][j] == player)
                            placeablePositions.add(new Point(I, J-1));
                    }
                    j=J;
                    if(j+1<=7 && board[i][j+1] == '_'){
                        j--;
                        while(j>0 && board[i][j] == opponent)
                            j--;
                        if(j>=0 && board[i][j] == player)
                            placeablePositions.add(new Point(I, J+1));
                    }
                    j=J;
                    if(i+1<=7 && j-1>=0 && board[i+1][j-1] == '_'){
                        i--;
                        j++;
                        while(i>0 && j<7 && board[i][j] == opponent) {
                            i--;
                            j++;
                        }
                        if(i>=0 && j<=7 && board[i][j] == player)
                            placeablePositions.add(new Point(I+1, J-1));
                    }
                    i=I;
                    j=J;
                    if(i+1 <= 7 && board[i+1][j] == '_'){
                        i=i-1;
                        while(i>0 && board[i][j] == opponent)
                            i--;
                        if(i>=0 && board[i][j] == player)
                            placeablePositions.add(new Point(I+1, J));
                    }
                    i=I;
                    if(i+1 <= 7 && j+1 <=7 && board[i+1][j+1] == '_') {
                        i--;
                        j--;
                        while(i>0 && j>0 && board[i][j] == opponent) {
                            i--;
                            j--;
                        }
                        if(i>=0 && j>=0 && board[i][j] == player)
                            placeablePositions.add(new Point(I+1, J+1));
                    }
                    i=I;
                    j=J;
                }
            }
        }
    }

   private void displayBoard(Board b){
        System.out.print("\n  ");
        for(int i=0;i<8;++i)
            System.out.print(boardX[i]+" ");
        System.out.println();
        for(int i=0;i<8;++i){
            System.out.print((i+1)+" ");
            for(int j=0;j<8;++j)
                System.out.print(b.board[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }

    int gameResult(Set<Point> whitePlaceableLocations, Set<Point> blackPlaceableLocations){
        updateScores();
        if(remaining == 0)
            return Integer.compare(WScore, BScore);
        if(WScore==0 || BScore == 0){
            if(WScore > 0) return 1;
            else if(BScore > 0) return -1;
        }
        if(whitePlaceableLocations.isEmpty() && blackPlaceableLocations.isEmpty())
            return Integer.compare(WScore, BScore);
        return -2;
    }

    HashSet<Point> getPlaceableLocations(char player, char opponent){
        HashSet<Point> placeablePositions = new HashSet<>();
        findPlaceableLocations(player, opponent, placeablePositions);
        return placeablePositions;
    }

    void showPlaceableLocations(HashSet<Point> locations, char player, char opponent){
        for(Point p:locations)
            board[p.getX()][p.getY()]='*';
        displayBoard(this);
        for(Point p:locations)
            board[p.getX()][p.getY()]='_';
    }

    void placeMove(Point p, char player, char opponent){
        int i = p.getX(), j = p.getY();
        board[i][j] = player;
        int I = i, J = j;

        if(i-1>=0 && j-1>=0 && board[i-1][j-1] == opponent){
            i--;
            j--;
            while(i>0 && j>0 && board[i][j] == opponent) {
                i--;
                j--;
            }
            if(i>=0 && j>=0 && board[i][j] == player) {
                while(i!=I-1 && j!=J-1)
                    board[++i][++j]=player;
            }
        }
        i=I;
        j=J;
        if(i-1>=0 && board[i-1][j] == opponent){
            i--;
            while(i>0 && board[i][j] == opponent)
                i--;
            if(i>=0 && board[i][j] == player) {
                while(i!=I-1)
                    board[++i][j]=player;
            }
        }
        i=I;
        if(i-1>=0 && j+1<=7 && board[i-1][j+1] == opponent){
            i--;
            j++;
            while(i>0 && j<7 && board[i][j] == opponent){
                i--;
                j++;
            }
            if(i>=0 && j<=7 && board[i][j] == player) {
                while(i!=I-1 && j!=J+1)
                    board[++i][--j] = player;
            }
        }
        i=I;
        j=J;
        if(j-1>=0 && board[i][j-1] == opponent){
            j--;
            while(j>0 && board[i][j] == opponent)
                j--;
            if(j>=0 && board[i][j] == player) {
                while(j!=J-1)
                    board[i][++j] = player;
            }
        }
        j=J;
        if(j+1<=7 && board[i][j+1] == opponent){
            j=j+1;
            while(j<7 && board[i][j] == opponent)
                j++;
            if(j<=7 && board[i][j] == player) {
                while(j!=J+1)
                    board[i][--j] = player;
            }
        }
        j=J;
        if(i+1<=7 && j-1>=0 && board[i+1][j-1] == opponent){
            i++;
            j--;
            while(i<7 && j>0 && board[i][j] == opponent){
                i++;
                j--;
            }
            if(i<=7 && j>=0 && board[i][j] == player) {
                while(i!=I+1 && j!=J-1)
                    board[--i][++j] = player;
            }
        }
        i=I;
        j=J;
        if(i+1 <= 7 && board[i+1][j] == opponent){
            i++;
            while(i<7 && board[i][j] == opponent)
                i++;
            if(i<=7 && board[i][j] == player) {
                while(i!=I+1)
                    board[--i][j] = player;
            }
        }
        i=I;

        if(i+1 <= 7 && j+1 <=7 && board[i+1][j+1] == opponent){
            i++;
            j++;
            while(i<7 && j<7 && board[i][j] == opponent){
                i++;
                j++;
            }
            if(i<=7 && j<=7 && board[i][j] == player)
                while(i!=I+1 && j!=J+1)
                    board[--i][--j] = player;
        }
    }

    void updateScores(){
        WScore = 0;
        BScore = 0;
        remaining = 0;
        for(int i=0;i<8;++i) {
            for(int j=0;j<8;++j) {
                if(board[i][j]=='W')
                    WScore++;
                else if(board[i][j]=='B')
                    BScore++;
                else remaining++;
            }
        }
    }

    int coordinateX(char x){
        for(int i=0;i<8;++i)
            if(boardX[i]==Character.toLowerCase(x)||boardX[i]==Character.toUpperCase(x)) return i;
        return -1; // Illegal move received
    }
}
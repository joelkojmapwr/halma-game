package studia.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardBuilder {
    public Board board;
    public List<Player> players = new ArrayList<Player>();
    /**
     * 
     * @param triangleSize - length of the triangle side (default 4)
     */
    public BoardBuilder(int triangleSize, int playerNumber) {
        // wiersze są ułożone na przemian, więc zastosujemy tablicę szerokości 2*szerokość -1 
        // domyślnie 25
        int length = 2*(3*triangleSize +1) - 1;
        // domyślnie 17
        int height = 4*triangleSize +1;
        this.board = new Board();
        board.setLength(length);
        board.setHeight(height);
        board.setTriangleSize(triangleSize);
        board.initPoints();
        
        

        this.initializePoints();
        this.initCornerPoints();
        this.initializePlayers(playerNumber);
        board.generateNeighbours1();
        spawnPawns();
    }

    private void initializePoints() {
        // punkt 0,0 jest w lewym dolnym rogu
        // initialize 1 triangle - 
        int newX, newY;
        for (int i = 0; i < board.height - board.triangleSize; i++) {
            for (int j = i%2; j <= i; j+=2) {
                // w parzystym wierszu pola są na parzystych pozycjach
                // w nieparzystym wierszu pola są na nieparzystych pozycjach
                newX = board.length/2 + j;
                newY = i;
                board.setPoint(new Point(new Pair(newX, newY)), newX, newY);
                newX = board.length/2 - j;
                newY = i;
                board.setPoint(new Point(new Pair(newX, newY)), newX, newY);
            }
        }
        // initialize 2 triangle
        for (int i = board.height - 1; i>=board.triangleSize; i--) {
            // zaczynamy od tego na środku
            for (int j = i%2; j <= (board.height -1 -i); j+=2) {
                // w parzystym wierszu pola są na parzystych pozycjach
                // w nieparzystym wierszu pola są na nieparzystych pozycjach
                newX = board.length/2 + j;
                newY = i;
                board.setPoint(new Point(new Pair(newX, newY)), newX, newY);
                newX = board.length/2 - j;
                newY = i;
                board.setPoint(new Point(new Pair(newX, newY)), newX, newY);
            }
        }
    }

    private void initCornerPoints(){
        Point topPoint = board.points[board.length/2][0];
        Point bottomPoint = board.points[board.length/2][board.height - 1];
        Point upperLeftPoint = board.points[0][board.triangleSize];
        Point lowerLeftPoint = board.points[0][board.height - 1 - board.triangleSize];
        Point upperRightPoint = board.points[board.length - 1][board.triangleSize];
        Point lowerRightPoint = board.points[board.length - 1][board.height - 1 - board.triangleSize];

        board.addCornerPoint(topPoint);
        board.addCornerPoint(upperRightPoint);
        board.addCornerPoint(lowerRightPoint);
        board.addCornerPoint(bottomPoint);
        board.addCornerPoint(lowerLeftPoint);
        board.addCornerPoint(upperLeftPoint);
    }

    private void initializePlayers(int playerNumber) {
        int startColor = 16;
        for (int i = 0; i<playerNumber; i++) {
            Player newPlayer = new Player(startColor);
            this.players.add(newPlayer);
            startColor +=3;
        }
        switch (playerNumber) {
            case 2:
                players.get(0).setStartCorner(board.cornerPoints.get(0).pos);
                players.get(1).setStartCorner(board.cornerPoints.get(3).pos);
                break;
            case 3:
                for (int i =0; i<3; i++){
                    players.get(i).setStartCorner(board.cornerPoints.get(i*2).pos);
                }
                break;
            case 4:
                players.get(0).setStartCorner(board.cornerPoints.get(0).pos);
                players.get(1).setStartCorner(board.cornerPoints.get(1).pos);
                players.get(2).setStartCorner(board.cornerPoints.get(3).pos);
                players.get(3).setStartCorner(board.cornerPoints.get(4).pos);
                break;
            case 6:
                for (int i =0; i<6; i++){
                    players.get(i).setStartCorner(board.cornerPoints.get(i).pos);
                }
                break;
            default:
                break;
        }
    }

    public abstract void spawnPawns();

    // isWon(Player)

    // possible moves

    // calculate distance between pawns
}

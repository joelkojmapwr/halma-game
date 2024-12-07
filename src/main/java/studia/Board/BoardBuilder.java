package studia.Board;

import java.util.ArrayList;
import java.util.List;

import studia.Utils.Pair;
import studia.Utils.Point;

public class BoardBuilder {
    private Board board;
    private List<Player> players = new ArrayList<Player>();
    private int playerNumber;
    private int pawnsPerPlayer;
    /**
     * 
     * @param triangleSize - length of the triangle side (default 4)
     */
    public BoardBuilder(int triangleSize, int playerNumber, int pawnsPerPlayer) {

        this.playerNumber = playerNumber;
        this.pawnsPerPlayer = pawnsPerPlayer;
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
        
    }

    public Board getBoard() {
        return board;
    }

    public void build(){
        this.initPoints();
        this.initValidPointsMap();
        this.initCornerPoints();
        this.initPlayers(playerNumber);
        board.generateNeighbours1();
        board.generateNeighbours2();
        spawnPawns();
    }

    private void initPoints() {
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

    private void initPlayers(int playerNumber) {
        int startColor = 1;
        for (int i = 0; i<playerNumber; i++) {
            Player newPlayer = new Player(startColor);
            this.players.add(newPlayer);
            // @TODO - handle colours
            startColor +=1;
        }
        switch (playerNumber) {
            case 2:
                players.get(0).setStartCorner(board.cornerPoints.get(0));
                players.get(0).setFinishCorner(board.cornerPoints.get(3));
                players.get(1).setStartCorner(board.cornerPoints.get(3));
                players.get(1).setFinishCorner(board.cornerPoints.get(0));
                break;
            case 3:
                for (int i =0; i<3; i++){
                    players.get(i).setStartCorner(board.cornerPoints.get(i*2));
                    players.get(i).setFinishCorner(board.cornerPoints.get((i*2+3)%6));
                }
                break;
            case 4:
                players.get(0).setStartCorner(board.cornerPoints.get(0));
                players.get(0).setFinishCorner(board.cornerPoints.get(3));
                players.get(1).setStartCorner(board.cornerPoints.get(1));
                players.get(1).setFinishCorner(board.cornerPoints.get(4));
                players.get(2).setStartCorner(board.cornerPoints.get(3));
                players.get(2).setFinishCorner(board.cornerPoints.get(0));
                players.get(3).setStartCorner(board.cornerPoints.get(4));
                players.get(3).setFinishCorner(board.cornerPoints.get(1));
                break;
            case 6:
                for (int i =0; i<6; i++){
                    players.get(i).setStartCorner(board.cornerPoints.get(i));
                    players.get(i).setFinishCorner(board.cornerPoints.get((i+3)%6));
                }
                break;
            default:
                break;
        }
        board.setPlayers(players);
    }

    public void spawnPawns(){
        PawnsSpawner pawnsSpawner = new PawnsSpawner(pawnsPerPlayer);
        pawnsSpawner.spawn(players);
    }

    public void initValidPointsMap() {
        int countPoints = 0;
        for (int i=0; i<board.height; i++) {
            for (int j=0; j<board.length; j++) {
                Point point = board.points[j][i];
                if (point != null) {
                    board.validPointsMap.put(countPoints, point);
                    countPoints++;
                }
            }
        }
        board.validPointsNumber = countPoints;
    }

    // isWon(Player)

    // possible moves

    // calculate distance between pawns
}

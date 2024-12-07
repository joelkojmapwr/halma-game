package studia.Board;

import java.util.ArrayList;
import java.util.List;

import studia.Utils.Pair;
import studia.Utils.Player;
import studia.Utils.Point;
import studia.PawnsSpawner.StandardPawnsSpawner;

public class BoardBuilder {
    private Board board;
    private Player[] players;
    private int playerNumber;
    private int pawnsPerPlayer;
    /**
     * 
     * @param triangleSize - length of the triangle side (default 4)
     */
    public BoardBuilder(int triangleSize, Player[] players, int pawnsPerPlayer) {
        this.players = players;
        this.playerNumber = players.length;
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
        this.initFinishPoints();
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
        /*int startColor = 1;
        for (int i = 0; i<playerNumber; i++) {
            Player newPlayer = new Player(startColor);
            this.players.add(newPlayer);
            // @TODO - handle colours
            startColor +=1;
        }*/
        switch (playerNumber) {
            case 2:
                players[0].setStartCorner(board.cornerPoints.get(0));
                players[0].setFinishCorner(board.cornerPoints.get(3));
                players[1].setStartCorner(board.cornerPoints.get(3));
                players[1].setFinishCorner(board.cornerPoints.get(0));
                break;
            case 3:
                for (int i =0; i<3; i++){
                    players[i].setStartCorner(board.cornerPoints.get(i*2));
                    players[i].setFinishCorner(board.cornerPoints.get((i*2+3)%6));
                }
                break;
            case 4:
                players[0].setStartCorner(board.cornerPoints.get(0));
                players[0].setFinishCorner(board.cornerPoints.get(3));
                players[1].setStartCorner(board.cornerPoints.get(1));
                players[1].setFinishCorner(board.cornerPoints.get(4));
                players[2].setStartCorner(board.cornerPoints.get(3));
                players[2].setFinishCorner(board.cornerPoints.get(0));
                players[3].setStartCorner(board.cornerPoints.get(4));
                players[3].setFinishCorner(board.cornerPoints.get(1));
                break;
            case 6:
                for (int i =0; i<6; i++){
                    players[i].setStartCorner(board.cornerPoints.get(i));
                    players[i].setFinishCorner(board.cornerPoints.get((i+3)%6));
                }
                break;
            default:
                break;
        }
        board.setPlayers(players);
    }

    private void initFinishPoints() {
        for (Player player : players) {
            player.finishPoints.add(player.finishCorner);
            for (Point neighbour2 : player.finishCorner.neighbours2) {
                player.finishPoints.add(neighbour2);
                for (Point neighbour1 : neighbour2.neighbours1) {
                    if (player.finishPoints.contains(neighbour1) == false) {
                        player.finishPoints.add(neighbour1);
                    } 
                }
            }
        }
    }

    private void spawnPawns(){
        StandardPawnsSpawner pawnsSpawner = new StandardPawnsSpawner(pawnsPerPlayer);
        pawnsSpawner.spawn(players);
    }

    private void initValidPointsMap() {
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
}

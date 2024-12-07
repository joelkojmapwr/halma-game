package studia.Board;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import studia.Utils.Player;
import studia.Utils.Point;
import studia.Utils.TrailingZeros;



public class Board {
    public Point[][] points;
    public Map<Integer, Point> validPointsMap = new HashMap<Integer, Point>();
    public Player[] players;
    public int validPointsNumber = 0;
    public int length;
    public int height;
    public int triangleSize;

    // cornerPoints starting from top (12 o'clock position) and going clockwise
    public List<Point> cornerPoints = new ArrayList<Point>();

    public void setTriangleSize(int triangleSize) {
        this.triangleSize = triangleSize;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPoint(Point point, int x, int y) {
        if (points[x][y] != null) {
            //System.out.println("Point already exists");
            return;
        }
        this.points[x][y] = point;
    }

    public void initPoints(){
        points = new Point[length][height];
    }

    public void addCornerPoint(Point point) {
        cornerPoints.add(point);
    }

    public void printBoard() {
        int countPrintedPoints = 0;
        for (int i = 0; i < height; i++) {
            int startIndex = 0;
            if (points[0][i] == null && i%2 == 1) { 
                System.out.print("  ");
                startIndex = 1;
            }
            for (int j = startIndex; j < length; j++) {
                //int currentIndex = i*length + j;
                String pointString = TrailingZeros.addTrailingZeros(countPrintedPoints, 3);
                if (points[j][i] == null) {
                    if ((j+i)%2 == 0) {
                        System.out.print("   "); // print 3 spaces
                    } else {
                        System.out.print(" ");
                    }
                } else if (points[j][i].pawn == null) {
                    //char c = (char) (j+65);
                    System.out.print(pointString);
                    countPrintedPoints++;
                } else {
                    points[j][i].pawn.print(pointString);
                    countPrintedPoints++;
                }
            }
            System.out.println();
        }
    }

    public void generateNeighbours1() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (points[j][i] != null) {
                    // sprawdzamy czy sąsiedzi istnieją
                    // jeśli tak to dodajemy do listy sąsiadów
                    // Pierwsi sąsiedzi są albo na tej samej wysokości i o 2 w prawo lub w lewo, albo o 1 w górę lub w dół i o jeden w prawo lub w lewo
                    // Drudzy sąsiedzi to 2 razy dystans do pierwszych sąsiadów -> czyli dla każdego sąsiada 1 rzędu może istnieć sąsiad 2 rzędu 
                    if (j >= 2) {
                        // lewy sąsiad
                        if (points[j-2][i] != null) {
                            points[j][i].neighbours1.add(points[j-2][i]);
                        }
                    }
                    if (j < length -2) {
                        // prawy sąsiad
                        if (points[j+2][i] != null) {
                            points[j][i].neighbours1.add(points[j+2][i]);
                        }
                    }
                    if (i >= 1) {
                        // górny sąsiad
                        if (j < length -1){
                            // górny prawy sąsiad
                            if (points[j+1][i-1] != null) {
                                points[j][i].neighbours1.add(points[j+1][i-1]);
                            }
                        }
                        if (j >= 1) {
                            // górny lewy sąsiad
                            if (points[j-1][i-1] != null) {
                                points[j][i].neighbours1.add(points[j-1][i-1]);
                            }
                        }
                    }
                    if (i < height -1) {
                        // dolny sąsiad
                        if (j < length -1){
                            // dolny prawy sąsiad
                            if (points[j+1][i+1] != null) {
                                points[j][i].neighbours1.add(points[j+1][i+1]);
                            }
                        }
                        if (j >= 1) {
                            // dolny lewy sąsiad
                            if (points[j-1][i+1] != null) {
                                points[j][i].neighbours1.add(points[j-1][i+1]);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Depends on generateNeighbours1 because generates neighbours2 based on neighbours1
     */
    public void generateNeighbours2() {
        for (int i=0; i< height; i++) {
            for (int j=0; j<length; j++){
                if (points[j][i] != null) {
                    for (Point neighbour1 : points[j][i].neighbours1) {
                        Point neighbour2 = points[j][i].getNeighbour2FromNeighbour1(neighbour1);
                        if (neighbour2 != null) {
                            points[j][i].neighbours2.add(neighbour2);
                        }
                    }
                }
            }
        }
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void move(Point oldPoint, Point newPoint) {
        if(oldPoint == newPoint) return;
        newPoint.pawn = oldPoint.pawn;
        oldPoint.pawn = null;
    }
}

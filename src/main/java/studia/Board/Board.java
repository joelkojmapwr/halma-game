package studia.Board;

import java.util.List;
import java.util.ArrayList;

public class Board {
    public Point[][] points;
    public int length;
    public int height;
    public int triangleSize;
    public int countPoints=0;
    private String pointString = "XXX";

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
        countPoints++;
    }

    public void initPoints(){
        points = new Point[length][height];
    }

    public void addCornerPoint(Point point) {
        cornerPoints.add(point);
    }

    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (points[j][i] == null) {
                    if ((j+i)%2 == 0) {
                        System.out.print("   "); // print 3 spaces
                    } else {
                        System.out.print(" ");
                    }
                } else if (points[j][i].pawn == null) {
                    //char c = (char) (j+65);
                    System.out.print(pointString);
                } else {
                    points[j][i].pawn.print(pointString);
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
                    // @TODO: dodać sąsiadów górnych i dolnych (prawych i lewych)
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
    /*
     * @TODO add custom Exceptions
     */
    public void move(Pair oldPointPos, Pair newPointPos) {
        if (points[oldPointPos.x][oldPointPos.y] == null) {
            System.out.println("Old point does not exist in the game area");
            return;
        }
        if (points[newPointPos.x][newPointPos.y] == null) {
            System.out.println("New point does not exist in the game area");
            return;
        }
        if (points[oldPointPos.x][oldPointPos.y].pawn == null) {
            System.out.println("No pawn in this point");
            return;
        }
        else {
            if (points[newPointPos.x][newPointPos.y].pawn == null) {
                points[newPointPos.x][newPointPos.y].pawn = points[oldPointPos.x][oldPointPos.y].pawn;
                points[oldPointPos.x][oldPointPos.y].pawn = null;
            }
            else {
                System.out.println("There is already a pawn in this point, could not move");
            }
        }
    }
}
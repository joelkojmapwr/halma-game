package studia.Board;

public class Board {
    public Point[][] points;
    public int length;
    public int height;
    public int pointArrayLength;
    public int size;
    /**
     * 
     * @param size - length of the triangle side (default 4)
     */
    public Board(int size) {
        // wiersze są ułożone na przemian, więc zastosujemy tablicę szrokości 2*szerokość -1 
        // domyślnie 13
        this.length = 3*size +1;
        // domyślnie 17
        this.height = 4*size +1;
        // domyślnie 25
        this.pointArrayLength = 2*length -1;
        this.size = size;

        points = new Point[pointArrayLength][height];
        this.initializePoints();
    }

    private void initializePoints() {
        // punkt 0,0 jest w lewym dolnym rogu
        // initialize 1 triangle - 
        for (int i = 0; i < height - this.size; i++) {
            for (int j = i%2; j <= i; j+=2) {
                // w parzystym wierszu pola są na parzystych pozycjach
                // w nieparzystym wierszu pola są na nieparzystych pozycjach
                points[this.pointArrayLength/2 + j][i] = new Point();
                points[this.pointArrayLength/2 - j][i] = new Point();
            }
        }
        // initialize 2 triangle
        for (int i = height - 1; i>=this.size; i--) {
            // zaczynamy od tego na środku
            for (int j = i%2; j <= (height -1 -i); j+=2) {
                // w parzystym wierszu pola są na parzystych pozycjach
                // w nieparzystym wierszu pola są na nieparzystych pozycjach
                points[this.pointArrayLength/2 + j][i] = new Point();
                points[this.pointArrayLength/2 - j][i] = new Point();
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < pointArrayLength; j++) {
                if (points[j][i] != null) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

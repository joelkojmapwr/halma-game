package studia.Utils;

public class Pair {
    public int x;
    public int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Pair distance(Pair a, Pair b) {
        return new Pair(a.x - b.x, a.y - b.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair p = (Pair) obj;
            return p.x == x && p.y == y;
        }
        return false;
    }
}

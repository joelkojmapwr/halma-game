package studia.Utils;

public class Pawn {

    public int color;

    public Pawn(int color){
        this.color = color;
    }
    /**
     * Prints the pawn with the color
     * @param pawnString
     */
    public void print(String pawnString){
        String output = "\033[38;5;" + (color + 1) + "m" + pawnString + "\033[0m";
        System.out.print(output);
    }
}

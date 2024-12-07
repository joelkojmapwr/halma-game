package studia.Utils;

public class Pawn {

    public int color;

    public Pawn(int color){
        this.color = color;
    }

    public void print(String pawnString){
        String output = "\033[38;5;" + (color + 1) + "m" + pawnString + "\033[0m";
        System.out.print(output);
    }
}

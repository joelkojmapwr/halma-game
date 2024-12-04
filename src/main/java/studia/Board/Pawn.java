package studia.Board;

public class Pawn {
    // Colour
    // player ID
    public Player player;
    public int color;

    public Pawn(int color){
        //this.player = player;
        this.color = color;
    }

    public void print(){
        String output = "\033[38;5;" + color + "mX\033[0m";
        System.out.print(output);
    }
}

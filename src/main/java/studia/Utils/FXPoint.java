package studia.Utils;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class representing a point on the board, just like {@link Point} but with a circle for GUI 
 */
public class FXPoint extends Point {
    private Circle circle;
    public Boolean isClicked = false;
    private static Color defcolor = Color.rgb(247, 237, 181); 

    public FXPoint(Pair pos){
        super(pos);
        circle = new Circle();
        circle.setRadius(1);
    }

    public Circle getCircle(){
        updateCircle();
        return circle;
    }
    /**
     * Updates the color of the circle based on the pawn on the point
     */
    public void updateCircle(){
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(defcolor);
        }
    }
    /**
     * Updates the color of the circle with the given color
     * @param c
     */
    public void updateCircle(Color c){
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(c);
        }
    }
}

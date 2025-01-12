package studia.Utils;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(defcolor);
        }
        return circle;
    }

    public void updateCircle(){
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(defcolor);
        }
    }
    
    public void updateCircle(Color c){
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(c);
        }
    }
}

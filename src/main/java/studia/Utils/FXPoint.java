package studia.Utils;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FXPoint extends Point {
    private Circle circle;
    public Boolean isClicked = false;

    public FXPoint(Pair pos){
        super(pos);
        circle = new Circle();
        circle.setRadius(1);
    }
    
    public Circle getCircle(){
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(Color.WHITE);
        }
        return circle;
    }

    public void updateCircle(){
        if (pawn != null) {
            circle.setFill(Color.web(studia.Utils.Color.colorName(pawn.color)));
        } else {
            circle.setFill(Color.WHITE);
        }
    }
}

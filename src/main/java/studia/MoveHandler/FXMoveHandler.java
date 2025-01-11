package studia.MoveHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javafx.scene.shape.Circle;
import studia.Board.Board;
import studia.Utils.FXPoint;
import studia.Utils.Point;
import studia.Client.Client;
import studia.Common.Message;

public class FXMoveHandler {

    protected Board board;
    public List<FXPoint> selectedPoints = new ArrayList<FXPoint>();
    protected Client client;

    public FXMoveHandler(Board board, Client client) {
        this.board = board;
        this.client = client;
        selectedPoints = new ArrayList<FXPoint>();
        setPointsClickedEventHandler();
    }
    
    protected void setPointsClickedEventHandler(){
        for (int i = 0; i < board.length; i++) {
            for (int j=0; j<board.height; j++) {
                if(board.points[i][j] == null) {
                    continue;
                }
                if (board.points[i][j] instanceof FXPoint == false) {
                    continue;
                }
                FXPoint point = (FXPoint) board.points[i][j];
                Circle circle = point.getCircle();
                circle.setOnMouseClicked(e -> {
                    if (selectedPoints.size() == 2) {
                        System.out.println("Two points already selected, you can't select more");
                        selectedPoints.clear();
                        return;
                    }
                    if (point.isClicked == false){
                        selectedPoints.add(point);
                        circle.setStrokeWidth(5);
                        point.isClicked = true;
                    }
                    else {
                        selectedPoints.remove(point);
                        circle.setStrokeWidth(1);
                        point.isClicked = false;
                    }
                    if (selectedPoints.size() == 2) {
                        newFXMove(selectedPoints.get(0), selectedPoints.get(1));
                        selectedPoints.get(0).isClicked = false;
                        selectedPoints.get(1).isClicked = false;
                        selectedPoints.get(0).getCircle().setStrokeWidth(1);
                        selectedPoints.get(1).getCircle().setStrokeWidth(1);
                        selectedPoints.clear();
                    }
                });
            }
            
        }
    }

    
    public Boolean newFXMove(FXPoint oldPoint, FXPoint newPoint) {
        int oldPos = -1;
        int newPos = -1;
        for (Map.Entry<Integer, Point> entry : board.validPointsMap.entrySet()) {
            if (entry.getValue() == oldPoint) {
                oldPos = entry.getKey();
            }
            if (entry.getValue() == newPoint) {
                newPos = entry.getKey();
            }
        }
        if (oldPos != -1 && newPos != -1) {
            client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), oldPos, newPos);
            return true;
        }
        return false;
    }
    
}

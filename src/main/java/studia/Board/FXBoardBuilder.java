package studia.Board;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import studia.Utils.FXPoint;

public class FXBoardBuilder {
    private Board board;
    private int paneWidth;
    private int paneHeight;

    public FXBoardBuilder(Board board, int paneWidth, int paneHeight) {
        this.board = board;
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;
    }
    /**
     * the ratio paneWidth/paneHeight should be 1/1
     * @param paneWidth
     * @param paneHeight
     * @return
     */
    public Pane buildBoard() {
        Pane root = new Pane();
        Polygon triangle1 = new Polygon();
		triangle1.getPoints().addAll(new Double[] {
            (double) paneWidth/2, 0.0,
            (double) paneWidth, (double) paneHeight*(board.height - board.triangleSize)/board.height,
            (double) 0.0, (double) paneHeight*(board.height - board.triangleSize)/board.height,
            
		});
        Polygon triangle2 = new Polygon();
        triangle2.getPoints().addAll(new Double[] {
			(double) paneWidth, (double) paneHeight*board.triangleSize/board.height,
            (double) paneWidth/2, (double) paneHeight,
            (double) 0.0, (double) paneHeight*board.triangleSize/board.height,
            
		});
        Group halmaStar = new Group();
        halmaStar.getChildren().addAll(triangle1, triangle2);
        halmaStar.getStyleClass().add("halmaStar");

        Group pointsGroup = getPointsGroup();
		root.getChildren().addAll(halmaStar, pointsGroup);

        return root;
    }

    public Group getPointsGroup() {

        Group pointsGroup = new Group();
        /*
         * looks quite good with current parameters
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.height; j++) {
                if (board.points[i][j] != null){
                    if (board.points[i][j] instanceof FXPoint){
                        //System.out.println("FXPoint");
                        FXPoint point = (FXPoint) board.points[i][j];
                        Circle circle = point.getCircle();
                        circle.setStroke(Color.BLACK);
                        circle.setCenterX((i+0.5)*paneWidth/board.length);
                        circle.setCenterY(j*paneHeight/board.height + 15);
                        circle.setRadius(15);
                        pointsGroup.getChildren().add(circle);
                    }
                }
                
            }
        }
        return pointsGroup;
    }

    public Pane updatePane() {
        return buildBoard();
    }
}

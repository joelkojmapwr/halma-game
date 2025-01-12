package studia.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.shape.Polygon;
import javafx.scene.Group;

import java.io.*;
import java.net.*;

/** In YinYan variant users select starting corner in this scene*/
public class SelectScene {
	private Scene scene;
	private TextField host, port;
	private Button btn;
	private Label errlabel;
	
	private Polygon[] polygons = new Polygon[6];
	private int selected = -1;
	
	ClientFX parent;
	/**
	 * @param parent Main Class
	 */
	SelectScene(ClientFX parent) {
		this.parent = parent;
		Group g = new Group();
    
		polygons[0] = new Polygon();
		polygons[0].getPoints().addAll(new Double[]{
			2.0*100.0, 0.0,
      2.0*66.0, 2.0*50.0,
      2.0*133.0, 2.0*50.0 });
      
    polygons[1] = new Polygon();
		polygons[1].getPoints().addAll(new Double[]{
			2.0*133.0, 2.0*50.0,
      2.0*200.0, 2.0*50.0,
      2.0*166.0, 2.0*100.0 });
    
    polygons[2] = new Polygon();
    polygons[2].getPoints().addAll(new Double[]{
			2.0*166.0, 2.0*100.0,
      2.0*200.0, 2.0*150.0,
      2.0*133.0, 2.0*150.0 });
      
    polygons[3] = new Polygon();
    polygons[3].getPoints().addAll(new Double[]{
			2.0*66.0, 2.0*150.0,
      2.0*133.0, 2.0*150.0,
      2.0*100.0, 2.0*200.0 });
      
    polygons[4] = new Polygon();
    polygons[4].getPoints().addAll(new Double[]{
			2.0*33.0, 2.0*100.0,
      2.0*66.0, 2.0*150.0,
      0.0, 2.0*150.0 });
      
    polygons[5] = new Polygon();
    polygons[5].getPoints().addAll(new Double[]{
			2.0*33.0, 2.0*100.0,
      0.0, 2.0*50.0,
      2.0*66.0, 2.0*50.0 });
        
    for(int i=0;i<6;i++)
			g.getChildren().add(polygons[i]);
			
		polygons[0].setOnMouseClicked(e -> { selected = 0; });
		polygons[1].setOnMouseClicked(e -> { selected = 1; });
		polygons[2].setOnMouseClicked(e -> { selected = 2; });
		polygons[3].setOnMouseClicked(e -> { selected = 3; });
		polygons[4].setOnMouseClicked(e -> { selected = 4; });
		polygons[5].setOnMouseClicked(e -> { selected = 5; });
        
		scene = new Scene(g, 400, 400);
	}
	
	/**
	 * Returns this scene
	 */
	public Scene get() { return scene; }
	
	/**
	 * @param pos marks this triangle as reserved
	 */
	public void setReserved(int pos) {
		polygons[pos].setFill(javafx.scene.paint.Color.RED);
	}
	
	/**
	 * Blocking call that waits for user to select corner and returns that corner number.
	 */
	public int getCorner() {
		selected = -1;
		try {
			while(selected == -1) Thread.sleep(1);
		} catch(Exception e) {}
		return selected;
	}
}

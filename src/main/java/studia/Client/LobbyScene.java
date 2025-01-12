package studia.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.*;
import java.net.*;

public class LobbyScene {
	private Scene scene;
	private Label label1, label2, label3;
	private int plr = 0, total = 0;
	
	ClientFX parent;
	LobbyScene(ClientFX parent) {
		this.parent = parent;
		
		label1 = new Label("Waiting for players...");
		label2 = new Label("Players: X/X");
		label3 = new Label("Variant: ?");

		VBox vb = new VBox();
		vb.getChildren().addAll(label1, label2, label3);
		scene = new Scene(vb, 300, 200);
	}
	
	public Scene get() { return scene; }
	
	public void updatePlayers(int n, int t) {
		plr = n;
		if(t > 0) total = t;
		label2.setText("Players: " + String.valueOf(plr) + "/" +String.valueOf(total));
	}
	
	public void updateVariant(int v) {
		label3.setText("Variant: " + studia.Utils.Variant.STR[v]);
	}
}

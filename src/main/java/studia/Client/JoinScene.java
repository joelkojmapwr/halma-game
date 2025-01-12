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

/**
 * Scene shown after starting client, server address and port are passed here and client is connected to server
 */
public class JoinScene {
	private Scene scene;
	private TextField host, port;
	private Button btn;
	private Label errlabel;
	
	ClientFX parent;
	/**
	 * @param parent Main Class
	 */
	JoinScene(ClientFX parent) {
		this.parent = parent;
		
		Label label1 = new Label("Host:");
		host = new TextField();
		Label label2 = new Label("Port:");
		port = new TextField();
		btn = new Button("Connect");
		
		btn.setOnAction(event -> onClick());
		
		errlabel = new Label("");
		VBox vb = new VBox();
		vb.getChildren().addAll(label1, host, label2, port, btn, errlabel);
		scene = new Scene(vb, 300, 200);
	}
	
	/**
	 * Returns this scene
	 */
	public Scene get() { return scene; }
	
	/**
	 * Prints error
	 */
	public void printError(String error) {
		errlabel.setText(error);
	}
	
	/**
	 * Handles "Connect" button click.
	 */
	private void onClick() {
		String shost = host.getText();
		String sport = port.getText();
		int iport;
		try { 
			iport = Integer.parseInt(sport);
			parent.tryConnect(shost, iport);
			printError("SUCCESS");
		} catch(Exception e) { printError("Error: " + e.getMessage()); }
	}
}

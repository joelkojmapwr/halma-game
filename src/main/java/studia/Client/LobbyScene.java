package studia.Client;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/** Scene that is being displayed when users are waiting for game to begin*/
public class LobbyScene {
	private Scene scene;
	private Label label1, label2, label3;
	private int plr = 0, total = 0;
	
	ClientFX parent;
	/**
	 * @param parent Main Class
	 */
	LobbyScene(ClientFX parent) {
		this.parent = parent;
		
		label1 = new Label("Waiting for players...");
		label2 = new Label("Players: X/X");
		label3 = new Label("Variant: ?");

		VBox vb = new VBox();
		vb.getChildren().addAll(label1, label2, label3);
		scene = new Scene(vb, 300, 200);
	}
	
	/**
	 * Returns this scene
	 */
	public Scene get() { return scene; }
	
	/**
	 * Updates number of player in lobby and total number of playes.
	 * @param n number of player in lobby
	 * @param t total number of playes
	 */
	public void updatePlayers(int n, int t) {
		plr = n;
		if(t > 0) total = t;
		label2.setText("Players: " + String.valueOf(plr) + "/" +String.valueOf(total));
	}
	
	/**
	 * Updates game variant.
	 * @param v variant
	 */
	public void updateVariant(int v) {
		label3.setText("Variant: " + studia.Utils.Variant.STR[v]);
	}
}

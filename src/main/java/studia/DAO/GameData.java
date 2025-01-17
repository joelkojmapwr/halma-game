package studia.DAO;

public class GameData {
    private int variant;
    private int botsNumber;
    private int playersNumber;
    private int startingPlayer;

    public int getVariant() {
        return variant;
    }
    public void setVariant(int variant) {
        this.variant = variant;
    }

    public int getBotsNumber() {
        return botsNumber;
    }

    public void setBotsNumber(int botsNumber) {
        this.botsNumber = botsNumber;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}

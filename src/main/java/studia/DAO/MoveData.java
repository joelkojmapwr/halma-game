package studia.DAO;

public class MoveData {
    private int from;
    private int to;
    private int playerNumber;

    public MoveData() {
    }

    public MoveData(int from, int to, int playerNumber) {
        this.from = from;
        this.to = to;
        this.playerNumber = playerNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MoveData moveData = (MoveData) obj;
        return from == moveData.from && to == moveData.to && playerNumber == moveData.playerNumber;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}

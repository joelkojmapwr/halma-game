package studia.MoveHandler;

import studia.Utils.Player;

public interface MoveHandler {
    public Boolean newMove(int oldPos, int newPos, Player player);
} 
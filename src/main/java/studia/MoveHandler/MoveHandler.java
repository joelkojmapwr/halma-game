package studia.MoveHandler;

import studia.Utils.Player;
import studia.Utils.Point;
import java.util.List;

public interface MoveHandler {
    public Boolean newMove(int oldPos, int newPos, Player player);
    public List<Point> getAvailableMoves(Player plr, Point from);
} 

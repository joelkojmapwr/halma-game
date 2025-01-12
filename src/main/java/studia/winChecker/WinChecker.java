package studia.winChecker;

import studia.Utils.Player;
/**
 * Interface for checking if a player has won the game
 */
public interface WinChecker {
    Boolean checkWin(Player player);
}

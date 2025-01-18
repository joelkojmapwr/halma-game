package studia.Common;

import studia.Board.Board;
import studia.DAO.MoveData;
import studia.Utils.Color;
import studia.Utils.Player;

import java.util.ArrayList;
import java.util.List;
import studia.Observer.Observer;

public class DAOGame extends Game {

    protected List<Observer> observers;
    
    public DAOGame(Player[] players, int current, Board board) {
        super(players, current, board);
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(Move m) {
        MoveData moveData = new MoveData(m.from, m.to, curplr);
        for(Observer observer : observers) {
            observer.updateGame(moveData);
        }
    }

    @Override
    public boolean playerMove(int p, Move m) {
		if(p != curplr) return false;
		if(!moveHandler.newMove(m.from, m.to, players[p])) return false;

        // Notify observers of a new move
        notifyObservers(m);
		
		board.printBoard();
		System.out.printf("Player %d (%s): %s\n", p, Color.colorName(p), m.toString());
		
		curplr = (curplr + 1) % players.length;
		if(winChecker.checkWin(players[p])) winner = p;
		
		return true;
	}

    public void setCurrentPlayer(int currentPlayer) {
        curplr = currentPlayer;
    }
}

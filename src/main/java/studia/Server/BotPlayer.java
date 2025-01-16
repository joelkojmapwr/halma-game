package studia.Server;

import studia.Utils.Player;
import studia.Utils.Point;
import studia.Utils.Pair;
import studia.Utils.Pawn;
import java.io.*;
import java.net.*;
import studia.Common.Message;
import studia.Common.MoveMessage;
import studia.Common.CornMessage;
import studia.Common.Move;
import studia.MoveHandler.MoveHandler;
import studia.MoveHandler.StandardMoveHandler;
import studia.Board.Board;
import studia.Common.Game;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

/**
 * Player class that sends and receives messages
 */
public class BotPlayer extends ServerPlayer {
	private Board board;
	private Game game;
	private MoveHandler movhandle;
	private Queue<Pawn> history = new ArrayDeque<Pawn>();
	private Map<Point, Integer> distmap = new HashMap<Point, Integer>();
	
	public BotPlayer(Server server, int color) {
		super(server, color);
	}
	
	private class PointPair {
		public Point p0, p1;
		
		PointPair() {}
		
		PointPair(Point p0, Point p1) {
			this.p0 = p0;
			this.p1 = p1;
		}
	}
	
	private void initdist() {
		Queue<Point> q = new ArrayDeque<Point>();
		Set<Point> v = new HashSet<Point>();
		
		v.add(finishCorner);
		q.add(finishCorner);
		distmap.put(finishCorner, 0);
		while(!q.isEmpty()) {
			Point c = q.poll();
			for(Point p: c.neighbours1) {
				if(v.contains(p)) continue;
				v.add(p);
				distmap.put(p, distmap.get(c) + 1);
				q.add(p);
			}
		}
	}
	
	private Move decodeMove(Point oldPoint, Point newPoint) {
		int oldPos = -1;
		int newPos = -1;
		for (Map.Entry<Integer, Point> entry : board.validPointsMap.entrySet()) {
			if (entry.getValue() == oldPoint) oldPos = entry.getKey();
			if (entry.getValue() == newPoint) newPos = entry.getKey();
		}
		return new Move(oldPos, newPos);
	}

	private boolean leavingFinish(PointPair p) {
		int index = board.cornerPoints.indexOf(startCorner);
		switch(index) {
			case 0: return p.p0.pos.y > p.p1.pos.y;
			case 1:
			case 2:
				return p.p0.pos.x < p.p1.pos.x;
			case 3: return p.p0.pos.y < p.p1.pos.y;
			case 4:
			case 5:
				return p.p0.pos.x > p.p1.pos.x;
		}
		return false;
	}

	int movs = 0;
	private Move bestMove() {
		List<Point> yourpoints = new ArrayList<Point>();
		for (Map.Entry<Integer, Point> entry : board.validPointsMap.entrySet())
			if (entry.getValue().pawn != null && pawns.contains(entry.getValue().pawn))
				yourpoints.add(entry.getValue());
		List<PointPair> allmoves = new ArrayList<PointPair>();
		for(Point p0: yourpoints) {
			List<Point> dest = movhandle.getAvailableMoves((Player)this, p0);
			for(Point p1: dest)
				allmoves.add(new PointPair(p0, p1));
		}
		int dist = 999999;
		PointPair min = null;
		for(PointPair p: allmoves) {
			if(history.contains(p.p0.pawn)) continue;
			int d1 = distmap.get(p.p1);
			int d2 = distmap.get(p.p0);
			int d = (d1 - d2);
			boolean p0f = finishPoints.contains(p.p0), p1f = finishPoints.contains(p.p1);
			if(p1f && leavingFinish(p)) d = 999999;
			d += p0f ? 1 : 0;
			d -= p1f && !p0f ? 1 : 0;
			if(d < dist) {
				dist = d;
				min = p;
			}
		}
		System.out.printf("%d\n", movs++);
		if(min == null) {
			history.poll();
			return decodeMove(yourpoints.get(0), yourpoints.get(0)); //stand
		} else {
			if(history.size() >= 2) history.poll();
			history.add(min.p0.pawn);
			return decodeMove(min.p0, min.p1);
		}
	}
	
	public void setBoard(Board b) {
		board = b;
		movhandle = new StandardMoveHandler(board);
		initdist();
	}
	
	public void setGame(Game g) {
		game = g;
	}
	
	/**
	 * @return true if success, can return false when board is null
	 */
	public boolean setMoveHandler(MoveHandler mh) {
		if(board == null) return false;
		movhandle = mh;
		return true;
	}
	
	public void setSocket(Socket s) {}
	
	public void closeSocket() {}
	
	/**
	 * @see studia.Client.Client#writeMessage
	 */
	public void writeMessage(int... args) {
		if(args[0] == Message.MSG_YMOV) {
			Move m = bestMove();
			Message msg = new MoveMessage(new int[] {color, m.from, m.to}, game, server);
			msg.setSender(this);
			server.onMessage(msg);
		} else if(args[0] == Message.MSG_CORN) {
			Message msg = new CornMessage(new int[] {args[1] == 0 ? 1 : 0}, server, null);
			msg.setSender(this);
			server.onMessage(msg);
		}
	}
	
	/**
	 * start receiving messages from player
	 */
	public void startReceiver() {}
	
	/**
	 * join receiver thread
	 */
	public void joinReceiver() throws InterruptedException {}
}



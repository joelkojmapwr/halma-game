package studia.Board;

import studia.Utils.Point;
import studia.Utils.FXPoint;



public class FXBoard extends Board {
    @Override
    public void printBoard() {
			for(Point[] row: points)
				for(Point p: row)
					if(p != null)
						((FXPoint)p).updateCircle();
		}
}

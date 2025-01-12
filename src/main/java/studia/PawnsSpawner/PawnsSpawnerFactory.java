package studia.PawnsSpawner;

import studia.Board.Board;
import studia.Utils.Variant;

public class PawnsSpawnerFactory {
	private Board b;
	private int pawns;
	private int seed;
	public PawnsSpawnerFactory(Board board, int plrpawns, int seed) {
		b = board;
		pawns = plrpawns;
		this.seed = seed;
	}
	
	public PawnsSpawner create(int variant) {
		switch(variant) {
			case Variant.STD: return new StandardPawnsSpawner(pawns);
			case Variant.CHAOS: return new ChaosPawnsSpawner(b, pawns, seed);
			case Variant.YINYAN: return new StandardPawnsSpawner(pawns);
			default: break;
		}
		return null;
	}
}

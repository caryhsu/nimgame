package caryhsu.nim.game.standard;

import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;

public class StandardMain {

	public static void main(String[] args) {
		PositionDumper<StandardNimPosition> dumper = new PositionDumper<StandardNimPosition>(StandardNimPosition.COMPARATOR);
		StandardNimGame game = new StandardNimGame();
		WinningPositionFinder<StandardNimGame, StandardNimPosition> finder = new WinningPositionFinder<StandardNimGame, StandardNimPosition>(game, dumper);
		finder.find();
	}

}

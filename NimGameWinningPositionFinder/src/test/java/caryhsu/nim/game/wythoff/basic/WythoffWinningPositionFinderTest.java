package caryhsu.nim.game.wythoff.basic;

import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.GameCache;
import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;
import caryhsu.nim.game.standard.fix3.StandardNimGame;
import caryhsu.nim.game.standard.fix3.StandardNimPosition;

public class WythoffWinningPositionFinderTest {

	@Test
	public void findWinningPositions() {
		PositionDumper<WythoffPosition> dumper = new PositionDumper<WythoffPosition>(WythoffPosition.COMPARATOR);
		WythoffGame game = new WythoffGame(10, true);
		WinningPositionFinder<WythoffGame, WythoffPosition> finder = new WinningPositionFinder<WythoffGame, WythoffPosition>(game, dumper);
		Set<WythoffPosition> winningPositions = finder.find();
		dumper.print("winningPositions:", winningPositions);
	}

	@Test
	public void dumpAllMove() {
		WythoffGame game = new WythoffGame(10, true);
		GameCache<WythoffGame, WythoffPosition> cache = new GameCache<WythoffGame, WythoffPosition>(game);
		cache.setComparator(WythoffPosition.COMPARATOR);
		cache.dump();
	}

}

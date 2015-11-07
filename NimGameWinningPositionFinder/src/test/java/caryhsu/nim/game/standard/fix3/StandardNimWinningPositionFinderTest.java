package caryhsu.nim.game.standard.fix3;

import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.GameCache;
import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;
import caryhsu.nim.game.multigrablast.two.GrabLastPosition;

public class StandardNimWinningPositionFinderTest {

	@Test
	public void findWinningPositions() {
		PositionDumper<StandardNimPosition> dumper = new PositionDumper<StandardNimPosition>(StandardNimPosition.COMPARATOR);
		StandardNimGame game = new StandardNimGame(20, false);
		WinningPositionFinder<StandardNimGame, StandardNimPosition> finder = new WinningPositionFinder<StandardNimGame, StandardNimPosition>(game, dumper);
		Set<StandardNimPosition> winningPositions = finder.find();
		winningPositions = StandardNimValuesNormalizer.normalize(winningPositions);
		dumper.print("winningPositions:", winningPositions);
	}

	@Test
	public void dumpAllMove() {
		StandardNimGame game = new StandardNimGame(20, false);
		GameCache<StandardNimGame, StandardNimPosition> cache = new GameCache<StandardNimGame, StandardNimPosition>(game);
		cache.setComparator(StandardNimPosition.COMPARATOR);
		cache.dump();
	}

}

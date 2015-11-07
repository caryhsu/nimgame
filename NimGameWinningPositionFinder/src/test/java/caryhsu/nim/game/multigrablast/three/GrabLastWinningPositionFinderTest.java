package caryhsu.nim.game.multigrablast.three;

import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.GameCache;
import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;

public class GrabLastWinningPositionFinderTest {

	@Test
	public void findWinningPositions() {
		PositionDumper<GrabLastPosition> dumper = new PositionDumper<GrabLastPosition>(GrabLastPosition.COMPARATOR);
		GrabLastGame game = new GrabLastGame(10, 1, 3, false, true);
		WinningPositionFinder<GrabLastGame, GrabLastPosition> finder = new WinningPositionFinder<GrabLastGame, GrabLastPosition>(game, dumper);
		Set<GrabLastPosition> winningPositions = finder.find();
		winningPositions = GrabLastNormalizer.normalize(winningPositions);
		dumper.print("winningPositions:", winningPositions);
	}

	@Test
	public void dumpAllMove() {
		GrabLastGame game = new GrabLastGame(10, 1, 3, false, true);
		GameCache<GrabLastGame, GrabLastPosition> cache = new GameCache<GrabLastGame, GrabLastPosition>(game);
		cache.setComparator(GrabLastPosition.COMPARATOR);
		cache.dump();
	}

}

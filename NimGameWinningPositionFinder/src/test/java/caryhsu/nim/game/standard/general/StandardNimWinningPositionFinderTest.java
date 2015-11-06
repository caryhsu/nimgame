package caryhsu.nim.game.standard.general;

import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;

public class StandardNimWinningPositionFinderTest {

	@Test
	public void findWinningPositions() {
		PositionDumper<StandardNimPosition> dumper = new PositionDumper<StandardNimPosition>(StandardNimPosition.COMPARATOR);
		//StandardNimGame game = new StandardNimGame(4, 7, false);
		StandardNimGame game = new StandardNimGame(3, 20, false);
		WinningPositionFinder<StandardNimGame, StandardNimPosition> finder = new WinningPositionFinder<StandardNimGame, StandardNimPosition>(game, dumper);
		Set<StandardNimPosition> winningPositions = finder.find();
		winningPositions = StandardNimValuesNormalizer.normalize(winningPositions);
		dumper.print("winningPositions:", winningPositions);
	}

}

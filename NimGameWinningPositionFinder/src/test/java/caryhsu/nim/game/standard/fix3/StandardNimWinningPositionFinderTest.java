package caryhsu.nim.game.standard.fix3;

import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;
import caryhsu.nim.game.standard.fix3.StandardNimGame;
import caryhsu.nim.game.standard.fix3.StandardNimPosition;
import caryhsu.nim.game.standard.fix3.StandardNimValuesNormalizer;

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

}

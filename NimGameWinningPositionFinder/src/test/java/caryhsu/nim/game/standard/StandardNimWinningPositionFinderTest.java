package caryhsu.nim.game.standard;

import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;
import caryhsu.nim.game.standard.StandardNimGame;
import caryhsu.nim.game.standard.StandardNimPosition;

public class StandardNimWinningPositionFinderTest {

	@Test
	public void findWinningPositions() {
		PositionDumper<StandardNimPosition> dumper = new PositionDumper<StandardNimPosition>(StandardNimPosition.COMPARATOR);
		StandardNimGame game = new StandardNimGame(3, 20);
		WinningPositionFinder<StandardNimGame, StandardNimPosition> finder = new WinningPositionFinder<StandardNimGame, StandardNimPosition>(game, dumper);
		Set<StandardNimPosition> winningPositions = finder.find();
		dumper.print("winningPositions:", winningPositions);
	}

}

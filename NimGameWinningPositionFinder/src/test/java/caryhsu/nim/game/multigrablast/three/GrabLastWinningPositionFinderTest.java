package caryhsu.nim.game.multigrablast.three;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import caryhsu.nim.game.FormulaDumper;
import caryhsu.nim.game.GameCache;
import caryhsu.nim.game.NimNumberUtils;
import caryhsu.nim.game.PositionDumper;
import caryhsu.nim.game.WinningPositionFinder;

public class GrabLastWinningPositionFinderTest {

	@Test
	public void findWinningPositions() {
		PositionDumper<GrabLastPosition> dumper = new PositionDumper<GrabLastPosition>(GrabLastPosition.COMPARATOR);
		FormulaDumper<GrabLastPosition> formulaDumper = new FormulaDumper<GrabLastPosition>() {
			@Override
			public String process(GrabLastPosition position) {
				int[] value0 = NimNumberUtils.toBinaryArray(position.getValues()[0] % 4);
				int[] value1 = NimNumberUtils.toBinaryArray(position.getValues()[1] % 4);
				int[] value2 = NimNumberUtils.toBinaryArray(position.getValues()[2] % 4);
				int value = NimNumberUtils.sumOfArraies(value0, value1, value2);
				return "" + Arrays.toString(value0) + "," + Arrays.toString(value1) + "," + Arrays.toString(value2) + ":" + value;
			}
		};
		dumper.setFormulaDumper(formulaDumper);
		GrabLastGame game = new GrabLastGame(10, 1, 3, false, true);
		WinningPositionFinder<GrabLastGame, GrabLastPosition> finder = new WinningPositionFinder<GrabLastGame, GrabLastPosition>(game, dumper);
		Set<GrabLastPosition> winningPositions = finder.find();
		//winningPositions = GrabLastNormalizer.normalize(winningPositions);
		dumper.println("winningPositions:", winningPositions);
	}

	
	@Test
	public void dumpAllMove() {
		GrabLastGame game = new GrabLastGame(10, 1, 3, false, true);
		GameCache<GrabLastGame, GrabLastPosition> cache = new GameCache<GrabLastGame, GrabLastPosition>(game);
		cache.setComparator(GrabLastPosition.COMPARATOR);
		cache.dump();
	}

}

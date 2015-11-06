package caryhsu.nim.game.standard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StandardNimValuesNormalizer {

	public static int[] normalize(int... values) {
		int[] values2 = values.clone();
		Arrays.sort(values2);
		return values2;
	}

	public static StandardNimPosition normalize(StandardNimPosition position) {
		return new StandardNimPosition(normalize(position.getValues()));
	}


	public static Set<StandardNimPosition> normalize(Set<StandardNimPosition> positions) {
		Set<StandardNimPosition> result = new HashSet<StandardNimPosition>();
		for(StandardNimPosition position : positions) {
			StandardNimPosition position2 = normalize(position);
			result.add(position2);
		}
		return result;
	}

}

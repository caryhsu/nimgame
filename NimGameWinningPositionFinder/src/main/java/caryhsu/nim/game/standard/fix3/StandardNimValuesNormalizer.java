package caryhsu.nim.game.standard.fix3;

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
		int[] values = normalize(position.getValues());
		return new StandardNimPosition(values[0], values[1], values[2]);
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

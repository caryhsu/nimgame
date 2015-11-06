package caryhsu.nim.game.multigrablast.two;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GrabLastNormalizer {

	public static int[] normalize(int... values) {
		int[] values2 = values.clone();
		Arrays.sort(values2);
		return values2;
	}

	public static GrabLastPosition normalize(GrabLastPosition position) {
		int[] values = normalize(position.getValues());
		return new GrabLastPosition(values[0], values[1]);
	}


	public static Set<GrabLastPosition> normalize(Set<GrabLastPosition> positions) {
		Set<GrabLastPosition> result = new HashSet<GrabLastPosition>();
		for(GrabLastPosition position : positions) {
			GrabLastPosition position2 = normalize(position);
			result.add(position2);
		}
		return result;
	}

}

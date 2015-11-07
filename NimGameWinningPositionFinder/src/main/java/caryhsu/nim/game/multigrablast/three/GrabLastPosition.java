package caryhsu.nim.game.multigrablast.three;
import java.util.Comparator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import caryhsu.nim.game.Position;

@EqualsAndHashCode
public class GrabLastPosition implements Position {

	@Getter private int[] values;

	public GrabLastPosition(int value1, int value2, int value3) {
		this.values = new int[] {value1, value2, value3};
	}
	
	@Override
	public String toString() {
		return "[" + this.values[0] + ", " + this.values[1] + ", " + this.values[2] + "]";
	}
	
	
	public static final Comparator<GrabLastPosition> COMPARATOR = new Comparator<GrabLastPosition>() {

		@Override
		public int compare(GrabLastPosition p1, GrabLastPosition p2) {
			int result = Integer.compare(p1.values[0], p2.values[0]);
			if (result != 0) return result;
			result = Integer.compare(p1.values[1], p2.values[1]);
			if (result != 0) return result;
			result = Integer.compare(p1.values[2], p2.values[2]);
			return result;
		}
		
	};

}

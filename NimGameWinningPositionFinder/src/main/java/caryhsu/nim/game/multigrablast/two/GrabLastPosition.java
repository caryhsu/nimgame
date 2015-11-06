package caryhsu.nim.game.multigrablast.two;
import java.util.Comparator;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import caryhsu.nim.game.Position;

@EqualsAndHashCode
public class GrabLastPosition implements Position {

	@Getter private int[] values;

	public GrabLastPosition(int value1, int value2) {
		this.values = new int[] {value1, value2};
	}
	
	@Override
	public String toString() {
		return "[" + this.values[0] + ", " + this.values[1] + "]";
	}
	
	
	public static final Comparator<GrabLastPosition> COMPARATOR = new Comparator<GrabLastPosition>() {

		@Override
		public int compare(GrabLastPosition p1, GrabLastPosition p2) {
			int result = Integer.compare(p1.values[0], p2.values[0]);
			if (result != 0) return result;
			result = Integer.compare(p1.values[1], p2.values[1]);
			return result;
		}
		
	};

}

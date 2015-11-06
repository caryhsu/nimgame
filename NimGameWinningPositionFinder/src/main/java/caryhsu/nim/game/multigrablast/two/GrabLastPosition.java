package caryhsu.nim.game.multigrablast.two;
import java.util.Arrays;
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
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + Arrays.hashCode(values);
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		GrabLastPosition other = (GrabLastPosition) obj;
//		if (!Arrays.equals(values, other.values))
//			return false;
//		return true;
//	}

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

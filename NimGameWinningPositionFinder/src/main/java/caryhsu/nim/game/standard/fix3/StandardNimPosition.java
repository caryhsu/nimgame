package caryhsu.nim.game.standard.fix3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import caryhsu.nim.game.Position;

@EqualsAndHashCode(exclude={})
public class StandardNimPosition implements Position {

	@Getter private int[] values;

	public StandardNimPosition(int value1, int value2, int value3) {
		this.values = new int[] {value1, value2, value3};
	}
	@Override
	public String toString() {
		List<Integer> sizeEachHeapsList = new ArrayList<Integer>();
		for(int size : this.values)
			sizeEachHeapsList.add(size);
		return sizeEachHeapsList.toString();
//		return "[" + this.values[0] + "," + this.values[1] + "," + this.values[2] + "]";
	}
	
	
	public static final Comparator<StandardNimPosition> COMPARATOR = new Comparator<StandardNimPosition>() {

		@Override
		public int compare(StandardNimPosition p1, StandardNimPosition p2) {
			int result = Integer.compare(p1.values[0], p2.values[0]);
			if (result != 0) return result;
			result = Integer.compare(p1.values[1], p2.values[1]);
			if (result != 0) return result;
			result = Integer.compare(p1.values[2], p2.values[2]);
			return result;
		}
		
	};

}

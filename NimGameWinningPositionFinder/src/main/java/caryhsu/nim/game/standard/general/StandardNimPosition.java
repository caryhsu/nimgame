package caryhsu.nim.game.standard.general;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.apache.commons.lang3.ArrayUtils;

import caryhsu.nim.game.Position;

@EqualsAndHashCode(exclude={})
public class StandardNimPosition implements Position {

	@Getter private int[] values;

	public StandardNimPosition(int... values) {
		this.values = values;
//		this.values = StandardNimValuesNormalizer.normalize(this.values);
	}
	
	public StandardNimPosition(List<Integer> values) {
		this.values = ArrayUtils.toPrimitive(values.toArray(new Integer[values.size()]));
//		this.values = StandardNimValuesNormalizer.normalize(this.values);
	}

	@Override
	public String toString() {
		List<Integer> sizeEachHeapsList = new ArrayList<Integer>();
		for(int size : this.values)
			sizeEachHeapsList.add(size);
		return sizeEachHeapsList.toString();
	}
	
	
	public static final Comparator<StandardNimPosition> COMPARATOR = new Comparator<StandardNimPosition>() {

		@Override
		public int compare(StandardNimPosition p1, StandardNimPosition p2) {
			for(int index = 0; index < p1.values.length; index++) {
				int result = Integer.compare(p1.values[index], p2.values[index]);
				if (result != 0) return result;
			}
			return 0;
		}
		
	};

}

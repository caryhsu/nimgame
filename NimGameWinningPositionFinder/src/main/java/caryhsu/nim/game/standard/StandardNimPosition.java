package caryhsu.nim.game.standard;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import caryhsu.nim.game.Position;

@EqualsAndHashCode(exclude={})
@AllArgsConstructor
public class StandardNimPosition implements Position {

	@Getter private int x;
	@Getter private int y;
	@Getter private int z;

	@Override
	public String toString() {
		return "[" + this.x + "," + this.y + "," + this.z + "]";
	}
	
	
	public static final Comparator<StandardNimPosition> COMPARATOR = new Comparator<StandardNimPosition>() {

		@Override
		public int compare(StandardNimPosition p1, StandardNimPosition p2) {
			int result = Integer.compare(p1.x, p2.x);
			if (result != 0) return result;
			result = Integer.compare(p1.y, p2.y);
			if (result != 0) return result;
			result = Integer.compare(p1.z, p2.z);
			return result;
		}
		
	};

}

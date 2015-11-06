package caryhsu.nim.game.grablast;
import java.util.Comparator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import caryhsu.nim.game.Position;

@EqualsAndHashCode(exclude={})
public class GrabLastPosition implements Position {

	@Getter private int value;

	public GrabLastPosition(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "[" + this.value + "]";
	}
	
	public static final Comparator<GrabLastPosition> COMPARATOR = new Comparator<GrabLastPosition>() {

		@Override
		public int compare(GrabLastPosition p1, GrabLastPosition p2) {
			int result = Integer.compare(p1.value, p2.value);
			return result;
		}
		
	};

}

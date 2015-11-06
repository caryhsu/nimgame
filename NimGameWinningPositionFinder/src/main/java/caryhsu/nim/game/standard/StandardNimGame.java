package caryhsu.nim.game.standard;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class StandardNimGame implements Game<StandardNimPosition> {

	private static final StandardNimPosition ZERO = new StandardNimPosition(0,0,0);
	private static final int MAX_SIZE = 20;
	@Getter private Set<StandardNimPosition> allPositions = new HashSet<StandardNimPosition>();

	public StandardNimGame() {
		initPositions();
	}

	public void add(StandardNimPosition... positions) {
		this.add(Arrays.asList(positions));
	}

	public void add(Collection<StandardNimPosition> positions) {
		this.allPositions.addAll(positions);
	}

	private void initPositions() {
		for(int i = 0; i <= MAX_SIZE; i++) {
			for(int j = 0; j <= MAX_SIZE; j++) {
				for(int k = 0; k <= MAX_SIZE; k++) {
					StandardNimPosition position = new StandardNimPosition(i, j, k);
					this.add(position);
				}
			}
		}
	}
	
	public boolean canMove(StandardNimPosition current, StandardNimPosition next) {
		if (current.getX() == next.getX() && current.getY() == next.getY()) {
			return current.getZ() > next.getZ();
		}
		if (current.getX() == next.getX() && current.getZ() == next.getZ()) {
			return current.getY() > next.getY();
		}
		if (current.getY() == next.getY() && current.getZ() == next.getZ()) {
			return current.getX() > next.getX();
		}
		return false;
	}

	public Set<StandardNimPosition> getOverPositions() {
		Set<StandardNimPosition> positions = new HashSet<StandardNimPosition>();
		positions.add(ZERO);
		return positions;
	}
	

}

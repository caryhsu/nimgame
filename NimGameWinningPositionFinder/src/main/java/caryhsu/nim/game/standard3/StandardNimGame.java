package caryhsu.nim.game.standard3;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class StandardNimGame implements Game<StandardNimPosition> {

	private static final StandardNimPosition ZERO = new StandardNimPosition(0,0,0);
	@Getter private int size;
	@Getter private Set<StandardNimPosition> allPositions = new HashSet<StandardNimPosition>();

	public StandardNimGame(int size) {
		this.size = size;
		initPositions();
	}

	private void initPositions() {
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j <= size; j++) {
				for(int k = 0; k <= size; k++) {
					StandardNimPosition position = new StandardNimPosition(i, j, k);
					this.allPositions.add(position);
				}
			}
		}
	}
	
	@Override
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

	@Override
	public Set<StandardNimPosition> getOverPositions() {
		Set<StandardNimPosition> positions = new HashSet<StandardNimPosition>();
		positions.add(ZERO);
		return positions;
	}
	
	@Override
	public boolean isWinWhenGameOver() {
		return true;
	}

}

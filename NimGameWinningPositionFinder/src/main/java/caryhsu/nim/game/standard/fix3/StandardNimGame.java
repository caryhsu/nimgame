package caryhsu.nim.game.standard.fix3;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class StandardNimGame implements Game<StandardNimPosition> {

	private static final StandardNimPosition ZERO = new StandardNimPosition(0,0,0);
	@Getter private int size;
	@Getter private Set<StandardNimPosition> allPositions = new HashSet<StandardNimPosition>();
	@Getter private boolean winWhenGameOver;

	public StandardNimGame(int size, boolean winWhenGameOver) {
		this.size = size;
		this.winWhenGameOver = winWhenGameOver;
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
		if (current.getValues()[0] == next.getValues()[0] && current.getValues()[1] == next.getValues()[1]) {
			return current.getValues()[2] > next.getValues()[2];
		}
		if (current.getValues()[0] == next.getValues()[0] && current.getValues()[2] == next.getValues()[2]) {
			return current.getValues()[1] > next.getValues()[1];
		}
		if (current.getValues()[1] == next.getValues()[1] && current.getValues()[2] == next.getValues()[2]) {
			return current.getValues()[0] > next.getValues()[0];
		}
		return false;
	}

	@Override
	public Set<StandardNimPosition> getOverPositions() {
		Set<StandardNimPosition> positions = new HashSet<StandardNimPosition>();
		positions.add(ZERO);
		return positions;
	}
	
}

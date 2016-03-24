package caryhsu.nim.game.wythoff.basic;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class WythoffGame implements Game<WythoffPosition> {

	@Getter private int size;
	@Getter private Set<WythoffPosition> allPositions = new HashSet<WythoffPosition>();
	@Getter private boolean winWhenGameOver;

	public WythoffGame(int size, boolean winWhenGameOver) {
		this.size = size;
		this.winWhenGameOver = winWhenGameOver;
		initPositions();
	}

	private void initPositions() {
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j <= size; i++) {
				WythoffPosition position = new WythoffPosition(i, j);
				this.allPositions.add(position);
			}
		}
	}
	
	@Override
	public boolean canMove(WythoffPosition current, WythoffPosition next) {
		int delta0 = current.getValues()[0] - next.getValues()[0];
		int delta1 = current.getValues()[1] - next.getValues()[1];
		return (delta0 == 0 && delta1 > 0) ||
				(delta1 == 0 && delta0 > 0) ||
				(delta0 > 0 && delta0 == delta1);
	}

	@Override
	public Set<WythoffPosition> getOverPositions() {
		Set<WythoffPosition> positions = new HashSet<WythoffPosition>();
		positions.add(new WythoffPosition(0, 0));
		return positions;
	}
	
}

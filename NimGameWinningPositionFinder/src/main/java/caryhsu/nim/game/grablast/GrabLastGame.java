package caryhsu.nim.game.grablast;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class GrabLastGame implements Game<GrabLastPosition> {

	@Getter private int size;
	@Getter private int minMove;
	@Getter private int maxMove;
	@Getter private Set<GrabLastPosition> allPositions = new HashSet<GrabLastPosition>();
	@Getter private boolean winWhenGameOver;

	public GrabLastGame(int size, int minMove, int maxMove, boolean winWhenGameOver) {
		this.size = size;
		this.minMove = minMove;
		this.maxMove = maxMove;
		this.winWhenGameOver = winWhenGameOver;
		initPositions();
	}

	private void initPositions() {
		for(int i = 0; i <= size; i++) {
			GrabLastPosition position = new GrabLastPosition(i);
			this.allPositions.add(position);
		}
	}
	
	@Override
	public boolean canMove(GrabLastPosition current, GrabLastPosition next) {
		int delta = next.getValue() - current.getValue();
		return delta >= minMove && delta <= maxMove;
	}

	@Override
	public Set<GrabLastPosition> getOverPositions() {
		Set<GrabLastPosition> positions = new HashSet<GrabLastPosition>();
		positions.add(new GrabLastPosition(size));
		return positions;
	}
	
}

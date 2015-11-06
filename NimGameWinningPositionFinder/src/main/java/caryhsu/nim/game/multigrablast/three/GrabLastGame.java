package caryhsu.nim.game.multigrablast.three;

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
			for(int j = 0; j <= size; j++) {
				for(int k = 0; k <= size; k++) {
					GrabLastPosition position = new GrabLastPosition(i, j, k);
					this.allPositions.add(position);
				}
			}
		}
	}
	
	@Override
	public boolean canMove(GrabLastPosition current, GrabLastPosition next) {
		int delta0 = next.getValues()[0] - current.getValues()[0];
		int delta1 = next.getValues()[1] - current.getValues()[1];
		int delta2 = next.getValues()[2] - current.getValues()[2];
		return (delta1 == 0 && delta2 == 0 && delta0 >= minMove && delta0 <= maxMove) || 
				(delta0 == 0 && delta2 == 0 && delta1 >= minMove && delta1 <= maxMove) ||
				(delta0 == 0 && delta1 == 0 && delta2 >= minMove && delta2 <= maxMove);
	}

	@Override
	public Set<GrabLastPosition> getOverPositions() {
		Set<GrabLastPosition> positions = new HashSet<GrabLastPosition>();
		positions.add(new GrabLastPosition(size, size, size));
		return positions;
	}
	
}

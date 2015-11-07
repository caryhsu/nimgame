package caryhsu.nim.game.multigrablast.three;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class GrabLastGame implements Game<GrabLastPosition> {

	@Getter private int size;
	@Getter private int minMove;
	@Getter private int maxMove;
	@Getter private boolean reverse;
	@Getter private Set<GrabLastPosition> allPositions = new HashSet<GrabLastPosition>();
	@Getter private boolean winWhenGameOver;

	public GrabLastGame(int size, int minMove, int maxMove, boolean reverse, boolean winWhenGameOver) {
		this.size = size;
		this.minMove = minMove;
		this.maxMove = maxMove;
		this.reverse = reverse;
		this.winWhenGameOver = winWhenGameOver;
		initPositions();
	}

	private void initPositions() {
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j <= size; j++) {
				for(int k = 0; k <= size; k++) {
					GrabLastPosition position = new GrabLastPosition(i, j, k);
					System.out.println(position);
					this.allPositions.add(position);
				}
			}
		}
	}
	
	@Override
	public boolean canMove(GrabLastPosition current, GrabLastPosition next) {
		if (current.toString().equals("[0, 0, 0]") && next.toString().equals("[0, 1, 0]")) {
			System.out.println(current);
		}
		if (reverse) {
			return canMove0(next, current);
		}
		else {
			return canMove0(current, next);
		}		
	}
	
	private boolean canMove0(GrabLastPosition current, GrabLastPosition next) {
		int delta0 = current.getValues()[0] - next.getValues()[0];
		int delta1 = current.getValues()[1] - next.getValues()[1];
		int delta2 = current.getValues()[2] - next.getValues()[2];
		return (delta1 == 0 && delta2 == 0 && delta0 >= minMove && delta0 <= maxMove) || 
				(delta0 == 0 && delta2 == 0 && delta1 >= minMove && delta1 <= maxMove) ||
				(delta0 == 0 && delta1 == 0 && delta2 >= minMove && delta2 <= maxMove);
	}

	@Override
	public Set<GrabLastPosition> getOverPositions() {
		Set<GrabLastPosition> positions = new HashSet<GrabLastPosition>();
		GrabLastPosition lastPos;
		if (this.reverse) {
			lastPos = new GrabLastPosition(size, size, size);
		}
		else {
			lastPos = new GrabLastPosition(0, 0, 0);
		}
		positions.add(lastPos);
		return positions;
	}
	
}

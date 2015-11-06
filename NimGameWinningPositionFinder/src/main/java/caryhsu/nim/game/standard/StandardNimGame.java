package caryhsu.nim.game.standard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class StandardNimGame implements Game<StandardNimPosition> {

	private static final StandardNimPosition ZERO = new StandardNimPosition(0,0,0);
	@Getter private int heapCount;
	@Getter private int sizeEachHeap;
	@Getter private Set<StandardNimPosition> allPositions = new HashSet<StandardNimPosition>();

	public StandardNimGame(int heapCount, int sizeEachHeap) {
		this.heapCount = heapCount;
		this.sizeEachHeap = sizeEachHeap;
		initPositions();
	}

	private void initPositions() {
		for(int i = 0; i <= sizeEachHeap; i++) {
			for(int j = 0; j <= sizeEachHeap; j++) {
				for(int k = 0; k <= sizeEachHeap; k++) {
					StandardNimPosition position = new StandardNimPosition(i, j, k);
					this.allPositions.add(position);
				}
			}
		}
	}
	
	@Override
	public boolean canMove(StandardNimPosition current, StandardNimPosition next) {
		List<Integer> diffIndexes = getValueDiffIndexes(current, next);
		if (diffIndexes.size() != 1) return false;
		int index = diffIndexes.get(0);
		int currentValue = current.getValues()[index];
		int nextValue = next.getValues()[index];
		return currentValue > nextValue;
	}

	private List<Integer> getValueDiffIndexes(StandardNimPosition p1, StandardNimPosition p2) {
		List<Integer> result = new ArrayList<Integer>();
		for(int index = 0; index < p1.getValues().length; index++) {
			int v1 = p1.getValues()[index];
			int v2 = p2.getValues()[index];
			if (v1 != v2) result.add(index);
		}
		return result;
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

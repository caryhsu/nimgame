package caryhsu.nim.game.standard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import caryhsu.nim.game.Game;

public class StandardNimGame implements Game<StandardNimPosition> {

	//private static final StandardNimPosition ZERO = new StandardNimPosition(0,0,0);
	@Getter private int heapCount;
	@Getter private int sizeEachHeap;
	@Getter private Set<StandardNimPosition> allPositions = new HashSet<StandardNimPosition>();

	public StandardNimGame(int heapCount, int sizeEachHeap) {
		this.heapCount = heapCount;
		this.sizeEachHeap = sizeEachHeap;
		initPositions();
	}

	private void initPositions() {
		int[] values = new int[heapCount];
		while(values != null) {
			StandardNimPosition position = new StandardNimPosition(values);
			this.allPositions.add(position);
			values = next(values);
		}
	}
	
	private int[] next(int[] values) {
		int[] result = values.clone();
		result[0]++;
		for(int index = 0; index < values.length; index++) {
			if (result[index] == sizeEachHeap+1) {
				if (index == values.length - 1) {
					return null;
				}
				else {
					result[index] = 0;
					result[index+1]++;
				}
			}
			else {
				break;
			}
		}
		return result;
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
		positions.add(getZeroPosition());
		return positions;
	}
	
	private StandardNimPosition getZeroPosition() {
		int[] values = new int[heapCount];
		StandardNimPosition position = new StandardNimPosition(values);
		return position;
	}

	@Override
	public boolean isWinWhenGameOver() {
		return true;
	}

}

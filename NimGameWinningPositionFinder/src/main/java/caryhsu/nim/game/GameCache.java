package caryhsu.nim.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import caryhsu.nim.game.grablast.GrabLastPosition;
import lombok.Getter;
import lombok.Setter;

public class GameCache<G extends Game<P>, P extends Position> {

	private G game;
	private Map<P, Set<P>> moveNexts = new HashMap<P, Set<P>>();
	private Map<P, Set<P>> moveFroms = new HashMap<P, Set<P>>();
	@Getter @Setter private Comparator<P> comparator;

	public GameCache(G game) {
		this.game = game;
		for(P current : this.game.getAllPositions()) {
			Set<P> nexts = getNextMoves(current);
			if (!this.moveNexts.containsKey(current)) {
				this.moveNexts.put(current, new HashSet<P>());
			}
			if (!this.moveFroms.containsKey(current)) {
				this.moveFroms.put(current, new HashSet<P>());
			}
			for(P next : nexts) {
				if (!this.moveNexts.containsKey(next)) {
					this.moveNexts.put(next, new HashSet<P>());
				}
				if (!this.moveFroms.containsKey(next)) {
					this.moveFroms.put(next, new HashSet<P>());
				}
				addMove(current, next);
			}
		}
	}

	private Set<P> getNextMoves(P position) {
		Set<P> positions = new HashSet<P>();
		Set<P> allPositions = game.getAllPositions();
		for(P iPosition : allPositions) {
			if (game.canMove(position, iPosition)) {
				positions.add(iPosition);
			}
		}
		return positions;
	}

	private void addMove(P current, P next) {
		this.moveNexts.get(current).add(next);
		this.moveFroms.get(next).add(current);
	}

	public Set<P> getMoveNexts(P position) {
		return this.moveNexts.get(position);
	}
	
	public Set<P> getMoveFroms(P position) {
		return this.moveFroms.get(position);
	}


	public void dump() {
		Set<P> allPositions = this.game.getAllPositions();
		List<P> allPositionList = new ArrayList<P>(allPositions);
		Collections.sort(allPositionList, comparator);
		for(P current : allPositionList) {
			Set<P> nexts = this.getMoveNexts(current);
			List<P> nextList = new ArrayList<P>(nexts);
			Collections.sort(nextList, comparator);
			for(P next : nextList) {
				System.out.println(current + "-->" + next);
			}
		}
		
	}
}

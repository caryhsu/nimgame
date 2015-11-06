package caryhsu.nim.game;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;


public class WinningPositionFinder<G extends Game<P>, P extends Position> {

	@Getter private G game;
	private Map<P, Set<P>> moveNexts = new HashMap<P, Set<P>>();
	private Map<P, Set<P>> moveFroms = new HashMap<P, Set<P>>();
	private Set<P> winningPositions = new HashSet<P>();
	private Set<P> dangerousPositions = new HashSet<P>();
	private Set<P> unknownStatePositions = new HashSet<P>();
	private PositionDumper<P> dumper;
	
	public WinningPositionFinder(G game, PositionDumper<P> dumper) {
		this.game = game;
		this.dumper = dumper;
		initMoves();
	}
	
	private void initMoves() {
		for(P position : this.game.getAllPositions()) {
			Set<P> nexts = getNextMoves(position);
			for(P next : nexts) {
				addMove(position, next);
			}
		}
	}

	public void find() {
		this.unknownStatePositions = new HashSet<P>(this.game.getAllPositions());
		//dumper.print("unknownStatePositions:", unknownStatePositions);
		Set<P> newWinningPositions = this.game.getOverPositions();
		while(!newWinningPositions.isEmpty()) {
			//dumper.print("newWinningPositions:", newWinningPositions);
			for(P newWinningPosition : newWinningPositions) {
				this.winningPositions.add(newWinningPosition);
				this.unknownStatePositions.remove(newWinningPosition);
				Set<P> dangerousPositions = this.moveFroms.get(newWinningPosition);
				this.dangerousPositions.addAll(dangerousPositions);
				this.unknownStatePositions.removeAll(dangerousPositions);
			}
			//dumper.print("dangerousPositions:", this.dangerousPositions);
			//dumper.print("unknownStatePositions:", this.unknownStatePositions);
			newWinningPositions = getNextNewWiningPositions();
		}
		dumper.print("winningPositions:", this.winningPositions);
	}
	
	private Set<P> getNextNewWiningPositions() {
		Set<P> positions = new HashSet<P>();
		for(P position : this.unknownStatePositions) {
			if (canMoveToUnknownStatePosition(position)) {
				positions.add(position);
			}
		}
		return positions;
	}

	private boolean canMoveToUnknownStatePosition(P position) {
		Set<P> nexts = this.moveNexts.get(position);
		for(P next : nexts) {
			if (this.unknownStatePositions.contains(next)) {
				return false;
			}
		}
		return true;
	}

	private void addMove(P current, P next) {
		if (!this.moveNexts.containsKey(current)) {
			this.moveNexts.put(current, new HashSet<P>());
		}
		this.moveNexts.get(current).add(next);
		
		if (!this.moveFroms.containsKey(next)) {
			this.moveFroms.put(next, new HashSet<P>());
		}
		this.moveFroms.get(next).add(current);
	}
	
	private Set<P> getNextMoves(P position) {
		Set<P> positions = new HashSet<P>();
		for(P iPosition : game.getAllPositions()) {
			if (game.canMove(position, iPosition)) {
				positions.add(iPosition);
			}
		}
		return positions;
	}
	
}

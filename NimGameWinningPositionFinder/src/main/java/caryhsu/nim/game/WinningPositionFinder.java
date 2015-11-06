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

	public Set<P> find() {
		this.unknownStatePositions = new HashSet<P>(this.game.getAllPositions());
		//dumper.print("unknownStatePositions:", unknownStatePositions);
		Set<P> newWinningPositions;
		if (this.game.isWinWhenGameOver()) {
			newWinningPositions = this.game.getOverPositions();
//			dumper.print("newWinningPositions:", newWinningPositions);
		}
		else {
			Set<P> dangerousPositions = this.game.getOverPositions();
			this.dangerousPositions.addAll(dangerousPositions);
			this.unknownStatePositions.removeAll(dangerousPositions);
			newWinningPositions = getNextNewWiningPositions();
		}
		while(!newWinningPositions.isEmpty()) {
			dumper.print("newWinningPositions:", newWinningPositions);
			for(P newWinningPosition : newWinningPositions) {
				this.winningPositions.add(newWinningPosition);
				this.unknownStatePositions.remove(newWinningPosition);
				Set<P> dangerousPositions = this.moveFroms.get(newWinningPosition);
				if (dangerousPositions == null) {
					throw new NullPointerException("cannot find position:" + newWinningPosition);
				}
				this.dangerousPositions.addAll(dangerousPositions);
				this.unknownStatePositions.removeAll(dangerousPositions);
			}
			dumper.print("dangerousPositions:", this.dangerousPositions);
			dumper.print("unknownStatePositions:", this.unknownStatePositions);
			newWinningPositions = getNextNewWiningPositions();
		}
		dumper.print("winningPositions:", this.winningPositions);
		return this.winningPositions;
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
		this.moveNexts.get(current).add(next);
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

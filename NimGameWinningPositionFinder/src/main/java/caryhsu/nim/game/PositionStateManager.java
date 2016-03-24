package caryhsu.nim.game;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class PositionStateManager<G extends Game<P>, P extends Position> {

	@Getter private Set<P> winningPositions = new HashSet<P>();
	@Getter private Set<P> dangerousPositions = new HashSet<P>();
	@Getter private Set<P> unknownStatePositions = new HashSet<P>();

	public PositionStateManager(G game) {
		this.setState(game.getAllPositions(), PositionState.UNKNOWN);
	}
	
	public void setState(P[] positions, PositionState state) {
		setState(Arrays.asList(positions), state);
	}

	public void setState(P position, PositionState state) {
		if (state == PositionState.DANGEROUS) {
			this.dangerousPositions.add(position);
			this.unknownStatePositions.remove(position);
			this.winningPositions.remove(position);
		}
		else if (state == PositionState.WINNING) {
			this.dangerousPositions.remove(position);
			this.unknownStatePositions.remove(position);
			this.winningPositions.add(position);
		}
		else if (state == PositionState.UNKNOWN) {
			this.dangerousPositions.remove(position);
			this.unknownStatePositions.add(position);
			this.winningPositions.remove(position);
		}
	}

	public void setState(Collection<P> positions, PositionState state) {
		if (state == PositionState.DANGEROUS) {
			this.dangerousPositions.addAll(positions);
			this.unknownStatePositions.removeAll(positions);
			this.winningPositions.removeAll(positions);
		}
		else if (state == PositionState.WINNING) {
			this.dangerousPositions.removeAll(positions);
			this.unknownStatePositions.removeAll(positions);
			this.winningPositions.addAll(positions);
		}
		else if (state == PositionState.UNKNOWN) {
			this.dangerousPositions.removeAll(positions);
			this.unknownStatePositions.addAll(positions);
			this.winningPositions.removeAll(positions);
		}
	}

	public static enum PositionState {
		DANGEROUS,
		WINNING,
		UNKNOWN;
	}
}

package caryhsu.nim.game;

import java.util.Set;


public interface Game<P extends Position> {
	
	public Set<P> getAllPositions();

	public boolean canMove(P current, P next);
	
	public Set<P> getOverPositions();
	
	public boolean isWinWhenGameOver();
	
}

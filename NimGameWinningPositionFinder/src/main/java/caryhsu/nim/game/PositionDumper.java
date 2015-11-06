package caryhsu.nim.game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionDumper<P extends Position> {
	
	private Comparator<P> comparator;

	public void print(String prefix, P position) {
		System.out.println(prefix + position);
	}
	
	public void print(String prefix, Collection<P> positions) {
		List<P> p2 = new ArrayList<P>(positions);
		Collections.sort(p2, comparator);
		System.out.println(prefix + p2);
	}

}

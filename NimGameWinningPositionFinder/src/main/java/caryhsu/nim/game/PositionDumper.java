package caryhsu.nim.game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PositionDumper<P extends Position> {
	
	@Getter @Setter private Comparator<P> comparator;
	@Getter @Setter FormulaDumper<P> formulaDumper;

	public PositionDumper(Comparator<P> comparator) {
		this(comparator, null);
	}
	public void print(String prefix, P position) {
		System.out.println(prefix + position);
	}
	
	public void print(String prefix, Collection<P> positions) {
		List<P> p2 = new ArrayList<P>(positions);
		if (comparator != null) {
			Collections.sort(p2, comparator);
		}
		System.out.println(prefix + p2);
	}

	public void println(String prefix, Collection<P> positions) {
		List<P> p2 = new ArrayList<P>(positions);
		if (comparator != null) {
			Collections.sort(p2, comparator);
		}
		System.out.println(prefix);
		for(P p : p2) {
			if (formulaDumper != null) {
				System.out.println(p + ":" + formulaDumper.process(p));
			}
			else {
				System.out.println(p);
			}
		}
	}
}

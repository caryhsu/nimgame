package caryhsu.nim.game.multigrablast.two;

import org.junit.Test;

public class GrabLastPositionTest {

	@Test
	public void testEquals() {
		GrabLastPosition p1 = new GrabLastPosition(5,5);
		GrabLastPosition p2 = new GrabLastPosition(5,5);
		System.out.println(p1.equals(p2));
		System.out.println(p1.hashCode() + ":" + p2.hashCode());
	}
}

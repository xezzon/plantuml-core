package net.sourceforge.plantuml.nwdiag.next;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NTetris<S extends Staged> {

	private final Map<S, Integer> all = new LinkedHashMap<>();
	private final BooleanGrid grid = new BooleanGrid();

	@Override
	public String toString() {
		return all.toString();
	}

	public void add(S element) {
		int x = 0;
		while (true) {
			if (grid.isSpaceAvailable(element, x)) {
				all.put(element, x);
				grid.useSpace(element, x);
				return;
			}
			x++;
			if (x > 100)
				throw new IllegalStateException();

		}
	}

	public final Map<S, Integer> getPositions() {
		return Collections.unmodifiableMap(all);
	}

	public int getNWidth() {
		int max = 0;
		for (Entry<S, Integer> ent : all.entrySet())
			max = Math.max(max, ent.getValue() + ent.getKey().getNWidth());

		return max;
	}

}

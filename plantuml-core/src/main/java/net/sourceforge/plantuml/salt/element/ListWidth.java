package net.sourceforge.plantuml.salt.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListWidth {

	private final List<Double> allWidth = new ArrayList<>();

	public void add(double width) {
		this.allWidth.add(width);
	}

	public ListWidth mergeMax(ListWidth other) {
		final ListWidth result = new ListWidth();
		for (int i = 0; i < this.allWidth.size() || i < other.allWidth.size(); i++) {
			final double w1 = this.getWidthSafe(i);
			final double w2 = other.getWidthSafe(i);
			result.add(Math.max(w1, w2));
		}
		return result;
	}

	private double getWidthSafe(int i) {
		if (i < allWidth.size()) {
			return allWidth.get(i);
		}
		return 0;
	}

	public double getTotalWidthWithMargin(final double margin) {
		double result = 0;
		for (Double w : allWidth) {
			if (result > 0) {
				result += margin;
			}
			result += w;
		}
		return result;
	}

	public Iterator<Double> iterator() {
		return allWidth.iterator();
	}

}

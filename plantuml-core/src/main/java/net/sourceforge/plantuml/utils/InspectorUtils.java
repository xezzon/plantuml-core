package net.sourceforge.plantuml.utils;

import java.util.List;

public abstract class InspectorUtils {

	private InspectorUtils() {

	}

	public static <O> Inspector<O> inspector(final List<O> list) {
		return new Inspector<O>() {

			private int pos = 0;

			@Override
			public O peek(int ahead) {
				final int tmp = pos + ahead;
				if (tmp < list.size())
					return list.get(tmp);
				return null;
			}

			@Override
			public void jump() {
				pos++;
			}
		};
	}
}

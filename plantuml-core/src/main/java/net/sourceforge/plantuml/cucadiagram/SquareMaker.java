package net.sourceforge.plantuml.cucadiagram;

import java.util.List;

class SquareMaker<O extends Object> {

	public void putInSquare(List<O> data, SquareLinker<O> linker) {
		final int branch = computeBranch(data.size());
		int headBranch = 0;
		for (int i = 1; i < data.size(); i++) {
			final int dist = i - headBranch;
			final O ent2 = data.get(i);
			if (dist == branch) {
				final O ent1 = data.get(headBranch);
				linker.topDown(ent1, ent2);
				headBranch = i;
			} else {
				final O ent1 = data.get(i - 1);
				linker.leftRight(ent1, ent2);
			}
		}

	}

	static int computeBranch(final int size) {
		final double sqrt = Math.sqrt(size);
		final int r = (int) sqrt;
		if (r * r == size) {
			return r;
		}
		return r + 1;
	}

	static int getBottomLeft(final int size) {
		final int s = computeBranch(size);
		final int line = (size - 1) / s;
		return line * s;
	}

	// static int getBottomLeft(final int size) {
	// final int s = computeBranch(size);
	// int result = s * (s - 1);
	// while (result >= size) {
	// result -= s;
	// }
	// return result;
	// }

}

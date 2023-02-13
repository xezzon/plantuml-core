package net.sourceforge.plantuml.salt.element;

import java.util.Comparator;

import net.sourceforge.plantuml.salt.Cell;

class TopFirst implements Comparator<Cell> {

	public int compare(Cell c0, Cell c1) {
		final int diffNb = c0.getNbRows() - c1.getNbRows();
		if (diffNb != 0) {
			return diffNb;
		}
		final int diffPos = c0.getMinRow() - c1.getMinRow();
		return diffPos;
	}

}

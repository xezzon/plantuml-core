package net.sourceforge.plantuml.salt.element;

import java.util.Comparator;

import net.sourceforge.plantuml.salt.Cell;

class LeftFirst implements Comparator<Cell> {

	public int compare(Cell c0, Cell c1) {
		final int diffNb = c0.getNbCols() - c1.getNbCols();
		if (diffNb != 0) {
			return diffNb;
		}
		final int diffPos = c0.getMinCol() - c1.getMinCol();
		return diffPos;
	}

}

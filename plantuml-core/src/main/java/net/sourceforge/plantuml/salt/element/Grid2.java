package net.sourceforge.plantuml.salt.element;

import java.util.List;

import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class Grid2 {

	private final List<Double> rowsStart;
	private final List<Double> colsStart;
	private final TableStrategy strategy;

	public Grid2(List<Double> rowsStart, List<Double> colsStart, TableStrategy strategy) {
		this.rowsStart = rowsStart;
		this.colsStart = colsStart;
		this.strategy = strategy;
	}

	public void drawU(UGraphic ug) {
		final double xmin = colsStart.get(0);
		final double xmax = colsStart.get(colsStart.size() - 1);
		final double ymin = rowsStart.get(0);
		final double ymax = rowsStart.get(rowsStart.size() - 1);
		if (strategy == TableStrategy.DRAW_OUTSIDE || strategy == TableStrategy.DRAW_OUTSIDE_WITH_TITLE) {
			ug.apply(new UTranslate(xmin, ymin)).draw(ULine.hline(xmax - xmin));
			ug.apply(new UTranslate(xmin, ymax)).draw(ULine.hline(xmax - xmin));
			ug.apply(new UTranslate(xmin, ymin)).draw(ULine.vline(ymax - ymin));
			ug.apply(new UTranslate(xmax, ymin)).draw(ULine.vline(ymax - ymin));
		}
		if (drawHorizontal()) {
			for (Double y : rowsStart) {
				ug.apply(new UTranslate(xmin, y)).draw(ULine.hline(xmax - xmin));
			}
		}
		if (drawVertical()) {
			for (Double x : colsStart) {
				ug.apply(new UTranslate(x, ymin)).draw(ULine.vline(ymax - ymin));
			}
		}
	}

	private boolean drawHorizontal() {
		if (strategy == TableStrategy.DRAW_HORIZONTAL || strategy == TableStrategy.DRAW_ALL) {
			return true;
		}
		return false;
	}

	private boolean drawVertical() {
		if (strategy == TableStrategy.DRAW_VERTICAL || strategy == TableStrategy.DRAW_ALL) {
			return true;
		}
		return false;
	}

}

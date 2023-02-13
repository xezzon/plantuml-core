package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class FtileForkInner extends AbstractFtile {

	private final List<Ftile> forks = new ArrayList<>();

	public FtileForkInner(List<Ftile> forks) {
		super(forks.get(0).skinParam());
		for (Ftile ftile : forks) {
			this.forks.add(ftile);
		}
	}
	
	@Override
	public Collection<Ftile> getMyChildren() {
		return Collections.unmodifiableCollection(forks);
	}


	public Swimlane getSwimlaneIn() {
		return forks.get(0).getSwimlaneIn();
	}

	public Swimlane getSwimlaneOut() {
		return getSwimlaneIn();
	}

	public Set<Swimlane> getSwimlanes() {
		return mergeSwimlanes(forks);
	}

	public static Set<Swimlane> mergeSwimlanes(List<Ftile> tiles) {
		final Set<Swimlane> result = new HashSet<>();
		for (Ftile tile : tiles) {
			result.addAll(tile.getSwimlanes());
		}
		return Collections.unmodifiableSet(result);
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();

		double xpos = 0;
		for (Ftile ftile : forks) {
			ug.apply(UTranslate.dx(xpos)).draw(ftile);
			final XDimension2D dim = ftile.calculateDimension(stringBounder);
			xpos += dim.getWidth();
		}
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		double height = 0;
		double width = 0;
		for (Ftile ftile : forks) {
			final XDimension2D dim = ftile.calculateDimension(stringBounder);
			width += dim.getWidth();
			if (dim.getHeight() > height) {
				height = dim.getHeight();
			}
		}
		final XDimension2D dimTotal = new XDimension2D(width, height);
		return new FtileGeometry(dimTotal, dimTotal.getWidth() / 2, 0, dimTotal.getHeight());
	}

	public UTranslate getTranslateFor(Ftile searched, StringBounder stringBounder) {
		double xpos = 0;
		for (Ftile ftile : forks) {
			if (ftile == searched) {
				return UTranslate.dx(xpos);
			}
			final XDimension2D dim = ftile.calculateDimension(stringBounder);
			xpos += dim.getWidth();
		}
		throw new IllegalArgumentException();
	}

}

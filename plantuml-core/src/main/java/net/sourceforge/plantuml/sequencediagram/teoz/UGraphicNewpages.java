package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.graphic.UGraphicDelegator;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class UGraphicNewpages extends UGraphicDelegator {

	private final double ymin;
	private final double ymax;
	private final double dy;

	private UGraphicNewpages create(UGraphic ug, double ymin, double ymax) {
		return new UGraphicNewpages(ug, ymin, ymax, 0);
	}

	private UGraphicNewpages(UGraphic ug, double ymin, double ymax, double dy) {
		super(ug);
		this.ymin = ymin;
		this.ymax = ymax;
		this.dy = dy;
	}

	public void draw(UShape shape) {
		System.err.println("UGraphicNewpages " + shape.getClass());
		if (dy >= ymin && dy < ymax) {
			getUg().draw(shape);
		} else {
			System.err.println("Removing " + shape);
		}

	}

	public UGraphic apply(UChange change) {
		double newdy = dy;
		if (change instanceof UTranslate) {
			newdy += ((UTranslate) change).getDy();
		}
		return new UGraphicNewpages(getUg().apply(change), ymin, ymax, newdy);
	}

}

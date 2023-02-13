package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.geom.Side;
import net.sourceforge.plantuml.svek.AbstractExtremityFactory;

public class ExtremityFactoryCircle extends AbstractExtremityFactory implements ExtremityFactory {

	private final boolean fill;
	private final HColor backgroundColor;

	public ExtremityFactoryCircle(boolean fill, HColor backgroundColor) {
		this.fill = fill;
		this.backgroundColor = backgroundColor;
	}

	@Override
	public UDrawable createUDrawable(XPoint2D center, double angle, Side side) {
		angle -= Math.PI / 2;
		return ExtremityCircle.create(center, fill, angle, backgroundColor);
	}

	@Override
	public UDrawable createUDrawable(XPoint2D p0, XPoint2D p1, XPoint2D p2, Side side) {
		final double ortho = atan2(p0, p2);
		return ExtremityCircle.create(p1, fill, ortho, backgroundColor);
	}

}

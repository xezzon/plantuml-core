package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.ugraphic.UEllipse;

public class ContainingEllipse {

	private final SmallestEnclosingCircle sec = new SmallestEnclosingCircle();
	private final YTransformer ytransformer;

	@Override
	public String toString() {
		return "ContainingEllipse " + getWidth() + " " + getHeight();
	}

	public ContainingEllipse(double coefY) {
		ytransformer = new YTransformer(coefY);
	}

	public void append(XPoint2D pt) {
		pt = ytransformer.getReversePoint2D(pt);
		sec.append(pt);
	}

	public void append(double x, double y) {
		append(new XPoint2D(x, y));
	}

	public double getWidth() {
		return 2 * sec.getCircle().getRadius();
	}

	public double getHeight() {
		return 2 * sec.getCircle().getRadius() * ytransformer.getAlpha();
	}

	public XPoint2D getCenter() {
		return ytransformer.getPoint2D(sec.getCircle().getCenter());
	}

	public UEllipse asUEllipse() {
		final UEllipse ellipse = new UEllipse(getWidth(), getHeight());
		ellipse.setDeltaShadow(deltaShadow);
		return ellipse;
	}

	private double deltaShadow;

	public void setDeltaShadow(double deltaShadow) {
		this.deltaShadow = deltaShadow;
	}

}

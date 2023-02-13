package net.sourceforge.plantuml.klimt.geom;

import java.awt.geom.AffineTransform;
import java.util.Arrays;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class USegment {

	private final double coord[];
	private final USegmentType pathType;

	public USegment(double[] coord, USegmentType pathType) {
		this.coord = coord.clone();
		this.pathType = pathType;
	}

	@Override
	public String toString() {
		return pathType.toString() + " " + Arrays.toString(coord);
	}

	public final double[] getCoord() {
		return coord;
	}

	public final USegmentType getSegmentType() {
		return pathType;
	}

	public USegment translate(double dx, double dy) {

		if (pathType == USegmentType.SEG_ARCTO)
			return new USegment(
					new double[] { coord[0], coord[1], coord[2], coord[3], coord[4], coord[5] + dx, coord[6] + dy },
					pathType);

		if (coord.length != 2)
			throw new UnsupportedOperationException();

		XPoint2D p1 = new XPoint2D(coord[0] + dx, coord[1] + dy);
		return new USegment(new double[] { p1.getX(), p1.getY() }, pathType);
	}

	public USegment rotate(double theta) {
		if (coord.length != 2)
			throw new UnsupportedOperationException();

		XPoint2D p1 = new XPoint2D(coord[0], coord[1]);
		final AffineTransform rotate = AffineTransform.getRotateInstance(theta);
		p1 = p1.transform(rotate);

		return new USegment(new double[] { p1.getX(), p1.getY() }, pathType);
	}

	public USegment affine(AffineTransform transform, double angle, double scale) {
		if (pathType == USegmentType.SEG_ARCTO) {
			XPoint2D p1 = new XPoint2D(coord[5], coord[6]);

			p1 = p1.transform(transform);

			final double large_arc_flag = coord[3];
			final double sweep_flag = coord[4];
			return new USegment(new double[] { coord[0] * scale, coord[1] * scale, coord[2] + angle, large_arc_flag,
					sweep_flag, p1.getX(), p1.getY() }, pathType);
		}

		if (coord.length != 2)
			throw new UnsupportedOperationException();
		XPoint2D p1 = new XPoint2D(coord[0], coord[1]);
		p1 = p1.transform(transform);

		return new USegment(new double[] { p1.getX(), p1.getY() }, pathType);
	}

}

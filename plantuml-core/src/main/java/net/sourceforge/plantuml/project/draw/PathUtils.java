package net.sourceforge.plantuml.project.draw;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UPath;

public class PathUtils {

	public static UPath UtoRight(double width, double height, double round) {
		final double halfRound = round / 2;
		final UPath result = new UPath();
		result.moveTo(0, 0);
		result.lineTo(width - halfRound, 0);
		result.arcTo(new XPoint2D(width, halfRound), halfRound, 0, 1);
		result.lineTo(width, height - halfRound);
		result.arcTo(new XPoint2D(width - halfRound, height), halfRound, 0, 1);
		result.lineTo(0, height);
		return result;
	}

	public static UPath UtoLeft(double width, double height, double round) {
		final double halfRound = round / 2;
		final UPath result = new UPath();
		result.moveTo(width, height);
		result.lineTo(halfRound, height);
		result.arcTo(new XPoint2D(0, height - halfRound), halfRound, 0, 1);
		result.lineTo(0, halfRound);
		result.arcTo(new XPoint2D(halfRound, 0), halfRound, 0, 1);
		result.lineTo(width, 0);
		return result;
	}

}

package net.sourceforge.plantuml.klimt.color;

import java.awt.Color;

public class HColorMiddle extends HColor {

	private final HColor color1;
	private final HColor color2;

	HColorMiddle(HColor c1, HColor c2) {
		this.color1 = c1;
		this.color2 = c2;
	}

	public final HColor getColor1() {
		return color1;
	}

	public final HColor getColor2() {
		return color2;
	}

	@Override
	public Color toColor(ColorMapper mapper) {
		final Color cc1 = color1.toColor(mapper);
		final Color cc2 = color2.toColor(mapper);
		final int r1 = cc1.getRed();
		final int g1 = cc1.getGreen();
		final int b1 = cc1.getBlue();
		final int r2 = cc2.getRed();
		final int g2 = cc2.getGreen();
		final int b2 = cc2.getBlue();

		final int r = (r1 + r2) / 2;
		final int g = (g1 + g2) / 2;
		final int b = (b1 + b2) / 2;
		return new Color(r, g, b);
	}

}

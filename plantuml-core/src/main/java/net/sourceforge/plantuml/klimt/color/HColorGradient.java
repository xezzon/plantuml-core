package net.sourceforge.plantuml.klimt.color;

import java.awt.Color;
import java.util.Objects;

public class HColorGradient extends HColor {

	private final HColor color1;
	private final HColor color2;
	private final char policy;

	HColorGradient(HColor color1, HColor color2, char policy) {
		if (color1 instanceof HColorGradient)
			color1 = ((HColorGradient) color1).color1;

		if (color2 instanceof HColorGradient)
			color2 = ((HColorGradient) color2).color2;

		this.color1 = Objects.requireNonNull(color1);
		this.color2 = Objects.requireNonNull(color2);
		this.policy = policy;
	}

	public final HColor getColor1() {
		return color1;
	}

	public final HColor getColor2() {
		return color2;
	}

	public final Color getColor(ColorMapper mapper, double coeff) {
		if (coeff > 1 || coeff < 0)
			throw new IllegalArgumentException("c=" + coeff);

		final Color c1 = color1.toColor(mapper);
		final Color c2 = color2.toColor(mapper);
		final int vred = c2.getRed() - c1.getRed();
		final int vgreen = c2.getGreen() - c1.getGreen();
		final int vblue = c2.getBlue() - c1.getBlue();

		final int red = c1.getRed() + (int) (coeff * vred);
		final int green = c1.getGreen() + (int) (coeff * vgreen);
		final int blue = c1.getBlue() + (int) (coeff * vblue);

		return new Color(red, green, blue);

	}

	public final char getPolicy() {
		return policy;
	}

	@Override
	public Color toColor(ColorMapper mapper) {
		return color1.toColor(mapper);
	}

}

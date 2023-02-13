package net.sourceforge.plantuml.klimt.color;

import java.awt.Color;

public abstract class ColorMapper {

	private ColorMapper() {
	}

	public abstract Color fromColorSimple(HColorSimple simple);

	public final static ColorMapper IDENTITY = new ColorMapper() {
		@Override
		public Color fromColorSimple(HColorSimple simple) {
			return simple.getAwtColor();
		}
	};
	public final static ColorMapper DARK_MODE = new ColorMapper() {
		@Override
		public Color fromColorSimple(HColorSimple simple) {
			return ((HColorSimple) simple.darkSchemeTheme()).getAwtColor();
		}
	};
	public final static ColorMapper LIGTHNESS_INVERSE = new ColorMapper() {
		@Override
		public Color fromColorSimple(HColorSimple simple) {
			return ColorUtils.getReversed(simple.getAwtColor());
		}
	};
	public static final ColorMapper MONOCHROME = new ColorMapper() {
		@Override
		public Color fromColorSimple(HColorSimple simple) {
			return ColorUtils.getGrayScaleColor(simple.getAwtColor());
		}
	};
	public static final ColorMapper MONOCHROME_REVERSE = new ColorMapper() {
		@Override
		public Color fromColorSimple(HColorSimple simple) {
			return ColorUtils.getGrayScaleColorReverse(simple.getAwtColor());
		}
	};

	public static ColorMapper reverse(final ColorOrder order) {
		return new ColorMapper() {
			@Override
			public Color fromColorSimple(HColorSimple simple) {
				return order.getReverse(simple.getAwtColor());
			}
		};
	}

}

package net.sourceforge.plantuml.klimt.color;

class HColorScheme extends HColor {

	private final HColor colorForLight;
	private final HColor colorForDark;
	private final HColor colorForTransparent;

	public HColorScheme(HColor colorForLight, HColor colorForDark, HColor colorForTransparent) {
		this.colorForLight = colorForLight;
		this.colorForDark = colorForDark;
		this.colorForTransparent = colorForTransparent;
	}

	@Override
	public HColor getAppropriateColor(HColor back) {
		if (back.isTransparent()) {
			if (colorForTransparent != null)
				return colorForTransparent;

			return colorForLight.withDark(colorForDark);

		}
		if (back.isDark())
			return colorForDark;

		return colorForLight;
	}

}

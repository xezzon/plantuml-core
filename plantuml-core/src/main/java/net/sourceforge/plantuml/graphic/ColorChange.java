package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

class ColorChange implements FontChange {

	static private final Pattern2 colorPattern = MyPattern.cmpile(Splitter.fontColorPattern2);

	private final HColor color;

	ColorChange(String s) {
		final Matcher2 matcherColor = colorPattern.matcher(s);
		if (matcherColor.find() == false) {
			throw new IllegalArgumentException();
		}
		final String s1 = matcherColor.group(1);
		this.color = HColorSet.instance().getColorOrWhite(s1);
	}

	HColor getColor() {
		return color;
	}

	public FontConfiguration apply(FontConfiguration initial) {
		return initial.changeColor(color);
	}

}

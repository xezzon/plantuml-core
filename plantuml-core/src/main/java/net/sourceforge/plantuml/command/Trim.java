package net.sourceforge.plantuml.command;

import java.util.regex.Pattern;

import net.sourceforge.plantuml.text.StringLocated;

public enum Trim {
	BOTH, LEFT_ONLY, NONE;

	private String ltrim(final String tmp1) {
		return LTRIM.matcher(tmp1).replaceAll("");
	}

	public String trim(StringLocated s) {
		if (this == NONE)
			return s.getString();
		if (this == BOTH)
			return s.getTrimmed().getString();
		return ltrim(s.getString());
	}

	private static Pattern LTRIM = Pattern.compile("^\\s+");
	private static Pattern RTRIM = Pattern.compile("\\s+$");

}

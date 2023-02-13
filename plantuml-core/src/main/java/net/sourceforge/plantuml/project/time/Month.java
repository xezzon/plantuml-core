package net.sourceforge.plantuml.project.time;

import java.time.format.TextStyle;
import java.util.Locale;

import net.sourceforge.plantuml.StringUtils;

public enum Month {

	JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), //
	JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

	private final int daysPerMonth;

	private Month(int daysPerMonth) {
		this.daysPerMonth = daysPerMonth;
	}

	public String shortName(Locale locale) {
		if (locale == Locale.ENGLISH)
			return longName(locale).substring(0, 3);

		return StringUtils.capitalize(getJavaTimeMonth().getDisplayName(TextStyle.SHORT_STANDALONE, locale));
	}

	private java.time.Month getJavaTimeMonth() {
		return java.time.Month.valueOf(this.toString());
	}

	public String longName(Locale locale) {
		if (locale == Locale.ENGLISH)
			return StringUtils.capitalize(name());

		final java.time.Month javaTimeMonth = getJavaTimeMonth();
		final String v1 = javaTimeMonth.getDisplayName(TextStyle.FULL_STANDALONE, locale);
		final String v2 = javaTimeMonth.getDisplayName(TextStyle.FULL, locale);
		return StringUtils.capitalize(longest(v1, v2));
	}

	private String longest(String v1, String v2) {
		if (v1.length() > v2.length())
			return v1;
		return v2;
	}

	static public String getRegexString() {
		final StringBuilder sb = new StringBuilder();
		for (Month month : Month.values()) {
			if (sb.length() > 0) {
				sb.append("|");
			}
			sb.append(month.name().substring(0, 3) + "[a-z]*");
		}
		return sb.toString();
	}

	public static Month fromString(String value) {
		value = StringUtils.goUpperCase(value).substring(0, 3);
		for (Month m : Month.values()) {
			if (m.name().startsWith(value)) {
				return m;
			}
		}
		throw new IllegalArgumentException();
	}

	public final int getDaysPerMonth(int year) {
		if (this == FEBRUARY && year % 4 == 0) {
			return 29;
		}
		return daysPerMonth;
	}

	public Month next() {
		return Month.values()[(this.ordinal() + 1) % 12];
	}

	public int m() {
		return 3 + (ordinal() + 10) % 12;
	}

}

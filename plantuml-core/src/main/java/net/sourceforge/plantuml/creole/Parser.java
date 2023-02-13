package net.sourceforge.plantuml.creole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public static final String MONOSPACED = "monospaced";

	public static boolean isLatexStart(String line) {
		return line.equals("<latex>");
	}

	public static boolean isLatexEnd(String line) {
		return line.equals("</latex>");
	}

	public static boolean isCodeStart(String line) {
		return line.equals("<code>");
	}

	public static boolean isCodeEnd(String line) {
		return line.equals("</code>");
	}

	public static boolean isTreeStart(String line) {
		return line.startsWith("|_");
	}

	public static double getScale(String s, double def) {
		if (s == null)
			return def;

		final Pattern p = Pattern.compile("(?:scale=|\\*)([0-9.]+)");
		final Matcher m = p.matcher(s);
		if (m.find())
			return Double.parseDouble(m.group(1));

		return def;
	}

	public static String getColor(String s) {
		if (s == null)
			return null;

		final Pattern p = Pattern.compile("color[= :](#[0-9a-fA-F]{6}|\\w+)");
		final Matcher m = p.matcher(s);
		if (m.find())
			return m.group(1);

		return null;
	}

}

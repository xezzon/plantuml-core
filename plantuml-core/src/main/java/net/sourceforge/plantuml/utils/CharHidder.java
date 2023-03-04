// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.utils;

public class CharHidder {
	// ::remove file when __HAXE__

	public static String addTileAtBegin(String s) {
		return "~" + s;
	}

	public static String hide(String s) {
		// System.err.println("hide " + s);
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			if (c == '\\' && i + 1 < s.length() && s.charAt(i + 1) == '~') {
				result.append(hideChar('~'));
				i++;
			} else if (c == '~' && i + 1 < s.length()) {
				i++;
				final char c2 = s.charAt(i);
				if (isToBeHidden(c2)) {
					result.append(hideChar(c2));
				} else {
					result.append(c);
					result.append(c2);
				}

			} else {
				result.append(c);
			}
		}
		// System.err.println("---> " + result);
		return result.toString();
	}

	private static boolean isToBeHidden(final char c) {
		if (c == '_' || c == '-' || c == '\"' || c == '#' || c == ']' || c == '[' || c == '*' || c == '.' || c == '/'
				|| c == '<') {
			return true;
		}
		return false;
	}

	private static char hideChar(char c) {
		if (c > 255) {
			throw new IllegalArgumentException();
		}
		return (char) ('\uE000' + c);
	}

	private static char unhideChar(char c) {
		if (c >= '\uE000' && c <= '\uE0FF') {
			return (char) (c - '\uE000');
		}
		return c;
	}

	public static String unhide(String s) {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			result.append(unhideChar(c));
		}
		// System.err.println("unhide " + result);
		return result.toString();
	}

}

package net.sourceforge.plantuml.style.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CssVariables {

	private final Map<String, String> variables = new HashMap<>();

	private final Pattern learnPattern = Pattern.compile("^--([_\\w][-_\\w]+)[ :]+(.*?);?");
	private final Pattern retrieve = Pattern.compile("var\\(-*([_\\w][-_\\w]+)\\)");

	public void learn(String s) {
		final Matcher m = learnPattern.matcher(s);
		if (m.matches())
			variables.put(m.group(1), m.group(2));
	}

	public void learn(String var, String value) {
		if (var.startsWith("--"))
			var = var.substring(2);
		variables.put(var, value);
	}

	public String value(String v) {
		if (v.startsWith("var(")) {
			final Matcher m = retrieve.matcher(v);
			if (m.matches()) {
				final String varname = m.group(1);
				final String result = variables.get(varname);
				if (result != null)
					return result;
			}
		}
		return v;
	}

}

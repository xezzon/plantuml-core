// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class SvgAttributes {
	// ::remove file when __HAXE__

	private final Map<String, String> attributes = new TreeMap<String, String>();

	public SvgAttributes() {
	}

	private SvgAttributes(SvgAttributes other) {
		this.attributes.putAll(other.attributes);
	}

	public SvgAttributes(String args) {
		final Pattern2 p = MyPattern.cmpile("(\\w+)\\s*=\\s*([%g][^%g]*[%g]|(?:\\w+))");
		final Matcher2 m = p.matcher(args);
		while (m.find()) {
			attributes.put(m.group(1), StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(m.group(2)));
		}
	}

	public Map<String, String> attributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public SvgAttributes add(String key, String value) {
		final SvgAttributes result = new SvgAttributes(this);
		result.attributes.put(key, value);
		return result;
	}

	public SvgAttributes add(SvgAttributes toBeAdded) {
		final SvgAttributes result = new SvgAttributes(this);
		result.attributes.putAll(toBeAdded.attributes);
		return result;
	}

}

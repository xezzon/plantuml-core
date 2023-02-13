package net.sourceforge.plantuml.cucadiagram;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class Stereostyles {

	public static final Stereostyles NONE = new Stereostyles();

	private final Set<String> names = new LinkedHashSet<>();

	private Stereostyles() {
	}

	public boolean isEmpty() {
		return names.isEmpty();
	}

	public static Stereostyles build(String label) {
		final Stereostyles result = new Stereostyles();
		final Pattern2 p = MyPattern.cmpile("\\<{3}(.*?)\\>{3}");
		final Matcher2 m = p.matcher(label);
		while (m.find()) {
			result.names.add(m.group(1));
		}
		return result;
	}

	public Collection<String> getStyleNames() {
		return Collections.unmodifiableCollection(names);
	}

}

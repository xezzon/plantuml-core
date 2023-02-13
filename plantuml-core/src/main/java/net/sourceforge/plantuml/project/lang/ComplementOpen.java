package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementOpen implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("OPEN" + suffix, "(opene?d?(?: for \\[([^\\[\\]]+?)\\])?)");
	}

	public Failable<String> getMe(GanttDiagram project, RegexResult arg, String suffix) {
		final String value = arg.get("OPEN" + suffix, 0);
		final int x = value.indexOf('[');
		if (x > 0) {
			final int y = value.lastIndexOf(']');
			final String s = value.substring(x + 1, y);
			return Failable.ok(s);
		}
		return Failable.ok("");
	}

}

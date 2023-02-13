package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementNamed implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("COMPLEMENT" + suffix, "\\[([^\\[\\]]+)\\]");
	}

	public Failable<String> getMe(GanttDiagram system, RegexResult arg, String suffix) {
		final String name = arg.get("COMPLEMENT" + suffix, 0);
		return Failable.ok(name);
	}
}

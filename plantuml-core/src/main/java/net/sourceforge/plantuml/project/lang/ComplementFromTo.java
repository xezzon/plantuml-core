package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementFromTo implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("COMPLEMENT" + suffix, "from[%s]+\\[([^\\[\\]]+)\\][%s]+to[%s]+\\[([^\\[\\]]+)\\]");
	}

	public Failable<TwoNames> getMe(GanttDiagram system, RegexResult arg, String suffix) {
		final String name1 = arg.get("COMPLEMENT" + suffix, 0);
		final String name2 = arg.get("COMPLEMENT" + suffix, 1);
		return Failable.ok(new TwoNames(name1, name2));
	}
}

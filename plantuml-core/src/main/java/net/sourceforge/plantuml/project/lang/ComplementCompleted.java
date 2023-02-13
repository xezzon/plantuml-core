package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Completion;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementCompleted implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("COMPLEMENT" + suffix, "(\\d+).*completed?");
	}

	public Failable<Completion> getMe(GanttDiagram system, RegexResult arg, String suffix) {
		final String value = arg.get("COMPLEMENT" + suffix, 0);
		return Failable.ok(new Completion(Integer.parseInt(value)));
	}
}

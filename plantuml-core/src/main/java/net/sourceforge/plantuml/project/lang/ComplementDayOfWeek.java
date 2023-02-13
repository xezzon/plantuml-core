package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.time.DayOfWeek;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementDayOfWeek implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("COMPLEMENT" + suffix, "(" + DayOfWeek.getRegexString() + ")")); //
	}

	public Failable<DayOfWeek> getMe(GanttDiagram project, RegexResult arg, String suffix) {
		final String s = arg.get("COMPLEMENT" + suffix, 0);
		return Failable.ok(DayOfWeek.fromString(s));
	}

}

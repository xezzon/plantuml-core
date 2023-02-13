package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementSeveralDays implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("COMPLEMENT" + suffix, "(\\d+)[%s]+(day|week)s?" + //
						"(?:[%s]+and[%s]+(\\d+)[%s]+(day|week)s?)?" //
				)); //
	}

	public Failable<Load> getMe(GanttDiagram system, RegexResult arg, String suffix) {
		final String nb1 = arg.get("COMPLEMENT" + suffix, 0);
		final int factor1 = arg.get("COMPLEMENT" + suffix, 1).startsWith("w") ? system.daysInWeek() : 1;
		final int days1 = Integer.parseInt(nb1) * factor1;

		final String nb2 = arg.get("COMPLEMENT" + suffix, 2);
		int days2 = 0;
		if (nb2 != null) {
			final int factor2 = arg.get("COMPLEMENT" + suffix, 3).startsWith("w") ? system.daysInWeek() : 1;
			days2 = Integer.parseInt(nb2) * factor2;
		}

		return Failable.ok(Load.inWinks(days1 + days2));
	}

}

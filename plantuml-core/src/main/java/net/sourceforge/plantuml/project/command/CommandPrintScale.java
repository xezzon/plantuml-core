package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.PrintScale;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandPrintScale extends SingleLineCommand2<GanttDiagram> {

	public CommandPrintScale() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandPrintScale.class.getName(), RegexLeaf.start(), //
				new RegexOr(new RegexLeaf("projectscale"), //
						new RegexLeaf("ganttscale"), //
						new RegexLeaf("printscale")), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOr("SCALE", //
						new RegexLeaf("yearly"), //
						new RegexLeaf("quarterly"), //
						new RegexLeaf("monthly"), //
						new RegexLeaf("daily"), //
						new RegexLeaf("weekly")), //
				new RegexOptional(new RegexConcat( //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("DATE", "(with)"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("calendar"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("date"))), //
				new RegexOptional(new RegexConcat( //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("zoom"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("ZOOM", "([.\\d]+)"))), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		final String scaleString = arg.get("SCALE", 0);
		final PrintScale scale = PrintScale.fromString(scaleString);
		diagram.setPrintScale(scale);

		final String zoom = arg.get("ZOOM", 0);
		if (zoom != null)
			diagram.setFactorScale(Double.parseDouble(zoom));

		final String withCalendarDate = arg.get("DATE", 0);
		if (withCalendarDate != null)
			diagram.setWithCalendarDate(true);

		return CommandExecutionResult.ok();
	}

}

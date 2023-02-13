package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNolink extends SingleLineCommand2<ActivityDiagram3> {

	public CommandNolink() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNolink.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("nolink"), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		// diagram.setColorNextArrow(color);
		diagram.setLabelNextArrow(Display.getWithNewlines("NOLINK"));
		return CommandExecutionResult.ok();
	}

}

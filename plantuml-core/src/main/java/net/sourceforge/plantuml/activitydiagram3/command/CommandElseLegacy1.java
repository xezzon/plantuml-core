// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandElseLegacy1 extends SingleLineCommand2<ActivityDiagram3> {
    // ::remove folder when __HAXE__

	public CommandElseLegacy1() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandElseLegacy1.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("else"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("when"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WHEN", "(.*)"), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		// if (getSystem().getLastEntityConsulted() == null) {
		// return CommandExecutionResult.error("No if for this endif");
		// }
		final Display when = Display.getWithNewlines(arg.get("WHEN", 0));
		return diagram.else2(LinkRendering.none().withDisplay(when));
	}

}

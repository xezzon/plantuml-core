package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandRepeatWhile3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandRepeatWhile3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandRepeatWhile3.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("repeat"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("while"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOr(//
						new RegexConcat(new RegexLeaf("TEST3", "\\((.*?)\\)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("(is|equals?)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("WHEN3", "\\((.+?)\\)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("(not)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("OUT3", "\\((.+?)\\)")), //
						new RegexConcat(new RegexLeaf("TEST4", "\\((.*?)\\)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("(not)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("OUT4", "\\((.+?)\\)")), //
						new RegexConcat(new RegexLeaf("TEST2", "\\((.*?)\\)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("(is|equals?)"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("WHEN2", "\\((.+?)\\)") //
						), //
						new RegexOptional(new RegexLeaf("TEST1", "\\((.*)\\)")) //
				), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexConcat( //
						new RegexOr(//
								new RegexLeaf("->"), //
								new RegexLeaf("COLOR", CommandLinkElement.STYLE_COLORS_MULTIPLES)), //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexOr(//
								new RegexLeaf("LABEL", "(.*)"), //
								new RegexLeaf("")) //
				)), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) throws NoSuchColorException {
		final Display test = Display.getWithNewlines(arg.getLazzy("TEST", 0));
		final Display yes = Display.getWithNewlines(arg.getLazzy("WHEN", 0));
		final Display out = Display.getWithNewlines(arg.getLazzy("OUT", 0));

		final String colorString = arg.get("COLOR", 0);
		final Rainbow rainbow;
		if (colorString == null) {
			rainbow = Rainbow.none();
		} else {
			rainbow = Rainbow.build(diagram.getSkinParam(), colorString,
					diagram.getSkinParam().colorArrowSeparationSpace());
		}

		final Display linkLabel = Display.getWithNewlines(arg.get("LABEL", 0));
		return diagram.repeatWhile(test, yes, out, linkLabel, rainbow);
	}

}

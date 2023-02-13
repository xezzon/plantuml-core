package net.sourceforge.plantuml.activitydiagram3.command;

import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandArrowLong3 extends CommandMultilines2<ActivityDiagram3> {

	public CommandArrowLong3() {
		super(getRegexConcat(), MultilinesStrategy.REMOVE_STARTING_QUOTE, Trim.BOTH);
	}

	@Override
	public String getPatternEnd() {
		return "^(.*);$";
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandArrowLong3.class.getName(), RegexLeaf.start(), //
				new RegexOr(//
						new RegexLeaf("->"), //
						new RegexLeaf("COLOR", CommandLinkElement.STYLE_COLORS_MULTIPLES)), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LABEL", "(.*)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeNow(ActivityDiagram3 diagram, BlocLines lines) throws NoSuchColorException {
		lines = lines.removeEmptyColumns();
		final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		// final HtmlColor color = diagram.getSkinParam().getIHtmlColorSet().getColorIfValid(line0.get("COLOR", 0));
		// diagram.setColorNextArrow(Rainbow.fromColor(color));
		final String colorString = line0.get("COLOR", 0);
		if (colorString != null) {
			Rainbow rainbow = Rainbow.build(diagram.getSkinParam(), colorString, diagram.getSkinParam()
					.colorArrowSeparationSpace());
			diagram.setColorNextArrow(rainbow);
		}
		lines = lines.removeStartingAndEnding(line0.get("LABEL", 0), 1);
		diagram.setLabelNextArrow(lines.toDisplay());
		return CommandExecutionResult.ok();
	}

	private <CS extends CharSequence> void removeStarting(List<CS> lines, String data) {
		if (lines.size() == 0) {
			return;
		}
		lines.set(0, (CS) data);
	}

	private <CS extends CharSequence> void removeEnding(List<CS> lines) {
		if (lines.size() == 0) {
			return;
		}
		final int n = lines.size() - 1;
		final CharSequence s = lines.get(n);
		lines.set(n, (CS) s.subSequence(0, s.length() - 1));
	}

}

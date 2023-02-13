package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.activitydiagram3.ftile.BoxStyle;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandRepeat3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandRepeat3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandRepeat3.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				ColorParser.exp4(), //
				new RegexLeaf("repeat"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexLeaf("LABEL", ":(.*?)")), //
				new RegexOptional(new RegexLeaf("STYLE", CommandActivity3.endingGroup())), //
				// new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.BACK);
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String s = arg.get("COLOR", 0);
		final HColor color = s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s);
		final Display label = Display.getWithNewlines(arg.get("LABEL", 0));
		final BoxStyle boxStyle;
		final String styleString = arg.get("STYLE", 0);

		if (styleString == null)
			boxStyle = BoxStyle.PLAIN;
		else
			boxStyle = BoxStyle.fromString(styleString);

		Colors colors = color().getColor(arg, diagram.getSkinParam().getIHtmlColorSet());
		final String stereo = arg.get("STEREO", 0);
		if (stereo != null) {
			final Stereotype stereotype = Stereotype.build(stereo);
			colors = colors.applyStereotype(stereotype, diagram.getSkinParam(), ColorParam.activityBackground);
		}

		diagram.startRepeat(color, label, boxStyle, colors);

		return CommandExecutionResult.ok();
	}

}

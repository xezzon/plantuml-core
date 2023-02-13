package net.sourceforge.plantuml.activitydiagram3.command;

import java.util.regex.Matcher;

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
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandActivity3 extends SingleLineCommand2<ActivityDiagram3> {

	public static final String endingGroup() {
		return "(" //
				+ ";(?:[%s]*\\<\\<\\w+\\>\\>)?" //
				+ "|" //
				+ Matcher.quoteReplacement("\\\\") // that is simply \ character
				+ "|" //
				+ "(?<![/|<}\\]])[/<}]" // About /<}
				+ "|" //
				+ "(?<![/|}\\]])\\]" // About ]
				+ "|" //
				+ "(?<!\\</?\\w{1,5})(?<!\\<img[^>]{1,999})(?<!\\<[&$]\\w{1,999})(?<!\\>)\\>" // About >
				+ "|" //
				+ "(?<!\\|.{1,999})\\|" // About |
				+ ")";
	}

	private static final String endingGroupShort() {
		return "(" //
				+ ";(?:[%s]*\\<\\<\\w+\\>\\>)?" //
				+ "|" //
				+ Matcher.quoteReplacement("\\\\") // that is simply \ character
				+ "|" //
				+ "(?<![/|<}\\]])[/<}]" // About /<}
				+ "|" //
				+ "(?<![/|}\\]])\\]" // About ]
				+ "|" //
				+ "(?<!\\</?\\w{1,5})(?<!\\<img[^>]{1,999})(?<!\\<[&$]\\w{1,999})(?<!\\>)\\>" // About >
				+ "|" //
				+ "\\|" // About |
				+ ")";
	}

	public static void main(String[] args) {
		System.err.println(Matcher.quoteReplacement("\\\\"));
		System.err.println(Matcher.quoteReplacement("\\\\").equals("\\\\\\\\"));
	}

	public CommandActivity3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandActivity3.class.getName(), RegexLeaf.start(), //
				UrlBuilder.OPTIONAL, //
				color().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(":"), //
				new RegexLeaf("LABEL", "(.*?)"), //
				new RegexLeaf("STYLE", endingGroupShort()), //
				RegexLeaf.end());
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.BACK);
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {

		final Url url;
		if (arg.get("URL", 0) == null) {
			url = null;
		} else {
			final UrlBuilder urlBuilder = new UrlBuilder(diagram.getSkinParam().getValue("topurl"), UrlMode.STRICT);
			url = urlBuilder.getUrl(arg.get("URL", 0));
		}

		Colors colors = color().getColor(arg, diagram.getSkinParam().getIHtmlColorSet());
		final String stereo = arg.get("STEREO", 0);
		Stereotype stereotype = null;
		if (stereo != null) {
			stereotype = Stereotype.build(stereo);
			colors = colors.applyStereotype(stereotype, diagram.getSkinParam(), ColorParam.activityBackground);
		}
		final BoxStyle style = BoxStyle.fromString(arg.get("STYLE", 0));
		final Display display = Display.getWithNewlines2(arg.get("LABEL", 0));
		return diagram.addActivity(display, style, url, colors, stereotype);
	}

}

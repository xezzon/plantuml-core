package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandFooter extends SingleLineCommand2<TitledDiagram> {

	public static final CommandFooter ME = new CommandFooter();

	private CommandFooter() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandFooter.class.getName(), //
				RegexLeaf.start(), //
				new RegexOptional(new RegexLeaf("POSITION", "(left|right|center)")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("footer"), //
				new RegexOr( //
						new RegexConcat(RegexLeaf.spaceZeroOrMore(), new RegexLeaf(":"), RegexLeaf.spaceZeroOrMore()), //
						RegexLeaf.spaceOneOrMore()), //
				new RegexOr(//
						new RegexLeaf("LABEL1", "[%g](.*)[%g]"), //
						new RegexLeaf("LABEL2", "(.*[%pLN_.].*)")), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		final String align = arg.get("POSITION", 0);
		HorizontalAlignment ha = HorizontalAlignment.fromString(align, HorizontalAlignment.CENTER);
		if (align == null)
			ha = FontParam.FOOTER.getStyleDefinition(null).getMergedStyle(diagram.getCurrentStyleBuilder())
					.getHorizontalAlignment();

		final Display s = Display.getWithNewlines(arg.getLazzy("LABEL", 0));
		diagram.getFooter().putDisplay(s, ha);

		return CommandExecutionResult.ok();
	}
}

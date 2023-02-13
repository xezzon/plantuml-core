package net.sourceforge.plantuml.nwdiag;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandProperty extends SingleLineCommand2<NwDiagram> {

	public CommandProperty() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandProperty.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NAME", "(address|color|width|description)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("="), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\"?"), //
				new RegexLeaf("VALUE", "([^\"]*)"), //
				new RegexLeaf("\"?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(NwDiagram diagram, LineLocation location, RegexResult arg) {
		return diagram.setProperty(arg.get("NAME", 0), arg.get("VALUE", 0));
	}

}

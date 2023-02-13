package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.baraye.CucaDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNamespaceSeparator extends SingleLineCommand2<CucaDiagram> {

	public CommandNamespaceSeparator() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNamespaceSeparator.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("set"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOr( //
						new RegexLeaf("separator"), //
						new RegexLeaf("namespaceseparator")), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("SEPARATOR", "(\\S+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(CucaDiagram diagram, LineLocation location, RegexResult arg) {
		final String s = arg.get("SEPARATOR", 0);
		if ("none".equalsIgnoreCase(s)) {
			diagram.setNamespaceSeparator(null);
		} else {
			diagram.setNamespaceSeparator(s);
		}
		return CommandExecutionResult.ok();
	}
}

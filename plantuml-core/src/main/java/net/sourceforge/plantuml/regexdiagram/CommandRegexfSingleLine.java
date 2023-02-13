package net.sourceforge.plantuml.regexdiagram;

import java.util.Collections;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandRegexfSingleLine extends SingleLineCommand2<PSystemRegex> {

	public CommandRegexfSingleLine() {
		super(true, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandRegexfSingleLine.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LINE", "(.*)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(PSystemRegex diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String line = arg.get("LINE", 0);

		final StringLocated string = new StringLocated(line, location);
		return diagram.addBlocLines(BlocLines.from(Collections.singletonList(string)));
	}
}

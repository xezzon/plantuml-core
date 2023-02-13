package net.sourceforge.plantuml.ebnf;

import java.util.Collections;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandEBnfSingleLine extends SingleLineCommand2<PSystemEbnf> {

	public CommandEBnfSingleLine() {
		super(true, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEBnfSingleLine.class.getName(), RegexLeaf.start(), //

				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("\\(\\*"), //
								new RegexLeaf("COMMENTA", "(.*[^%s].*)"), //
								new RegexLeaf("\\*\\)"), //
								RegexLeaf.spaceZeroOrMore())), //

				new RegexLeaf("ID", "(\\w[-\\w]*)"), //

				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("\\(\\*"), //
								new RegexLeaf("COMMENTB", "(.*[^%s].*)"), //
								new RegexLeaf("\\*\\)"), //
								RegexLeaf.spaceZeroOrMore())), //

				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("EQUALS", "(=)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LINE", "(.*;)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(PSystemEbnf diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String id = arg.get("ID", 0);
		final String equals = arg.get("EQUALS", 0);
		final String line = arg.get("LINE", 0);
		final String full = id + equals + line;

		final String commentAbove = arg.get("COMMENTA", 0);
		final String commentBelow = arg.get("COMMENTB", 0);

		final StringLocated string = new StringLocated(full, location);
		return diagram.addBlocLines(BlocLines.from(Collections.singletonList(string)), commentAbove, commentBelow);
	}
}

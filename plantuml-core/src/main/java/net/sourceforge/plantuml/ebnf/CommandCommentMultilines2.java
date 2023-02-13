package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandCommentMultilines2 extends CommandMultilines2<PSystemEbnf> {

	public CommandCommentMultilines2() {
		super(getRegexConcat(), MultilinesStrategy.KEEP_STARTING_QUOTE, Trim.BOTH);
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCommentMultilines2.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\(\\*\\*"), //
				new RegexLeaf(".*"), //
				RegexLeaf.end());
	}

	@Override
	public String getPatternEnd() {
		return "^.*\\*\\*\\)[%s]*$";
	}

	@Override
	protected CommandExecutionResult executeNow(PSystemEbnf diagram, BlocLines lines) throws NoSuchColorException {
		final Display note = lines.removeFewChars(3).toDisplay();
		return diagram.addNote(note, null);
	}

}

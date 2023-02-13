package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandLayoutNewLine extends SingleLineCommand2<ClassDiagram> {

	public CommandLayoutNewLine() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {

		return RegexConcat.build(CommandLayoutNewLine.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("layout_new_line"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ClassDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.layoutNewLine();
		return CommandExecutionResult.ok();
	}
}

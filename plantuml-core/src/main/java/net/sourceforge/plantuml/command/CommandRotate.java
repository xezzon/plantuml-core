package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandRotate extends SingleLineCommand2<UmlDiagram> {

	public static final CommandRotate ME = new CommandRotate();

	private CommandRotate() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandRotate.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("rotate"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.setRotation(true);
		return CommandExecutionResult.ok();
	}

}

package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.Direction;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandMindMapDirection extends SingleLineCommand2<MindMapDiagram> {

	public CommandMindMapDirection() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMindMapDirection.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("[^*]*"), //
				new RegexLeaf("\\b"), //
				new RegexLeaf("DIRECTION", "(left|right|top|bottom)"), //
				new RegexLeaf("\\b"), //
				new RegexLeaf("[^*]*"), //
				new RegexLeaf("(side|direction)"), //
				new RegexLeaf("[^*]*"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(MindMapDiagram diagram, LineLocation location, RegexResult arg) {
		final String dir = arg.get("DIRECTION", 0);
		final Direction direction = Direction.lazzyValueOf(dir);
		diagram.setDefaultDirection(direction);
		return CommandExecutionResult.ok();
	}

}

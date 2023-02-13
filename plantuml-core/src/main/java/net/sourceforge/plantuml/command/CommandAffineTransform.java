package net.sourceforge.plantuml.command;

import java.util.Collections;

import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAffineTransform extends SingleLineCommand2<UmlDiagram> {

	public static final CommandAffineTransform ME = new CommandAffineTransform();

	private CommandAffineTransform() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAffineTransform.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("!transformation"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("ANIMATION", "([^{}]*)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, LineLocation location, RegexResult arg) {
		final CharSequence value = arg.get("ANIMATION", 0);
		return CommandExecutionResult.ok();
	}

}

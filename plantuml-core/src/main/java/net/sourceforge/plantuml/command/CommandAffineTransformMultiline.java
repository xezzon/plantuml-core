package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandAffineTransformMultiline extends CommandMultilines<TitledDiagram> {

	public static final CommandAffineTransformMultiline ME = new CommandAffineTransformMultiline();

	private CommandAffineTransformMultiline() {
		super("^!transformation[%s]+\\{[%s]*$");
	}

	@Override
	public String getPatternEnd() {
		return "^[%s]*!\\}[%s]*$";
	}

	public CommandExecutionResult execute(final TitledDiagram diagram, BlocLines lines) {
		// lines = lines.subExtract(1, 1);
		// diagram.setAnimation(lines);
		return CommandExecutionResult.error("Not yet implemented");
	}

}

package net.sourceforge.plantuml.ebnf;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemEbnfFactory extends PSystemCommandFactory {

	public PSystemEbnfFactory() {
		super(DiagramType.EBNF);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandComment());
		cmds.add(new CommandCommentMultilines2());
		cmds.add(new CommandCommentMultilines());
		cmds.add(new CommandEBnfSingleLine());
		cmds.add(new CommandEbnfMultilines());
		// cmds.add(new CommandNoteMultilines());
	}

	@Override
	public PSystemEbnf createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new PSystemEbnf(source);
	}

}

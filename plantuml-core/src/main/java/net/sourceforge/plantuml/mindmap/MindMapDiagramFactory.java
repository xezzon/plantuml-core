// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.mindmap;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandRankDir;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class MindMapDiagramFactory extends PSystemCommandFactory {

	public MindMapDiagramFactory() {
		super(DiagramType.MINDMAP);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		CommonCommands.addCommonCommands1(cmds);
		// cmds.add(new CommandMindMapTabulation());
		cmds.add(new CommandRankDir());
		cmds.add(new CommandMindMapOrgmode());
		cmds.add(new CommandMindMapOrgmodeMultiline());
		cmds.add(new CommandMindMapRoot());
		cmds.add(new CommandMindMapPlus());
		cmds.add(new CommandMindMapDirection());
	}

	@Override
	public MindMapDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new MindMapDiagram(source);
	}

}

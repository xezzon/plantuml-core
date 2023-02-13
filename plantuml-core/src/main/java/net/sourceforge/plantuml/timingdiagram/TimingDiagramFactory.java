package net.sourceforge.plantuml.timingdiagram;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommandFootboxIgnored;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.timingdiagram.command.CommandAnalog;
import net.sourceforge.plantuml.timingdiagram.command.CommandAtPlayer;
import net.sourceforge.plantuml.timingdiagram.command.CommandAtTime;
import net.sourceforge.plantuml.timingdiagram.command.CommandBinary;
import net.sourceforge.plantuml.timingdiagram.command.CommandChangeStateByPlayerCode;
import net.sourceforge.plantuml.timingdiagram.command.CommandChangeStateByTime;
import net.sourceforge.plantuml.timingdiagram.command.CommandClock;
import net.sourceforge.plantuml.timingdiagram.command.CommandConstraint;
import net.sourceforge.plantuml.timingdiagram.command.CommandDefineStateLong;
import net.sourceforge.plantuml.timingdiagram.command.CommandDefineStateShort;
import net.sourceforge.plantuml.timingdiagram.command.CommandHideTimeAxis;
import net.sourceforge.plantuml.timingdiagram.command.CommandHighlight;
import net.sourceforge.plantuml.timingdiagram.command.CommandModeCompact;
import net.sourceforge.plantuml.timingdiagram.command.CommandNote;
import net.sourceforge.plantuml.timingdiagram.command.CommandNoteLong;
import net.sourceforge.plantuml.timingdiagram.command.CommandPixelHeight;
import net.sourceforge.plantuml.timingdiagram.command.CommandRobustConcise;
import net.sourceforge.plantuml.timingdiagram.command.CommandScalePixel;
import net.sourceforge.plantuml.timingdiagram.command.CommandTicks;
import net.sourceforge.plantuml.timingdiagram.command.CommandTimeMessage;
import net.sourceforge.plantuml.timingdiagram.command.CommandUseDateFormat;

public class TimingDiagramFactory extends PSystemCommandFactory {

	@Override
	public TimingDiagram createEmptyDiagram(UmlSource source, Map<String, String> skinParam) {
		return new TimingDiagram(source);
	}

	@Override
	protected void initCommandsList(List<Command> cmds) {
		CommonCommands.addCommonCommands1(cmds);
		cmds.add(new CommandFootboxIgnored());
		cmds.add(new CommandRobustConcise());
		cmds.add(new CommandClock());
		cmds.add(new CommandAnalog());
		cmds.add(new CommandBinary());
		cmds.add(new CommandDefineStateShort());
		cmds.add(new CommandDefineStateLong());
		cmds.add(new CommandChangeStateByPlayerCode());
		cmds.add(new CommandChangeStateByTime());
		cmds.add(new CommandAtTime());
		cmds.add(new CommandAtPlayer());
		cmds.add(new CommandTimeMessage());
		cmds.add(new CommandNote());
		cmds.add(new CommandNoteLong());
		cmds.add(new CommandConstraint());
		cmds.add(new CommandScalePixel());
		cmds.add(new CommandHideTimeAxis());
		cmds.add(new CommandHighlight());
		cmds.add(new CommandModeCompact());
		cmds.add(new CommandTicks());
		cmds.add(new CommandPixelHeight());
		cmds.add(new CommandUseDateFormat());
	}

}

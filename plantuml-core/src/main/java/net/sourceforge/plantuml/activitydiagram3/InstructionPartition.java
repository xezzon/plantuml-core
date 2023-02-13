package net.sourceforge.plantuml.activitydiagram3;

import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;

public class InstructionPartition extends AbstractInstruction implements Instruction {

	private final InstructionList list = InstructionList.empty();
	private final Instruction parent;

	public InstructionPartition(Instruction parent, String partitionTitle) {
		this.parent = parent;
	}

	public Instruction getParent() {
		return parent;
	}

	public Set<Swimlane> getSwimlanes() {
		return list.getSwimlanes();
	}

	public Swimlane getSwimlaneIn() {
		return list.getSwimlaneIn();
	}

	public Swimlane getSwimlaneOut() {
		return list.getSwimlaneOut();
	}

	public Ftile createFtile(FtileFactory factory) {
		return list.createFtile(factory);
	}

	public CommandExecutionResult add(Instruction other) {
		return list.add(other);
	}

	public boolean kill() {
		return list.kill();
	}

	public LinkRendering getInLinkRendering() {
		return list.getInLinkRendering();
	}

	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		throw new UnsupportedOperationException();
	}

	public boolean containsBreak() {
		return list.containsBreak();
	}

}

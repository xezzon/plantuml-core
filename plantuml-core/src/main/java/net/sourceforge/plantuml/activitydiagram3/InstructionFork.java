package net.sourceforge.plantuml.activitydiagram3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.FtileWithNoteOpale;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;

public class InstructionFork extends WithNote implements Instruction {

	private final List<InstructionList> forks = new ArrayList<>();
	private final Instruction parent;
	private final LinkRendering inlinkRendering;
	private final ISkinParam skinParam;
	private final Swimlane swimlaneIn;
	private Swimlane swimlaneOut;
	private ForkStyle style = ForkStyle.FORK;
	private String label;
	boolean finished = false;

	@Override
	public boolean containsBreak() {
		for (InstructionList fork : forks) {
			if (fork.containsBreak()) {
				return true;
			}
		}
		return false;
	}

	public InstructionFork(Instruction parent, LinkRendering inlinkRendering, ISkinParam skinParam, Swimlane swimlane) {
		this.parent = parent;
		this.inlinkRendering = Objects.requireNonNull(inlinkRendering);
		this.skinParam = skinParam;
		this.swimlaneIn = swimlane;
		this.swimlaneOut = swimlane;
		this.forks.add(InstructionList.empty());
	}

	private InstructionList getLastList() {
		return forks.get(forks.size() - 1);
	}

	@Override
	public CommandExecutionResult add(Instruction ins) {
		return getLastList().add(ins);
	}


	private Rainbow getInLinkRenderingColor(ISkinParam skinParam) {
		Rainbow color;
		color = Rainbow.build(skinParam);
		return color;
	}

	@Override
	public Ftile createFtile(FtileFactory factory) {
		final List<Ftile> all = new ArrayList<>();
		for (InstructionList list : forks) {
			all.add(list.createFtile(factory));
		}
		Ftile result = factory.createParallel(all, style, label, swimlaneIn, swimlaneOut);
		if (getPositionedNotes().size() > 0) {
			result = FtileWithNoteOpale.create(result, getPositionedNotes(), skinParam, false, VerticalAlignment.CENTER);
		}
		return result;
	}

	public Instruction getParent() {
		return parent;
	}

	public void forkAgain(Swimlane swimlane) {
		this.swimlaneOut = swimlane;
		this.forks.add(InstructionList.empty());
	}

	@Override
	final public boolean kill() {
		return getLastList().kill();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return inlinkRendering;
	}

	@Override
	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		if (finished) {
			return super.addNote(note, position, type, colors, swimlaneNote);
		}
		if (getLastList().getLast() == null) {
			return getLastList().addNote(note, position, type, colors, swimlaneNote);
		}
		return getLastList().addNote(note, position, type, colors, swimlaneNote);
	}

	@Override
	public Set<Swimlane> getSwimlanes() {
		final Set<Swimlane> result = new HashSet<>(InstructionList.getSwimlanes2(forks));
		result.add(swimlaneIn);
		result.add(swimlaneOut);
		return result;
	}

	@Override
	public Swimlane getSwimlaneIn() {
		return swimlaneIn;
	}

	@Override
	public Swimlane getSwimlaneOut() {
		return swimlaneOut;
	}

	public void manageOutRendering(LinkRendering nextLinkRenderer, boolean endFork) {
		if (endFork) {
			this.finished = true;
		}
		if (nextLinkRenderer == null) {
			return;
		}
		getLastList().setOutRendering(nextLinkRenderer);
	}

	public void setStyle(ForkStyle style, String label, Swimlane swimlane) {
		this.style = style;
		this.label = label;
		this.swimlaneOut = swimlane;
	}

}

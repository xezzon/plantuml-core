// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.activitydiagram3;

import java.util.Objects;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileKilled;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.style.ISkinParam;

public class InstructionSpot extends MonoSwimable implements Instruction {

	private boolean killed = false;
	private final LinkRendering inlinkRendering;
	private final String spot;
	private final HColor color;

	@Override
	public boolean containsBreak() {
		return false;
	}

	public InstructionSpot(String spot, HColor color, LinkRendering inlinkRendering, Swimlane swimlane) {
		super(swimlane);
		this.spot = spot;
		this.inlinkRendering = Objects.requireNonNull(inlinkRendering);
		this.color = color;
	}

	@Override
	public Ftile createFtile(FtileFactory factory) {
		Ftile result = factory.spot(getSwimlaneIn(), spot, color);
		result = eventuallyAddNote(factory, result, result.getSwimlaneIn(), VerticalAlignment.CENTER);
		if (killed)
			return new FtileKilled(result);

		return result;
	}


	@Override
	public CommandExecutionResult add(Instruction other) {
		throw new UnsupportedOperationException();
	}

	@Override
	final public boolean kill() {
		this.killed = true;
		return true;
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return inlinkRendering;
	}

}

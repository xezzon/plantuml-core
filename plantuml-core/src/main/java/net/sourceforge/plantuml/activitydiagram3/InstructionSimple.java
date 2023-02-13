package net.sourceforge.plantuml.activitydiagram3;

import java.util.Collection;
import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.BoxStyle;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileKilled;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.url.Url;

public class InstructionSimple extends MonoSwimable implements Instruction {

	private boolean killed = false;
	private final Display label;
	private final Colors colors;
	private final LinkRendering inlinkRendering;
	private final BoxStyle style;
	private final Url url;
	private final Stereotype stereotype;

	@Override
	public boolean containsBreak() {
		return false;
	}

	public InstructionSimple(Display label, LinkRendering inlinkRendering, Swimlane swimlane, BoxStyle style, Url url,
			Colors colors, Stereotype stereotype) {
		super(swimlane);
		this.stereotype = stereotype;
		this.url = url;
		this.style = style;
		this.label = label;
		this.inlinkRendering = Objects.requireNonNull(inlinkRendering);
		this.colors = Objects.requireNonNull(colors);
	}


	@Override
	public Ftile createFtile(FtileFactory factory) {
		Ftile result = factory.activity(label, getSwimlaneIn(), style, colors, stereotype);
		if (url != null) {
			result = factory.addUrl(result, url);
		}
		result = eventuallyAddNote(factory, result, result.getSwimlaneIn(), VerticalAlignment.CENTER);
		if (killed) {
			return new FtileKilled(result);
		}
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

package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import java.util.Objects;

import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;

public class FtileDecorateOut extends FtileDecorate {

	final private LinkRendering linkRendering;

	public FtileDecorateOut(final Ftile ftile, final LinkRendering linkRendering) {
		super(ftile);
		this.linkRendering = Objects.requireNonNull(linkRendering);
	}

	public LinkRendering getOutLinkRendering() {
		return linkRendering;
	}

}

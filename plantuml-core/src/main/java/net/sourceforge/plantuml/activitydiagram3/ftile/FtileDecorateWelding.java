package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;

public class FtileDecorateWelding extends FtileDecorate {

	private final List<WeldingPoint> breaks;

	public FtileDecorateWelding(final Ftile ftile, final List<WeldingPoint> breaks) {
		super(ftile);
		this.breaks = new ArrayList<>(breaks);
	}

	@Override
	public List<WeldingPoint> getWeldingPoints() {
		return Collections.unmodifiableList(breaks);
	}

}

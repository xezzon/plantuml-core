package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileGoto extends FtileEmpty {

	private final String name;

	public FtileGoto(ISkinParam skinParam, Swimlane swimlane, String name) {
		super(skinParam, swimlane);
		this.name = name;
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		return calculateDimensionEmpty().withoutPointOut();
	}

	public String getName() {
		return name;
	}

}

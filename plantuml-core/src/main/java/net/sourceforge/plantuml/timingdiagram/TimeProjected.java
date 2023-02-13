package net.sourceforge.plantuml.timingdiagram;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.timingdiagram.graphic.IntricatedPoint;

public interface TimeProjected {

	public IntricatedPoint getTimeProjection(StringBounder stringBounder, TimeTick tick);

}

package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.color.ColorMapper;

public interface UDriver<SHAPE extends UShape, O> {
	public void draw(SHAPE shape, double x, double y, ColorMapper mapper, UParam param, O object);

}

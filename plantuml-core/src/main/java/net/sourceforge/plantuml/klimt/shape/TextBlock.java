// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.shape;

import net.atmp.InnerStrategy;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MagneticBorder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;

public interface TextBlock extends UDrawable, UShape {

	public XDimension2D calculateDimension(StringBounder stringBounder);

	// ::comment when __HAXE__
	public MinMax getMinMax(StringBounder stringBounder);

	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy);

	public MagneticBorder getMagneticBorder();

	public HColor getBackcolor();

}

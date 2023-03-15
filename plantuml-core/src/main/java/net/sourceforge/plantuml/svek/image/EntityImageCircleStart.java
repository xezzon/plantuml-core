// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.abel.Entity;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;

public class EntityImageCircleStart extends AbstractEntityImage {

	private static final int SIZE = 20;

	public StyleSignatureBasic getDefaultStyleDefinitionCircle() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.circle, SName.start);
	}

	public EntityImageCircleStart(Entity entity, ISkinParam skinParam) {
		super(entity, skinParam);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE, SIZE);
	}

	final public void drawU(UGraphic ug) {
		final UEllipse circle = UEllipse.build(SIZE, SIZE);

		final Style style = getDefaultStyleDefinitionCircle().getMergedStyle(getSkinParam().getCurrentStyleBuilder());
		HColor color = getEntity().getColors().getColor(ColorType.BACK);
		if (color == null)
			color = style.value(PName.LineColor).asColor(getSkinParam().getIHtmlColorSet());

		final double shadowing = style.value(PName.Shadowing).asDouble();

		circle.setDeltaShadow(shadowing);
		ug.apply(color.bg()).apply(HColors.none()).draw(circle);
	}

	public ShapeType getShapeType() {
		return ShapeType.CIRCLE;
	}

}

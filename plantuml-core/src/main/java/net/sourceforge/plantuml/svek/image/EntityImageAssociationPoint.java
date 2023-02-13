package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.CopyForegroundColorToBackgroundColor;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EntityImageAssociationPoint extends AbstractEntityImage {

	private static final int SIZE = 4;

	public EntityImageAssociationPoint(Entity entity, ISkinParam skinParam) {
		super(entity, skinParam);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE, SIZE);
	}

	private Style getStyle() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.classDiagram, SName.arrow)
				.withTOBECHANGED(getStereo()).getMergedStyle(getSkinParam().getCurrentStyleBuilder());
	}

	final public void drawU(UGraphic ug) {
		final UShape circle = new UEllipse(SIZE, SIZE);

		final HColor color = getStyle().value(PName.LineColor).asColor(getSkinParam().getIHtmlColorSet());
		ug.apply(color).apply(new CopyForegroundColorToBackgroundColor()).draw(circle);
	}

	public ShapeType getShapeType() {
		return ShapeType.CIRCLE;
	}

}

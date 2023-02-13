package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EntityImageGroup extends AbstractEntityImage {

	// final private TextBlock desc;
	// final private static int MARGIN = 10;

	public EntityImageGroup(Entity entity, ISkinParam skinParam) {
		super(entity, skinParam);
		// this.desc = Display.create(StringUtils.getWithNewlines(entity.getDisplay()),
		// FontConfiguration.create(
		// getFont(FontParam.ACTIVITY), HtmlColorUtils.BLACK),
		// HorizontalAlignment.CENTER);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(30, 30);
	}

	final public void drawU(UGraphic ug) {
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

}

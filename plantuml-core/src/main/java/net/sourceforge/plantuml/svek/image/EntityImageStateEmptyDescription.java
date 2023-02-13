package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EntityImageStateEmptyDescription extends EntityImageStateCommon {

	final private static int MIN_WIDTH = 50;
	final private static int MIN_HEIGHT = 40;

	public EntityImageStateEmptyDescription(Entity entity, ISkinParam skinParam) {
		super(entity, skinParam);

	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D dim = title.calculateDimension(stringBounder);
		final XDimension2D result = dim.delta(MARGIN * 2);
		return result.atLeast(MIN_WIDTH, MIN_HEIGHT);
	}

	final public void drawU(UGraphic ug) {
		if (url != null)
			ug.startUrl(url);

		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dimTotal = calculateDimension(stringBounder);
		final XDimension2D dimDesc = title.calculateDimension(stringBounder);

		final UStroke stroke = getStyleState().getStroke(lineConfig.getColors());

		ug = applyColor(ug);
		ug = ug.apply(stroke);

		ug.draw(getShape(dimTotal));

		final double xDesc = (dimTotal.getWidth() - dimDesc.getWidth()) / 2;
		final double yDesc = (dimTotal.getHeight() - dimDesc.getHeight()) / 2;
		title.drawU(ug.apply(new UTranslate(xDesc, yDesc)));

		if (url != null)
			ug.closeUrl();

	}

}

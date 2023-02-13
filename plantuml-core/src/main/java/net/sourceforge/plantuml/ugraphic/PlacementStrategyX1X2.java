package net.sourceforge.plantuml.ugraphic;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class PlacementStrategyX1X2 extends AbstractPlacementStrategy {

	public PlacementStrategyX1X2(StringBounder stringBounder) {
		super(stringBounder);
	}

	public Map<TextBlock, XPoint2D> getPositions(double width, double height) {
		final double usedWidth = getSumWidth();
		//double maxHeight = getMaxHeight();

		final double space = (width - usedWidth) / (getDimensions().size() + 1);
		final Map<TextBlock, XPoint2D> result = new LinkedHashMap<>();
		double x = space;
		for (Map.Entry<TextBlock, XDimension2D> ent : getDimensions().entrySet()) {
			final double y = (height - ent.getValue().getHeight()) / 2;
			result.put(ent.getKey(), new XPoint2D(x, y));
			x += ent.getValue().getWidth() + space;
		}
		return result;
	}

}

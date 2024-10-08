// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.geom;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public class PlacementStrategyY1Y2Center extends AbstractPlacementStrategy {

	public PlacementStrategyY1Y2Center(StringBounder stringBounder) {
		super(stringBounder);
	}

	public Map<TextBlock, XPoint2D> getPositions(double width, double height) {
		final double usedHeight = getSumHeight();
		// double maxWidth = getMaxWidth();

		final double space = (height - usedHeight) / (getDimensions().size() + 1);
		final Map<TextBlock, XPoint2D> result = new LinkedHashMap<>();
		double y = space;
		for (Map.Entry<TextBlock, XDimension2D> ent : getDimensions().entrySet()) {
			final double x = (width - ent.getValue().getWidth()) / 2;
			result.put(ent.getKey(), new XPoint2D(x, y));
			y += ent.getValue().getHeight() + space;
		}
		return result;
	}

}

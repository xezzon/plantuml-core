package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.color.HColor;

public class MiddleFactoryCircleCircled implements MiddleFactory {

	private final MiddleCircleCircledMode mode;
	private final HColor backColor;
	private final HColor diagramBackColor;

	public MiddleFactoryCircleCircled(MiddleCircleCircledMode mode, HColor backColor, HColor diagramBackColor) {
		this.mode = mode;
		this.backColor = backColor;
		this.diagramBackColor = diagramBackColor;
	}

	public UDrawable createUDrawable(double angle) {
		return new MiddleCircleCircled(angle, mode, backColor, diagramBackColor);
	}

}

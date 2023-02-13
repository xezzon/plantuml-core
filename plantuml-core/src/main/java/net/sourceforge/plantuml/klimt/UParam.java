package net.sourceforge.plantuml.klimt;

import net.sourceforge.plantuml.klimt.color.HColor;

public interface UParam {

	public HColor getColor();

	public HColor getBackcolor();

	public UStroke getStroke();

	public boolean isHidden();

	public UPattern getPattern();
}

package net.sourceforge.plantuml.style;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public interface Value {

	public String asString();

	public HColor asColor(HColorSet set);

	public int asInt(boolean minusOneIfError);

	public double asDouble();

	public boolean asBoolean();

	public int asFontStyle();

	public HorizontalAlignment asHorizontalAlignment();

	public int getPriority();

}

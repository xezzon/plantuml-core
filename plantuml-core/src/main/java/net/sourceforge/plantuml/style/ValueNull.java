package net.sourceforge.plantuml.style;

import java.awt.Font;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public class ValueNull extends ValueAbstract implements Value {

	public static final Value NULL = new ValueNull();

	private ValueNull() {
	}

	@Override
	public int asInt(boolean minusOneIfError) {
		return 0;
	}

	@Override
	public double asDouble() {
		return 0;
	}

	@Override
	public boolean asBoolean() {
		return false;
	}

	@Override
	public String asString() {
		return "";
	}

	@Override
	public int asFontStyle() {
		return Font.PLAIN;
	}

	@Override
	public HColor asColor(HColorSet set) {
		return HColors.BLACK;
	}

	@Override
	public HorizontalAlignment asHorizontalAlignment() {
		return HorizontalAlignment.LEFT;
	}

}

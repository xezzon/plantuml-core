package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontParam;

public class SkinParamColors extends SkinParamDelegator {

	public final Colors getColors() {
		return colors;
	}

	final private Colors colors;

	public SkinParamColors(ISkinParam skinParam, Colors colors) {
		super(skinParam);
		this.colors = colors;
	}

	@Override
	public String toString() {
		return "SkinParamColors::" + colors;
	}

	@Override
	public boolean shadowing(Stereotype stereotype) {
		if (colors.getShadowing() == null) {
			return super.shadowing(stereotype);
		}
		return colors.getShadowing();
	}

	@Override
	public HColor getFontHtmlColor(Stereotype stereotype, FontParam... param) {
		final HColor value = colors.getColor(ColorType.TEXT);
		if (value == null) {
			return super.getFontHtmlColor(stereotype, param);
		}
		return value;
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		final ColorType type = param.getColorType();
		if (type == null) {
			return super.getHtmlColor(param, stereotype, clickable);
		}
		final HColor value = colors.getColor(type);
		if (value != null) {
			return value;
		}
		assert value == null;
		return super.getHtmlColor(param, stereotype, clickable);
	}

}

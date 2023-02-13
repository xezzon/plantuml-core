package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.skin.rose.Rose;

public class SkinParamUtils {

	private static final Rose rose = new Rose();

	public static UFont getFont(ISkinParam skinParam, FontParam fontParam, Stereotype stereo) {
		return skinParam.getFont(stereo, false, fontParam);
	}

	public static HColor getFontColor(ISkinParam skinParam, FontParam fontParam, Stereotype stereo) {
		return skinParam.getFontHtmlColor(stereo, fontParam);
	}

	public static HColor getColor(ISkinParam skinParam, Stereotype stereo, ColorParam... colorParam) {
		return rose.getHtmlColor(skinParam, stereo, colorParam);
	}
	
}

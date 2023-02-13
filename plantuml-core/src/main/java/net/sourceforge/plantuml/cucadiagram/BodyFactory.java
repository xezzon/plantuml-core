package net.sourceforge.plantuml.cucadiagram;

import java.util.List;
import java.util.Set;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.style.Style;

public class BodyFactory {

	public final static boolean BODY3 = false;

	public static Bodier createLeaf(LeafType type, Set<VisibilityModifier> hides) {
		if (type.isLikeClass() || type == LeafType.OBJECT)
			return new BodierLikeClassOrObject(type, hides);

		return new BodierSimple();
	}

	public static Bodier createGroup(Set<VisibilityModifier> hides) {
		return new BodierSimple();
	}

	public static TextBlock create1(HorizontalAlignment align, List<CharSequence> rawBody, ISkinParam skinParam,
			Stereotype stereotype, Entity entity, Style style) {
		return new BodyEnhanced1(align, rawBody, skinParam, entity, style);
	}

	public static TextBlock create2(HorizontalAlignment align, Display display, ISkinParam skinParam,
			Stereotype stereotype, Entity entity, Style style) {
		return new BodyEnhanced1(align, display, skinParam, entity, style);
	}

	public static TextBlock create3(Display rawBody, ISkinSimple skinParam, HorizontalAlignment align,
			FontConfiguration titleConfig, LineBreakStrategy lineBreakStrategy, Style style) {
		return new BodyEnhanced2(rawBody, skinParam, align, titleConfig, lineBreakStrategy, style);
	}

}

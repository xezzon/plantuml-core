package net.sourceforge.plantuml.graphic;

import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.cucadiagram.LinkStyle;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class HtmlColorAndStyle {

	private final HColor arrowHeadColor;
	private final HColor arrowColor;
	private final LinkStyle style;

	@Override
	public String toString() {
		return arrowColor + " " + style;
	}

	public HtmlColorAndStyle(HColor color, HColor arrowHeadColor) {
		this(color, LinkStyle.NORMAL(), arrowHeadColor);
	}

	public HtmlColorAndStyle(HColor arrowColor, LinkStyle style, HColor arrowHeadColor) {
		this.arrowColor = Objects.requireNonNull(arrowColor);
		this.arrowHeadColor = arrowHeadColor == null ? arrowColor : arrowHeadColor;
		this.style = style;
	}

	public HColor getArrowColor() {
		return arrowColor;
	}

	public HColor getArrowHeadColor() {
		return arrowHeadColor;
	}

	public LinkStyle getStyle() {
		return style;
	}

	static final public StyleSignatureBasic getDefaultStyleDefinitionArrow() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.arrow);
	}

	public static HtmlColorAndStyle build(ISkinParam skinParam, String definition) throws NoSuchColorException {

		final Style style = getDefaultStyleDefinitionArrow().getMergedStyle(skinParam.getCurrentStyleBuilder());
		HColor arrowColor = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		final HColor arrowHeadColor = null;

		LinkStyle linkStyle = LinkStyle.NORMAL();
		final HColorSet set = skinParam.getIHtmlColorSet();
		for (String s : definition.split(",")) {
			final LinkStyle tmpStyle = LinkStyle.fromString1(s);
			if (tmpStyle.isNormal() == false) {
				linkStyle = tmpStyle;
				continue;
			}
			final HColor tmpColor = s == null ? null : set.getColor(s);
			if (tmpColor != null)
				arrowColor = tmpColor;

		}
		return new HtmlColorAndStyle(arrowColor, linkStyle, arrowHeadColor);
	}

}

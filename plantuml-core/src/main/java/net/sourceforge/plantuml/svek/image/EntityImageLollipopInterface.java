package net.sourceforge.plantuml.svek.image;

import java.util.EnumMap;
import java.util.Map;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UGroupType;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignature;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

public class EntityImageLollipopInterface extends AbstractEntityImage {

	private static final int SIZE = 10;

	private final TextBlock desc;
	private final SName sname;
	private final Url url;

	public StyleSignature getSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, sname, SName.circle).withTOBECHANGED(getStereo());
	}

	private UStroke getUStroke() {
		return new UStroke(1.5);
	}

	public EntityImageLollipopInterface(Entity entity, ISkinParam skinParam, SName sname) {
		super(entity, skinParam);
		this.sname = sname;

		final FontConfiguration fc = FontConfiguration.create(getSkinParam(),
				getSignature().getMergedStyle(skinParam.getCurrentStyleBuilder()));

		this.desc = entity.getDisplay().create(fc, HorizontalAlignment.CENTER, skinParam);
		this.url = entity.getUrl99();

	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE, SIZE);
	}

	final public void drawU(UGraphic ug) {

		final Style style = getSignature().getMergedStyle(getSkinParam().getCurrentStyleBuilder());
		final HColor backgroundColor = style.value(PName.BackGroundColor).asColor(getSkinParam().getIHtmlColorSet());
		final HColor borderColor = style.value(PName.LineColor).asColor(getSkinParam().getIHtmlColorSet());
		final double shadow = style.value(PName.Shadowing).asDouble();

		final UEllipse circle;
		if (getEntity().getLeafType() == LeafType.LOLLIPOP_HALF) {
			circle = new UEllipse(SIZE, SIZE, angle - 90, 180);
		} else {
			circle = new UEllipse(SIZE, SIZE);
			if (getSkinParam().shadowing(getEntity().getStereotype()))
				circle.setDeltaShadow(shadow);
		}

		ug = ug.apply(backgroundColor.bg()).apply(borderColor);
		if (url != null)
			ug.startUrl(url);

		final Map<UGroupType, String> typeIDent = new EnumMap<>(UGroupType.class);
		typeIDent.put(UGroupType.CLASS, "elem " + getEntity().getName() + " selected");
		typeIDent.put(UGroupType.ID, "elem_" + getEntity().getName());
		ug.startGroup(typeIDent);
		ug.apply(getUStroke()).draw(circle);
		ug.closeGroup();

		final XDimension2D dimDesc = desc.calculateDimension(ug.getStringBounder());
		final double widthDesc = dimDesc.getWidth();

		final double x = SIZE / 2 - widthDesc / 2;
		final double y = SIZE;
		desc.drawU(ug.apply(new UTranslate(x, y)));
		if (url != null)
			ug.closeUrl();

	}

	public ShapeType getShapeType() {
		return ShapeType.CIRCLE;
	}

	private double angle;

	public void addImpact(double angle) {
		this.angle = 180 - (angle * 180 / Math.PI);
	}

}

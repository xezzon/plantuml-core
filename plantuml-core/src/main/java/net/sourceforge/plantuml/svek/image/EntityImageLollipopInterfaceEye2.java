package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.BodyFactory;
import net.sourceforge.plantuml.cucadiagram.EntityPortion;
import net.sourceforge.plantuml.cucadiagram.PortionShower;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.text.Guillemet;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

public class EntityImageLollipopInterfaceEye2 extends AbstractEntityImage {

	public static final double SIZE = 14;
	private final TextBlock desc;
	private final TextBlock stereo;
	private final SymbolContext ctx;
	final private Url url;

	public EntityImageLollipopInterfaceEye2(Entity entity, ISkinParam skinParam, PortionShower portionShower) {
		super(entity, skinParam);
		final Stereotype stereotype = entity.getStereotype();

//		final USymbol symbol = Objects.requireNonNull(
//				entity.getUSymbol() == null ? skinParam.componentStyle().toUSymbol() : entity.getUSymbol());

		// final FontParam fontParam = symbol.getFontParam();
		final FontParam fontParam = FontParam.COMPONENT;

		this.desc = BodyFactory.create2(skinParam.getDefaultTextAlignment(HorizontalAlignment.CENTER),
				entity.getDisplay(), skinParam, stereotype, entity, getStyle(fontParam));

		this.url = entity.getUrl99();

		HColor backcolor = getEntity().getColors().getColor(ColorType.BACK);
//		if (backcolor == null)
//			backcolor = SkinParamUtils.getColor(getSkinParam(), getStereo(), symbol.getColorParamBack());

		final HColor forecolor = HColors.BLACK;
		// final HColor forecolor = SkinParamUtils.getColor(getSkinParam(), getStereo(),
		// symbol.getColorParamBorder());
		this.ctx = new SymbolContext(backcolor, forecolor).withStroke(new UStroke(1.5))
				.withShadow(getSkinParam().shadowing(getEntity().getStereotype()) ? 3 : 0);

		if (stereotype != null && stereotype.getLabel(Guillemet.DOUBLE_COMPARATOR) != null
				&& portionShower.showPortion(EntityPortion.STEREOTYPE, entity)) {
//			final FontParam fontParam = symbol.getFontParamStereotype();
//			stereo = Display.getWithNewlines(stereotype.getLabel(getSkinParam().guillemet())).create(
//					FontConfiguration.create(getSkinParam(), fontParam, stereotype), HorizontalAlignment.CENTER,
//					skinParam);
			stereo = TextBlockUtils.empty(0, 0);
		} else {
			stereo = TextBlockUtils.empty(0, 0);
		}

	}

	private Style getStyle(FontParam fontParam) {
		return fontParam.getStyleDefinition(SName.componentDiagram)
				.getMergedStyle(getSkinParam().getCurrentStyleBuilder());
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(SIZE, SIZE);
	}

	final public void drawU(UGraphic ug) {
		if (url != null) {
			ug.startUrl(url);
		}
		final UEllipse circle = new UEllipse(SIZE, SIZE);
		if (getSkinParam().shadowing(getEntity().getStereotype())) {
			circle.setDeltaShadow(4);
		}
		ctx.apply(ug).draw(circle);

		final XDimension2D dimDesc = desc.calculateDimension(ug.getStringBounder());
		final double x1 = SIZE / 2 - dimDesc.getWidth() / 2;
		final double y1 = SIZE * 1.4;
		desc.drawU(ug.apply(new UTranslate(x1, y1)));

		final XDimension2D dimStereo = stereo.calculateDimension(ug.getStringBounder());
		final double x2 = SIZE / 2 - dimStereo.getWidth() / 2;
		final double y2 = -dimStereo.getHeight();
		stereo.drawU(ug.apply(new UTranslate(x2, y2)));

		if (url != null) {
			ug.closeUrl();
		}
	}

	public ShapeType getShapeType() {
		return ShapeType.CIRCLE;
	}

}

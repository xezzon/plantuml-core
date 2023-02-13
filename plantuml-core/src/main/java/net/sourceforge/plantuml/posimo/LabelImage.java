package net.sourceforge.plantuml.posimo;

import java.util.Objects;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.cucadiagram.Link;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class LabelImage {

	// private final Entity entity;
	final private ISkinParam param;
	final private Rose rose;
	final private TextBlock name;

	public LabelImage(Link link, Rose rose, ISkinParam param) {
		Objects.requireNonNull(link);
		// this.entity = entity;
		this.param = param;
		this.rose = rose;
//		this.name = link.getLabel().create(
//				FontConfiguration.create(param.getFont(FontParam.CLASS, null, false), HtmlColorUtils.BLACK,
//						param.getHyperlinkColor(), param.useUnderlineForHyperlink()), HorizontalAlignment.CENTER,
//				new SpriteContainerEmpty());
		throw new UnsupportedOperationException();
	}

	public XDimension2D getDimension(StringBounder stringBounder) {
		final XDimension2D dim = name.calculateDimension(stringBounder);
		return dim;
		// return Dimension2DDouble.delta(dim, 2 * margin);
	}

	public void drawU(UGraphic ug, double x, double y) {
		// final Dimension2D dim = getDimension(ug.getStringBounder());
		// ug.getParam().setBackcolor(rose.getHtmlColor(param,
		// ColorParam.classBackground).getColor());
		// ug.getParam().setColor(rose.getHtmlColor(param,
		// ColorParam.classBorder).getColor());
		// ug.draw(x, y, new URectangle(dim.getWidth(), dim.getHeight()));
		name.drawU(ug.apply(new UTranslate(x, y)));
	}
}

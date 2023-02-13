package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.style.ClockwiseTopRightBottomLeft;
import net.sourceforge.plantuml.svek.Ports;
import net.sourceforge.plantuml.svek.WithPorts;
import net.sourceforge.plantuml.ugraphic.UEmpty;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class TextBlockMarged extends AbstractTextBlock implements TextBlock, WithPorts {

	private final TextBlock textBlock;
	private final double top;
	private final double right;
	private final double bottom;
	private final double left;

	TextBlockMarged(TextBlock textBlock, double top, double right, double bottom, double left) {
		this.textBlock = textBlock;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	TextBlockMarged(TextBlock textBlock, ClockwiseTopRightBottomLeft margins) {
		this.textBlock = textBlock;
		this.top = margins.getTop();
		this.right = margins.getRight();
		this.bottom = margins.getBottom();
		this.left = margins.getLeft();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D dim = textBlock.calculateDimension(stringBounder);
		return dim.delta(left + right, top + bottom);
	}

	public void drawU(UGraphic ug) {
		// ug.apply(HColorUtils.BLUE).draw(new
		// URectangle(calculateDimension(ug.getStringBounder())));
		final XDimension2D dim = calculateDimension(ug.getStringBounder());
		if (dim.getWidth() > 0) {
			ug.draw(new UEmpty(dim));
			final UTranslate translate = new UTranslate(left, top);
			textBlock.drawU(ug.apply(translate));
		}
	}

	@Override
	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		final XRectangle2D parent = textBlock.getInnerPosition(member, stringBounder, strategy);
		if (parent == null) {
			return null;
		}
		final UTranslate translate = new UTranslate(left, top);
		return translate.apply(parent);
	}

	@Override
	public Ports getPorts(StringBounder stringBounder) {
		return ((WithPorts) textBlock).getPorts(stringBounder).translateY(top);
	}

}

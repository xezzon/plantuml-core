package net.sourceforge.plantuml.creole;

import java.util.List;
import java.util.Objects;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.creole.atom.Atom;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.InnerStrategy;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.svek.Ports;
import net.sourceforge.plantuml.svek.WithPorts;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UGraphicStencil;

final public class SheetBlock2 extends AbstractTextBlock implements TextBlock, Atom, WithPorts {

	private final SheetBlock1 block;
	private final UStroke defaultStroke;
	private final Stencil stencil;

	public SheetBlock2 enlargeMe(final double delta1, final double delta2) {
		final Stencil newStencil = new Stencil() {

			public double getStartingX(StringBounder stringBounder, double y) {
				return stencil.getStartingX(stringBounder, y) - delta1;
			}

			public double getEndingX(StringBounder stringBounder, double y) {
				return stencil.getEndingX(stringBounder, y) + delta2;
			}
		};
		return new SheetBlock2(block, newStencil, defaultStroke);
	}

	public SheetBlock2(SheetBlock1 block, Stencil stencil, UStroke defaultStroke) {
		this.block = block;
		this.stencil = Objects.requireNonNull(stencil);
		this.defaultStroke = defaultStroke;
	}

	private HorizontalAlignment getHorizontalAlignment() {
		return block.getHorizontalAlignment();
	}

	@Override
	public String toString() {
		return block.toString();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return block.calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug) {
		if (stencil != null)
			ug = UGraphicStencil.create(ug, stencil, defaultStroke);

		if (getHorizontalAlignment() == HorizontalAlignment.CENTER && block.getMinimumWidth() > 0) {
			final double width = calculateDimension(ug.getStringBounder()).getWidth();
			final double dx = (block.getMinimumWidth() - width) / 2;
			ug = ug.apply(UTranslate.dx(dx));
		}
		block.drawU(ug);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

	@Override
	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		return block.getInnerPosition(member, stringBounder, strategy);
	}

	@Override
	public Ports getPorts(StringBounder stringBounder) {
		return new Ports();
	}

	@Override
	public List<Neutron> getNeutrons() {
		throw new UnsupportedOperationException();
	}

}

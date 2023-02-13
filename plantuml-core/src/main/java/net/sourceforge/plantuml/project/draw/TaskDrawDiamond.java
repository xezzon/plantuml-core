package net.sourceforge.plantuml.project.draw;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.SpriteContainerEmpty;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UPolygon;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.project.LabelStrategy;
import net.sourceforge.plantuml.project.ToTaskDraw;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.lang.CenterBorderColor;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.timescale.TimeScale;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.style.ClockwiseTopRightBottomLeft;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TaskDrawDiamond extends AbstractTaskDraw {

	public TaskDrawDiamond(TimeScale timeScale, Real y, String prettyDisplay, Day start, ISkinParam skinParam,
			Task task, ToTaskDraw toTaskDraw, StyleBuilder styleBuilder) {
		super(timeScale, y, prettyDisplay, start, skinParam, task, toTaskDraw, styleBuilder);
	}

	@Override
	StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.ganttDiagram, SName.milestone);
	}

	@Override
	public double getHeightMax(StringBounder stringBounder) {
		return getFullHeightTask(stringBounder);
	}

	@Override
	protected double getShapeHeight(StringBounder stringBounder) {
//		final Style style = getStyle();
//		final ClockwiseTopRightBottomLeft padding = style.getPadding();
		int result = (int) getFontConfiguration().getFont().getSize2D();
		if (result % 2 == 1)
			result--;
		return result;

	}

	@Override
	final public void drawTitle(UGraphic ug, LabelStrategy labelStrategy, double colTitles, double colBars) {

		final Style style = getStyle();
		final ClockwiseTopRightBottomLeft margin = style.getMargin();
		final ClockwiseTopRightBottomLeft padding = style.getPadding();

		final TextBlock title = getTitle();

		ug = ug.apply(UTranslate.dy(margin.getTop()));

		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D titleDim = title.calculateDimension(stringBounder);
		final double h = (getShapeHeight(stringBounder) - titleDim.getHeight()) / 2;

		final double x;
		if (labelStrategy.titleInFirstColumn()) {
			if (labelStrategy.rightAligned())
				x = colTitles - titleDim.getWidth() - margin.getRight();
			else

				x = margin.getLeft();
		} else if (labelStrategy.titleInLastColumn()) {
			x = colBars + margin.getLeft();
		} else {
			final double x1 = timeScale.getStartingPosition(start);
			final double x2 = timeScale.getEndingPosition(start);
			final double width = getShapeHeight(ug.getStringBounder());
			final double delta = x2 - x1 - width;
			x = x2 - delta / 2 + padding.getLeft();
		}
		title.drawU(ug.apply(new UTranslate(x, h)));
	}

	@Override
	protected TextBlock getTitle() {
		return Display.getWithNewlines(prettyDisplay).create(getFontConfiguration(), HorizontalAlignment.LEFT,
				new SpriteContainerEmpty());
	}

	@Override
	public void drawU(UGraphic ug) {

		final Style style = getStyle();
		final ClockwiseTopRightBottomLeft margin = style.getMargin();
		ug = ug.apply(UTranslate.dy(margin.getTop()));

		final double x1 = timeScale.getStartingPosition(start);
		final double x2 = timeScale.getEndingPosition(start);
		final double width = getShapeHeight(ug.getStringBounder());
		final double delta = x2 - x1 - width;

		if (url != null)
			ug.startUrl(url);

		drawShape(applyColors(ug).apply(UTranslate.dx(x1 + delta / 2)));

		if (url != null)
			ug.closeUrl();
	}

	private UGraphic applyColors(UGraphic ug) {
		final CenterBorderColor col = this.getColors();
		if (col != null && col.isOk()) {
			return col.apply(ug);
		}
		return ug.apply(getLineColor()).apply(getBackgroundColor().bg());
	}

	private void drawShape(UGraphic ug) {
		ug.draw(getDiamond(ug.getStringBounder()));
	}

	@Override
	public FingerPrint getFingerPrintNote(StringBounder stringBounder) {
		return null;
	}

	@Override
	public FingerPrint getFingerPrint(StringBounder stringBounder) {
		final double h = getFullHeightTask(stringBounder);
		final double startPos = timeScale.getStartingPosition(start);
		return new FingerPrint(startPos, getY(stringBounder).getCurrentValue(), startPos + h,
				getY(stringBounder).getCurrentValue() + h);
	}

	private UShape getDiamond(StringBounder stringBounder) {
		final double h = getShapeHeight(stringBounder);
		final UPolygon result = new UPolygon();
		result.addPoint(h / 2, 0);
		result.addPoint(h, h / 2);
		result.addPoint(h / 2, h);
		result.addPoint(0, h / 2);
		return result;
	}

	@Override
	public double getX1(TaskAttribute taskAttribute) {
		final double x1 = timeScale.getStartingPosition(start);
		final double x2 = timeScale.getEndingPosition(start);
		final double width = getShapeHeight(null);
		final double delta = x2 - x1 - width;
		return x1 + delta;
	}

	@Override
	public double getX2(TaskAttribute taskAttribute) {
		final double x1 = timeScale.getStartingPosition(start);
		final double x2 = timeScale.getEndingPosition(start);
		final double width = getShapeHeight(null);
		final double delta = x2 - x1 - width;
		return x2 - delta;
	}

}

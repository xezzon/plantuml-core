package net.sourceforge.plantuml.timingdiagram;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.command.Position;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.Sheet;
import net.sourceforge.plantuml.creole.SheetBlock1;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.svek.image.Opale;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TimingNote {

	private final TimeTick when;
	private final Player player;
	private final Display note;
	private final Position position;
	private final ISkinParam skinParam;
	private final Style style;

	public TimingNote(TimeTick when, Player player, Display note, Position position, ISkinParam skinParam,
			Style style) {
		this.style = style;
		this.note = note;
		this.player = player;
		this.when = when;
		this.skinParam = skinParam;
		this.position = position;
	}

	public void drawU(UGraphic ug) {
		if (position == Position.BOTTOM)
			ug = ug.apply(UTranslate.dy(getMarginY() / 2));

		createOpale().drawU(ug);

	}

	private Opale createOpale() {

		final FontConfiguration fc = FontConfiguration.create(skinParam, style);
		final double shadowing = style.value(PName.Shadowing).asDouble();
		final HColor borderColor = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		final HColor noteBackgroundColor = style.value(PName.BackGroundColor).asColor(skinParam.getIHtmlColorSet());
		final UStroke stroke = style.getStroke();

		final Sheet sheet = skinParam
				.sheet(fc, skinParam.getDefaultTextAlignment(HorizontalAlignment.LEFT), CreoleMode.FULL)
				.createSheet(note);
		final SheetBlock1 sheet1 = new SheetBlock1(sheet, LineBreakStrategy.NONE, skinParam.getPadding());
		final Opale opale = new Opale(shadowing, borderColor, noteBackgroundColor, sheet1, false, stroke);
		return opale;
	}

	public double getHeight(StringBounder stringBounder) {
		return createOpale().calculateDimension(stringBounder).getHeight() + getMarginY();
	}

	private double getMarginY() {
		return 10;
	}

	public TimeTick getWhen() {
		return when;
	}

	public final Position getPosition() {
		return position;
	}

}

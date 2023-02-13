package net.sourceforge.plantuml.activitydiagram3.ftile;

import static net.sourceforge.plantuml.utils.ObjectUtils.instanceOfAny;

import net.sourceforge.plantuml.klimt.UBackground;
import net.sourceforge.plantuml.klimt.UChange;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UGraphicNo;


public class ZadBuilder extends UGraphicNo {

	@Override
	public UGraphic apply(UChange change) {
		return new ZadBuilder(this, change);
	}

	private final Context context;

	static class Context {
		private final Zad zad = new Zad();
	}

	public ZadBuilder(StringBounder stringBounder) {
		super(stringBounder, new UTranslate());
		this.context = new Context();
	}

	private ZadBuilder(ZadBuilder other, UChange change) {
		// super(other, change);
		super(other.getStringBounder(), change instanceof UTranslate ? other.getTranslate().compose((UTranslate) change)
				: other.getTranslate());
		if (!instanceOfAny(change, UBackground.class, HColor.class, UStroke.class, UTranslate.class))
			throw new UnsupportedOperationException(change.getClass().toString());

		this.context = other.context;
	}

	public void draw(UShape shape) {
		if (shape instanceof URectangle) {
			drawRectangle((URectangle) shape);
		}
	}

	private void drawRectangle(URectangle shape) {
		final MinMax area = shape.getMinMax().translate(getTranslate());
		// System.err.println("ZadBuilder " + shape + " " + area);
		context.zad.add(area);
	}

	public Zad getZad() {
		return context.zad;
	}

}

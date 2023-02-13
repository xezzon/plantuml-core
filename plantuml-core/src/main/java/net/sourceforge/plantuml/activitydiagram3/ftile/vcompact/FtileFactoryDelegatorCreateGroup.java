package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.Collections;

import net.sourceforge.plantuml.activitydiagram3.PositionedNote;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.style.Style;

public class FtileFactoryDelegatorCreateGroup extends FtileFactoryDelegator {

	public FtileFactoryDelegatorCreateGroup(FtileFactory factory) {
		super(factory);
	}

	@Override
	public Ftile createGroup(Ftile list, Display name, HColor backColor, PositionedNote note, USymbol type,
			Style style) {
		Ftile result = new FtileGroup(list, name, backColor, skinParam(), type, style);
		if (note != null)
			result = new FtileWithNotes(result, Collections.singleton(note), skinParam(), VerticalAlignment.CENTER);

		return result;
	}

}

// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.atmp;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.compress.CompressionMode;
import net.sourceforge.plantuml.klimt.compress.UShapeIgnorableForCompression;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.UEmpty;

public class SpecialText implements UShapeIgnorableForCompression {

	private final TextBlock title;

	public SpecialText(TextBlock title) {
		this.title = title;
	}

	public boolean isIgnoreForCompressionOn(CompressionMode mode) {
		return true;
	}

	public void drawWhenCompressed(UGraphic ug, CompressionMode mode) {
		final XDimension2D dim = title.calculateDimension(ug.getStringBounder());
		ug.apply(UTranslate.dx(dim.getWidth())).draw(new UEmpty(1, 1));
	}

	public final TextBlock getTitle() {
		return title;
	}

}

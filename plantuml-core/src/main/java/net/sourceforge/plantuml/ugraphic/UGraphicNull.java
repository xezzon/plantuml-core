package net.sourceforge.plantuml.ugraphic;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.geom.EnsureVisible;

public class UGraphicNull extends AbstractUGraphic<String> implements EnsureVisible {

	@Override
	protected AbstractCommonUGraphic copyUGraphic() {
		return new UGraphicNull(this);
	}

	private UGraphicNull(UGraphicNull other) {
		super(other);
	}

	public UGraphicNull() {
		super(HColors.BLACK, ColorMapper.IDENTITY, FileFormat.PNG.getDefaultStringBounder(), "foo");
	}

	@Override
	public void writeToStream(OutputStream os, String metadata, int dpi) throws IOException {
	}

	public void ensureVisible(double x, double y) {
	}

}

// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import net.sourceforge.plantuml.klimt.drawing.svg.SvgGraphics;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.StringBounderRaw;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.font.UFontContext;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.text.SvgCharSizeHack;

/**
 * Format for output files generated by PlantUML.
 * 
 * @author Arnaud Roques
 * 
 */
public enum FileFormat {
	PNG("image/png"), //
	RAW("image/raw"), //
	SVG("image/svg+xml"); //

	private final String mimeType;

	FileFormat(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Returns the file format to be used for that format.
	 * 
	 * @return a string starting by a point.
	 */
	public String getFileSuffix() {

		return "." + StringUtils.goLowerCase(name());
	}

	final static private BufferedImage imDummy = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	final static public Graphics2D gg = imDummy.createGraphics();
	static {
		// KEY_FRACTIONALMETRICS
		gg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	public StringBounder getDefaultStringBounder() {
		return getDefaultStringBounder(TikzFontDistortion.getDefault(), SvgCharSizeHack.NO_HACK);
	}

	public StringBounder getDefaultStringBounder(TikzFontDistortion tikzFontDistortion, SvgCharSizeHack charSizeHack) {

		if (this == SVG)
			return getSvgStringBounder(charSizeHack);

		return getNormalStringBounder();
	}

	private StringBounder getSvgStringBounder(final SvgCharSizeHack charSizeHack) {
		return new StringBounderRaw(FileFormat.gg.getFontRenderContext()) {
			public String toString() {
				return "FileFormat::getSvgStringBounder";
			}

			protected XDimension2D calculateDimensionInternal(UFont font, String text) {
				text = charSizeHack.transformStringForSizeHack(text);
				return getJavaDimension(font, text);
			}

			public boolean matchesProperty(String propertyName) {
				return "SVG".equalsIgnoreCase(propertyName);
			}

		};
	}

	private StringBounder getNormalStringBounder() {
		return new StringBounderRaw(FileFormat.gg.getFontRenderContext()) {
			public String toString() {
				return "FileFormat::getNormalStringBounder";
			}

			protected XDimension2D calculateDimensionInternal(UFont font, String text) {
				return getJavaDimension(font, text);
			}

			public boolean matchesProperty(String propertyName) {
				return false;
			}

		};
	}

	static private XDimension2D getJavaDimension(UFont font, String text) {
		final Font javaFont = font.getUnderlayingFont(UFontContext.G2D);
		final FontMetrics fm = gg.getFontMetrics(javaFont);
		final Rectangle2D rect = fm.getStringBounds(text, gg);
		return new XDimension2D(rect.getWidth(), rect.getHeight());
	}


}

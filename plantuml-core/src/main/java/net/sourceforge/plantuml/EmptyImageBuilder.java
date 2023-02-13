package net.sourceforge.plantuml;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.UAntiAliasing;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.g2d.UGraphicG2d;
import net.sourceforge.plantuml.utils.Log;

public class EmptyImageBuilder {

	private final BufferedImage im;
	private final Graphics2D g2d;
	private final Color background;
	private final StringBounder stringBounder;

	private static EmptyImageBuilder create(String watermark, int width, int height, Color background,
			StringBounder stringBounder, double dpiFactor) {
		EmptyImageBuilder result = new EmptyImageBuilder(watermark, (int) (width * dpiFactor),
				(int) (height * dpiFactor), background, stringBounder);
		if (dpiFactor != 1.0)
			result.g2d.setTransform(AffineTransform.getScaleInstance(dpiFactor, dpiFactor));
		return result;
	}

	public EmptyImageBuilder(String watermark, int width, int height, Color background, StringBounder stringBounder) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("width and height must be positive");

		this.background = background;
		this.stringBounder = stringBounder;
		Log.info("Creating image " + width + "x" + height);
		im = new BufferedImage(width, height, getType(background));
		g2d = im.createGraphics();
		UAntiAliasing.ANTI_ALIASING_ON.apply(g2d);
		if (background != null) {
			g2d.setColor(background);
			g2d.fillRect(0, 0, width, height);
		}
		if (watermark != null) {
			final int gray = 200;
			g2d.setColor(new Color(gray, gray, gray));
			printWatermark(watermark, width, height);
		}
	}

	private int getType(Color background) {
		if (background == null) {
			return BufferedImage.TYPE_INT_ARGB;
		}
		if (background.getAlpha() != 255) {
			return BufferedImage.TYPE_INT_ARGB;
		}
		return BufferedImage.TYPE_INT_RGB;
	}

	private void printWatermark(String watermark, int maxWidth, int maxHeight) {
		final Font javaFont = g2d.getFont();
		final FontMetrics fm = g2d.getFontMetrics(javaFont);
		final Rectangle2D rect = fm.getStringBounds(watermark, g2d);
		final int height = (int) rect.getHeight();
		final int width = (int) rect.getWidth();
		if (height < 2 || width < 2) {
			return;
		}
		if (width <= maxWidth)
			for (int y = height; y < maxHeight; y += height + 1) {
				for (int x = 0; x < maxWidth; x += width + 10) {
					g2d.drawString(watermark, x, y);
				}
			}
		else {
			final List<String> withBreaks = withBreaks(watermark, javaFont, fm, maxWidth);
			int y = 0;
			while (y < maxHeight) {
				for (String s : withBreaks) {
					g2d.drawString(s, 0, y);
					y += (int) fm.getStringBounds(s, g2d).getHeight();
				}
				y += 10;
			}
		}
	}

	private int getWidth(String line, Font javaFont, FontMetrics fm) {
		final Rectangle2D rect = fm.getStringBounds(line, g2d);
		return (int) rect.getWidth();
	}

	private List<String> withBreaks(String watermark, Font javaFont, FontMetrics fm, int maxWidth) {
		final String[] words = watermark.split("\\s+");
		final List<String> result = new ArrayList<>();
		String pending = "";
		for (String word : words) {
			final String candidate = pending.length() == 0 ? word : pending + " " + word;
			if (getWidth(candidate, javaFont, fm) < maxWidth) {
				pending = candidate;
			} else {
				result.add(pending);
				pending = word;
			}
		}
		if (pending.length() > 0) {
			result.add(pending);
		}
		return result;
	}

	public BufferedImage getBufferedImage() {
		return im;
	}

	public Graphics2D getGraphics2D() {
		return g2d;
	}

//	public UGraphicG2d getUGraphicG2d(FileFormat format) {
//		final HColor back = HColors.simple(background);
//		final UGraphicG2d result = new UGraphicG2d(back, ColorMapper.IDENTITY, stringBounder, g2d, 1.0, format);
//		result.setBufferedImage(im);
//		return result;
//	}

}

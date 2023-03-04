// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.drawing.g2d;

import static java.lang.Math.max;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.FontStyle;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.font.UFontContext;
import net.sourceforge.plantuml.klimt.geom.EnsureVisible;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UText;
import net.sourceforge.plantuml.text.StyledString;

public class DriverTextG2d implements UDriver<UText, Graphics2D> {

	private final EnsureVisible visible;
	private final StringBounder stringBounder;

	public DriverTextG2d(EnsureVisible visible, StringBounder stringBounder) {
		this.visible = visible;
		this.stringBounder = stringBounder;
	}

	public void draw(UText shape, double x, double y, ColorMapper mapper, UParam param, Graphics2D g2d) {
		final FontConfiguration fontConfiguration = shape.getFontConfiguration();

		if (fontConfiguration.getColor().isTransparent())
			return;

		final String text = shape.getText();

		final List<StyledString> strings = StyledString.build(text);

		for (StyledString styledString : strings) {
			final FontConfiguration fc = styledString.getStyle() == FontStyle.BOLD ? fontConfiguration.bold()
					: fontConfiguration;
			x += printSingleText(g2d, fc, styledString.getText(), x, y, mapper);
		}
	}

	private double printSingleText(Graphics2D g2d, final FontConfiguration fontConfiguration, final String text,
			double x, double y, ColorMapper mapper) {
		final UFont font = fontConfiguration.getFont();
		final HColor extended = fontConfiguration.getExtendedColor();

		final XDimension2D dim = stringBounder.calculateDimension(font, text);
		final double height = max(10, dim.getHeight());
		final double width = dim.getWidth();

		final int orientation = 0;

		if (orientation == 90) {
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setFont(font.getUnderlayingFont(UFontContext.G2D));
			g2d.setColor(fontConfiguration.getColor().toColor(mapper));
			final AffineTransform orig = g2d.getTransform();
			g2d.translate(x, y);
			g2d.rotate(Math.PI / 2);
			g2d.drawString(text, 0, 0);
			g2d.setTransform(orig);

		} else if (orientation == 0) {

			if (fontConfiguration.containsStyle(FontStyle.BACKCOLOR)) {
				final Rectangle2D.Double area = new Rectangle2D.Double(x, y - height + 1.5, width, height);
				if (extended instanceof HColorGradient) {
					final GradientPaint paint = DriverRectangleG2d.getPaintGradient(x, y, mapper, width, height,
							extended);
					g2d.setPaint(paint);
					g2d.fill(area);
				} else {
					final Color backColor = extended.toColor(mapper);
					if (backColor != null) {
						g2d.setColor(backColor);
						g2d.setBackground(backColor);
						g2d.fill(area);
					}
				}
			}
			visible.ensureVisible(x, y - height + 1.5);
			visible.ensureVisible(x + width, y + 1.5);

			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			// https://stackoverflow.com/questions/31536952/how-to-fix-text-quality-in-java-graphics
			// https://stackoverflow.com/questions/72818320/improve-java2d-drawing-quality-on-hi-resolution-monitors
			/*
			 * g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
			 * RenderingHints.VALUE_RENDER_QUALITY);
			 * g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
			 * RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			 * g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			 * RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			 * g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			 * RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			 */
			g2d.setFont(font.getUnderlayingFont(UFontContext.G2D));
			g2d.setColor(fontConfiguration.getColor().toColor(mapper));
			g2d.drawString(text, (float) x, (float) y);

			if (fontConfiguration.containsStyle(FontStyle.UNDERLINE)) {
				if (extended != null)
					g2d.setColor(extended.toColor(mapper));

				final UStroke stroke = fontConfiguration.getUnderlineStroke();
				if (stroke.getThickness() > 0) {
					DriverLineG2d.manageStroke(stroke, g2d);
					final int ypos = (int) (y + 2.5);
					g2d.drawLine((int) x, ypos, (int) (x + width), ypos);
					g2d.setStroke(new BasicStroke());
				}
			}
			if (fontConfiguration.containsStyle(FontStyle.WAVE)) {
				final int ypos = (int) (y + 2.5) - 1;
				if (extended != null)
					g2d.setColor(extended.toColor(mapper));

				for (int i = (int) x; i < x + width - 5; i += 6) {
					g2d.drawLine(i, ypos - 0, i + 3, ypos + 1);
					g2d.drawLine(i + 3, ypos + 1, i + 6, ypos - 0);
				}
			}
			if (fontConfiguration.containsStyle(FontStyle.STRIKE)) {
				final FontMetrics fm = g2d.getFontMetrics(font.getUnderlayingFont(UFontContext.G2D));
				final int ypos = (int) (y - fm.getDescent() - 0.5);
				if (extended != null)
					g2d.setColor(extended.toColor(mapper));

				g2d.setStroke(new BasicStroke((float) 1.5));
				g2d.drawLine((int) x, ypos, (int) (x + width), ypos);
				g2d.setStroke(new BasicStroke());
			}
		}
		return width;
	}

}

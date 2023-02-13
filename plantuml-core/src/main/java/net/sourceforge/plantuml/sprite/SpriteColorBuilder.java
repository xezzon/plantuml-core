package net.sourceforge.plantuml.sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpriteColorBuilder {

	private final static ColorPalette COLOR_PALETTE = new ColorPalette();

	public static Sprite buildSprite(List<CharSequence> strings) {
		final SpriteColor result = new SpriteColor(strings.get(0).length(), strings.size());
		for (int col = 0; col < result.getWidth(); col++) {
			for (int line = 0; line < result.getHeight(); line++) {
				if (col >= strings.get(line).length()) {
					continue;
				}
				final char charColor = strings.get(line).charAt(col);
				final int idx = "0123456789ABCDEF".indexOf(charColor);
				if (idx != -1) {
					result.setGray(col, line, idx);
				} else {
					final Color rgb = COLOR_PALETTE.getColorFor(charColor);
					result.setColor(col, line, rgb.getRGB() & 0xFFFFFF);
				}
			}
		}
		return result;
	}

	static public List<String> encodeColor(BufferedImage img) {
		final int width = img.getWidth();
		final int height = img.getHeight();

		final List<String> result = new ArrayList<>();

		for (int y = 0; y < height; y++) {
			final StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {
				final int rgb = (img.getRGB(x, y) & 0xFFFFFF);
				final char code = COLOR_PALETTE.getCharFor(new Color(rgb));
				sb.append(code);
			}
			result.add(sb.toString());
		}
		return Collections.unmodifiableList(result);
	}

}

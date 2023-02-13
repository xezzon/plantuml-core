package net.sourceforge.plantuml.sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpriteColorBuilder4096 {

	private final static ColorPalette4096 COLOR_PALETTE = new ColorPalette4096();

	public static Sprite buildSprite(List<String> strings) {
		final SpriteColor result = new SpriteColor(strings.get(0).length() / 2, strings.size());
		for (int col = 0; col < result.getWidth(); col++) {
			for (int line = 0; line < result.getHeight(); line++) {
				if (col * 2 >= strings.get(line).length()) {
					continue;
				}
				final String charColor = strings.get(line).toString().substring(col * 2, col * 2 + 2);
				final Color rgb = COLOR_PALETTE.getColorFor(charColor);
				result.setColor(col, line, rgb.getRGB() & 0xFFFFFF);
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
				final String code = COLOR_PALETTE.getStringFor(new Color(rgb));
				sb.append(code);
			}
			result.add(sb.toString());
		}
		return Collections.unmodifiableList(result);
	}

}

package net.sourceforge.plantuml.fun;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SImageIO;

public class IconLoader {

	private static final int NUMBER_OF_ICONS = 31;

	private final static Map<String, BufferedImage> all = new ConcurrentHashMap<String, BufferedImage>();
	static private final List<String> tmp = new ArrayList<>();

	public static BufferedImage getRandom() {
		// return addTransparent(getIcon("sprite029.png"));
		return addTransparent(getIcon(getSomeQuote()));
	}

	private static String getSomeQuote() {
		synchronized (tmp) {
			if (tmp.size() == 0) {
				for (int i = 0; i < NUMBER_OF_ICONS; i++)
					tmp.add("sprite" + String.format("%03d", i) + ".png");

				Collections.shuffle(tmp);
			}
			final int size = tmp.size();
			final String result = tmp.get(size - 1);
			tmp.remove(size - 1);
			return result;
		}
	}

	private static BufferedImage getIcon(String name) {
		BufferedImage result = all.get(name);
		if (result == null) {
			result = getIconSlow(name);
			if (result != null)
				all.put(name, result);

		}
		return result;
	}

	private static BufferedImage getIconSlow(String name) {
		try {
			final InputStream is = IconLoader.class.getResourceAsStream(name);
			if (is == null)
				return null;

			final BufferedImage image = SImageIO.read(is);
			is.close();
			return image;
		} catch (IOException e) {
			Logme.error(e);
		}
		return null;
	}

	private static BufferedImage addTransparent(BufferedImage ico) {
		if (ico == null)
			return null;

		final BufferedImage transparentIcon = new BufferedImage(ico.getWidth(), ico.getHeight(),
				BufferedImage.TYPE_INT_ARGB_PRE);
		for (int i = 0; i < ico.getWidth(); i++)
			for (int j = 0; j < ico.getHeight(); j++) {
				final int col = ico.getRGB(i, j);
				if (col != ico.getRGB(0, 0))
					transparentIcon.setRGB(i, j, col);
			}

		return transparentIcon;
	}

}

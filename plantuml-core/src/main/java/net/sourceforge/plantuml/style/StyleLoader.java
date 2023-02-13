package net.sourceforge.plantuml.style;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.SkinParam;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.style.parser.StyleParser;
import net.sourceforge.plantuml.style.parser.StyleParsingException;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.LineLocationImpl;
import net.sourceforge.plantuml.utils.Log;

public class StyleLoader {

	private final SkinParam skinParam;

	public StyleLoader(SkinParam skinParam) {
		this.skinParam = skinParam;
	}

	private StyleBuilder styleBuilder;

	public StyleBuilder loadSkin(String filename) throws IOException, StyleParsingException {
		this.styleBuilder = new StyleBuilder(skinParam);

		final InputStream internalIs = getInputStreamForStyle(filename);
		if (internalIs == null) {
			Log.error("No .skin file seems to be available");
			throw new NoStyleAvailableException();
		}
		final BlocLines lines2 = BlocLines.load(internalIs, new LineLocationImpl(filename, null));
		loadSkinInternal(lines2);
		if (this.styleBuilder == null) {
			Log.error("No .skin file seems to be available");
			throw new NoStyleAvailableException();
		}
		return this.styleBuilder;
	}

	public static InputStream getInputStreamForStyle(String filename) throws IOException {
		final String res = "/skin/" + filename;
		final InputStream is = StyleLoader.class.getResourceAsStream(res);
		return is;

	}

	private void loadSkinInternal(final BlocLines lines) throws StyleParsingException {
		for (Style newStyle : StyleParser.parse(lines, styleBuilder))
			this.styleBuilder.loadInternal(newStyle.getSignature(), newStyle);
	}

	public static final int DELTA_PRIORITY_FOR_STEREOTYPE = 1000;

	public static Map<PName, Value> addPriorityForStereotype(Map<PName, Value> tmp) {
		final Map<PName, Value> result = new EnumMap<>(PName.class);
		for (Entry<PName, Value> ent : tmp.entrySet())
			result.put(ent.getKey(), ((ValueImpl) ent.getValue()).addPriority(DELTA_PRIORITY_FOR_STEREOTYPE));

		return result;
	}

}

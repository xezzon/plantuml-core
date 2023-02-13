package net.sourceforge.plantuml.url;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.text.BackSlash;

public class CMapData {

	private final StringBuilder stringBuilder = new StringBuilder();

	public String asString(String nameId) {
		return "<map id=\"" + nameId + "_map\" name=\"" + nameId + "_map\">\n" + stringBuilder.toString() + "</map>\n";
	}

	public boolean containsData() {
		return stringBuilder.length() > 0;
	}

	public void appendString(String s) {
		stringBuilder.append(s);
	}

	public void appendLong(long s) {
		stringBuilder.append(s);
	}

	private void appendUrl(int seq, Url url, double scale) {
		appendString("<area shape=\"rect\" id=\"id");
		appendLong(seq);
		appendString("\" href=\"");
		appendString(url.getUrl());
		appendString("\" title=\"");
		final String tooltip = url.getTooltip().replaceAll("\\\\n", BackSlash.NEWLINE).replaceAll("&", "&#38;")
				.replaceAll("\"", "&#34;").replaceAll("\'", "&#39;");
		appendString(tooltip);
		appendString("\" alt=\"\" coords=\"");
		appendString(url.getCoords(scale));
		appendString("\"/>");

		appendString(BackSlash.NEWLINE);
	}

	public static CMapData cmapString(Set<Url> allUrlEncountered, double scale) {
		final CMapData cmapdata = new CMapData();

		final List<Url> all = new ArrayList<>(allUrlEncountered);
		Collections.sort(all, Url.SURFACE_COMPARATOR);

		int seq = 1;
		for (Url u : all) {
			if (u.hasData() == false) {
				continue;
			}
			cmapdata.appendUrl(seq, u, scale);
			seq++;
		}
		return cmapdata;
	}

}

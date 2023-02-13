package net.sourceforge.plantuml;

import java.util.StringTokenizer;

public class TikzFontDistortion {

	private final double magnify;
	private final double distortion;

	private TikzFontDistortion(double magnify, double distortion) {
		this.magnify = magnify;
		this.distortion = distortion;
	}

	@Override
	public String toString() {
		return "" + magnify + ";" + distortion;
	}

	public static TikzFontDistortion fromValue(String value) {
		if (value == null) {
			return getDefault();
		}
		final StringTokenizer st = new StringTokenizer(value, ";");
		if (st.hasMoreElements() == false) {
			return getDefault();
		}
		final String v1 = st.nextToken();
		if (st.hasMoreElements() == false) {
			return getDefault();
		}
		final String v2 = st.nextToken();
		if (v1.matches("[\\d.]+") && v2.matches("[-\\d.]+")) {
			return new TikzFontDistortion(Double.parseDouble(v1), Double.parseDouble(v2));
		}
		return getDefault();
	}

	public static TikzFontDistortion getDefault() {
		return new TikzFontDistortion(1.20, 4.0);
	}

	public final double getMagnify() {
		return magnify;
	}

	public final double getDistortion() {
		return distortion;
	}

}

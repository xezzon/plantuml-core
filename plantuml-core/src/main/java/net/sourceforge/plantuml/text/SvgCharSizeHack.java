package net.sourceforge.plantuml.text;

public interface SvgCharSizeHack {

	public static final SvgCharSizeHack NO_HACK = new SvgCharSizeHack() {
		public String transformStringForSizeHack(String s) {
			return s;
		}
	};

	public String transformStringForSizeHack(String s);

}

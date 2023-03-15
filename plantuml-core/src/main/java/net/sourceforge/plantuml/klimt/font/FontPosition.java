// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.font;

public enum FontPosition {
	NORMAL, EXPOSANT, INDICE;

	public int getSpace() {
		if (this == EXPOSANT)
			return -6;

		if (this == INDICE)
			return 3;

		return 0;
	}

	public UFont mute(UFont font) {
		if (this == NORMAL)
			return font;

		int size = font.getSize() - 3;
		if (size < 2)
			size = 2;

		return font.withSize((float) size);
	}

	public String getHtmlTag() {
		if (this == EXPOSANT)
			return "sup";

		if (this == INDICE)
			return "sub";

		throw new UnsupportedOperationException();
	}

}

package net.sourceforge.plantuml.skin;

public class SimpleContext2D implements Context2D {

	private final boolean isBackground;

	public SimpleContext2D(boolean isBackground) {
		this.isBackground = isBackground;
	}

	public boolean isBackground() {
		return isBackground;
	}

}

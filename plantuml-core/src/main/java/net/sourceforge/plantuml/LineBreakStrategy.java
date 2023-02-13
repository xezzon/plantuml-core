package net.sourceforge.plantuml;

public class LineBreakStrategy {

	public static final LineBreakStrategy NONE = new LineBreakStrategy(null);

	private final String value;

	public LineBreakStrategy(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public boolean isAuto() {
		return "auto".equalsIgnoreCase(value);
	}

	public double getMaxWidth() {
		if (value != null && value.matches("-?\\d+"))
			return Double.parseDouble(value);

		return 0;
	}

}

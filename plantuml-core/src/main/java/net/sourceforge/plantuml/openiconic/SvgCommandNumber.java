package net.sourceforge.plantuml.openiconic;

import java.util.Locale;

public class SvgCommandNumber implements SvgCommand {

	final private String number;

	public SvgCommandNumber(String number) {
		if (number.matches("[-.0-9e]+") == false) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

	@Override
	public String toString() {
		return " " + number;
	}

	public SvgCommandNumber(double number) {
		this.number = String.format(Locale.US, "%1.4f", number);
	}

	public SvgCommandNumber add(SvgCommandNumber other) {
		return new SvgCommandNumber(getDouble() + other.getDouble());
	}

	public String toSvg() {
		return number;
	}

	public double getDouble() {
		return Double.parseDouble(number);
	}

}

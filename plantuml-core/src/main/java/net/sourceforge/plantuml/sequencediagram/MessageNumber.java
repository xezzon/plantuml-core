package net.sourceforge.plantuml.sequencediagram;

public class MessageNumber implements CharSequence {

	private final String representation;

	@Override
	public String toString() {
		return representation;
	}

	public MessageNumber(String s) {
		this.representation = s;
	}

	public String getNumberRaw() {
		return representation.replaceAll("\\</?b\\>", "");
	}

	public char charAt(int arg0) {
		return representation.charAt(arg0);
	}

	public int length() {
		return representation.length();
	}

	public CharSequence subSequence(int arg0, int arg1) {
		return representation.subSequence(arg0, arg1);
	}

}

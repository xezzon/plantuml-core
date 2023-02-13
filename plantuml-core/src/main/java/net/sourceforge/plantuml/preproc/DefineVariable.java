package net.sourceforge.plantuml.preproc;

public class DefineVariable {

	private final String name;
	private final String defaultValue;

	public DefineVariable(String name) {
		name = name.trim();
		final int idx = name.indexOf('=');
		if (idx == -1) {
			this.name = name;
			this.defaultValue = null;
		} else {
			this.name = name.substring(0, idx).trim();
			final String right = name.substring(idx + 1).trim();
			this.defaultValue = right.substring(1, right.length() - 1);
		}
	}

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public DefineVariable removeDefault() {
		if (defaultValue == null) {
			throw new IllegalStateException();
		}
		return new DefineVariable(name);
	}

}

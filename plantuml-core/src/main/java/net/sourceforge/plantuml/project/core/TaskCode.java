package net.sourceforge.plantuml.project.core;

public class TaskCode {

	private final String code;

	public TaskCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object arg) {
		final TaskCode other = (TaskCode) arg;
		return this.code.equals(other.code);
	}

	@Override
	public String toString() {
		return code.toString();
	}

	public String getSimpleDisplay() {
		return code;
	}

}

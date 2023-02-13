package net.sourceforge.plantuml.project.core;

public enum TaskAttribute {
	START, END, LOAD;

	public static TaskAttribute fromString(String value) {
		return valueOf(value.toUpperCase());
	}

}

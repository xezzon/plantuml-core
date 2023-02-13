package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.style.StyleBuilder;

public abstract class AbstractTask implements Task {

	private final TaskCode code;
	private final StyleBuilder styleBuilder;

	private Task row;

	protected AbstractTask(StyleBuilder styleBuilder, TaskCode code) {
		this.styleBuilder = styleBuilder;
		this.code = code;
	}

	final public void putInSameRowAs(Task row) {
		if (this != row)
			this.row = row;
	}

	public final Task getRow() {
		return row;
	}

	public final TaskCode getCode() {
		return code;
	}

	public final StyleBuilder getStyleBuilder() {
		return styleBuilder;
	}

}

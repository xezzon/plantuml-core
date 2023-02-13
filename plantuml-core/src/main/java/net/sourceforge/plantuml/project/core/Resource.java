package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.project.OpenClose;
import net.sourceforge.plantuml.project.draw.ResourceDraw;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.DayOfWeek;

public class Resource {

	private final String name;
	private ResourceDraw draw;

	private final OpenClose openClose = new OpenClose();

	public Resource(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final Resource other = (Resource) obj;
		return this.name.equals(other.name);
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public ResourceDraw getResourceDraw() {
		return draw;
	}

	public void setTaskDraw(ResourceDraw draw) {
		this.draw = draw;
	}

	public boolean isClosedAt(Day day) {
		return openClose.isClosed(day);
	}

	public void addCloseDay(Day day) {
		openClose.close(day);
	}

	public void addForceOnDay(Day day) {
		openClose.open(day);
	}

	public void addCloseDay(DayOfWeek day) {
		openClose.close(day);
	}
}

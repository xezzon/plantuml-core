package net.sourceforge.plantuml.project.timescale;

import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.DayOfWeek;

public class TimeScaleCompressed implements TimeScale {

	private final TimeScale daily;

	public TimeScaleCompressed(Day calendar, double scale) {
		this.daily = new TimeScaleDaily(calendar, scale, null);
	}

	public double getStartingPosition(Day instant) {
		return daily.getStartingPosition(instant);
	}

	public double getEndingPosition(Day instant) {
		return daily.getEndingPosition(instant);
	}

	public double getWidth(Day instant) {
		return daily.getWidth(instant);
	}

	public boolean isBreaking(Day instant) {
		return instant.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

}

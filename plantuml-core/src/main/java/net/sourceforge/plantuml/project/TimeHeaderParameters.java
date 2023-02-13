package net.sourceforge.plantuml.project;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.DayOfWeek;
import net.sourceforge.plantuml.style.Style;

public class TimeHeaderParameters {

	private final Map<Day, HColor> colorDays;
	private final double scale;
	private final Day min;
	private final Day max;
	private final HColorSet colorSet;
	private final Style timelineStyle;
	private final Style closedStyle;
	private final Locale locale;
	private final OpenClose openClose;
	private final Map<DayOfWeek, HColor> colorDaysOfWeek;
	private final Set<Day> verticalSeparatorBefore;

	public TimeHeaderParameters(Map<Day, HColor> colorDays, double scale, Day min, Day max, HColorSet colorSet,
			Style timelineStyle, Style closedStyle, Locale locale, OpenClose openClose, Map<DayOfWeek, HColor> colorDaysOfWeek,
			Set<Day> verticalSeparatorBefore) {
		this.colorDays = colorDays;
		this.scale = scale;
		this.min = min;
		this.max = max;
		this.colorSet = colorSet;
		this.timelineStyle = timelineStyle;
		this.closedStyle = closedStyle;
		this.locale = locale;
		this.openClose = openClose;
		this.colorDaysOfWeek = colorDaysOfWeek;
		this.verticalSeparatorBefore = verticalSeparatorBefore;
	}

	public HColor getColor(Day wink) {
		return colorDays.get(wink);
	}

	public HColor getColor(DayOfWeek dayOfWeek) {
		return colorDaysOfWeek.get(dayOfWeek);
	}

	public final double getScale() {
		return scale;
	}

	public final Day getMin() {
		return min;
	}

	public final Day getMax() {
		return max;
	}

	public final HColorSet getColorSet() {
		return colorSet;
	}

	public final Style getTimelineStyle() {
		return timelineStyle;
	}

	public final Style getClosedStyle() {
		return closedStyle;
	}

	public final Locale getLocale() {
		return locale;
	}

	public final LoadPlanable getLoadPlanable() {
		return openClose;
	}

	public Day getStartingDay() {
		return openClose.getStartingDay();
	}

	public final Set<Day> getVerticalSeparatorBefore() {
		return verticalSeparatorBefore;
	}

}

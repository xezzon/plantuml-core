package net.sourceforge.plantuml.project.core3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeLineSimple implements TimeLine {

	private final List<Long> events = new ArrayList<>();

	public long getNext(long moment) {
		for (long e : events) {
			if (e > moment) {
				return e;
			}
		}
		return TimeLine.MAX_TIME;
	}

	public long getPrevious(long moment) {
		long last = -TimeLine.MAX_TIME;
		for (long e : events) {
			if (e >= moment) {
				return last;
			}
			last = e;
		}
		return last;
	}

	public void add(long event) {
		this.events.add(event);
		Collections.sort(events);
	}

}

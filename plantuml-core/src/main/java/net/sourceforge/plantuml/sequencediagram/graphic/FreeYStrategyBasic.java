package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.sequencediagram.Event;

class FreeYStrategyBasic implements FreeYStrategy {

	public double peekPosition(Event event, ParticipantRange range) {
		return 0;
	}

	public void addElement(Event event, ParticipantRange range, double height) {
	}

}

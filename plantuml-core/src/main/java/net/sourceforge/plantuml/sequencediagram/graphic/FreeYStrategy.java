package net.sourceforge.plantuml.sequencediagram.graphic;

import net.sourceforge.plantuml.sequencediagram.Event;

interface FreeYStrategy {

	double peekPosition(Event event, ParticipantRange range);

	void addElement(Event event, ParticipantRange range, double height);
}

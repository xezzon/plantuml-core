package net.sourceforge.plantuml.sequencediagram;

import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.style.Styleable;

public enum ParticipantType implements Styleable {
	PARTICIPANT, //
	ACTOR, //
	BOUNDARY, //
	CONTROL, //
	ENTITY, //
	QUEUE, //
	DATABASE, //
	COLLECTIONS;

	private ParticipantType() {
	}

	public StyleSignatureBasic getStyleSignature() {
		if (this == PARTICIPANT)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.participant);

		if (this == ACTOR)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.actor);

		if (this == BOUNDARY)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.boundary);

		if (this == CONTROL)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.control);

		if (this == ENTITY)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.entity);

		if (this == QUEUE)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.queue);

		if (this == DATABASE)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.database);

		if (this == COLLECTIONS)
			return StyleSignatureBasic.of(SName.root, SName.element, SName.sequenceDiagram, SName.collections);

		return null;
	}

}

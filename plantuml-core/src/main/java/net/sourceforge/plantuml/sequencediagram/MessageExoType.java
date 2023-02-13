package net.sourceforge.plantuml.sequencediagram;

public enum MessageExoType {
	FROM_LEFT, TO_LEFT, FROM_RIGHT, TO_RIGHT;

	public int getDirection() {
		switch (this) {
		case FROM_LEFT:
			return 1;
		case TO_LEFT:
			return -1;
		case TO_RIGHT:
			return 1;
		case FROM_RIGHT:
			return -1;
		}
		throw new IllegalStateException();
	}

	public boolean isLeftBorder() {
		return this == MessageExoType.FROM_LEFT || this == MessageExoType.TO_LEFT;
	}

	public boolean isRightBorder() {
		return this == MessageExoType.FROM_RIGHT || this == MessageExoType.TO_RIGHT;
	}

	public MessageExoType reverse() {
		switch (this) {
		case FROM_LEFT:
			return TO_LEFT;
		case TO_RIGHT:
			return FROM_RIGHT;
		case FROM_RIGHT:
			return TO_RIGHT;
		case TO_LEFT:
			return FROM_LEFT;
		}
		throw new IllegalStateException();
	}

}

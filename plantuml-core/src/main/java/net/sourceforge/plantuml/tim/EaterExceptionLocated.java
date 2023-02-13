package net.sourceforge.plantuml.tim;

import java.util.Objects;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterExceptionLocated extends Exception {

	private final String message;
	private final StringLocated location;

	private EaterExceptionLocated(String message, StringLocated location) {
		this.message = message;
		this.location = location;
	}

	public static EaterExceptionLocated located(String message, StringLocated location) {
		return new EaterExceptionLocated(message, Objects.requireNonNull(location));
	}

	public final String getMessage() {
		return message;
	}

	public final StringLocated getLocation() {
		return location;
	}

}

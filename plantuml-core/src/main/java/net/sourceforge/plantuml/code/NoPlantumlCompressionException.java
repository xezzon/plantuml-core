package net.sourceforge.plantuml.code;

import java.io.IOException;

public class NoPlantumlCompressionException extends IOException {

	public NoPlantumlCompressionException(Exception cause) {
		super(cause);
	}

	public NoPlantumlCompressionException(String description) {
		super(description);
	}

}

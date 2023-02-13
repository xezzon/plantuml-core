package net.sourceforge.plantuml.error;

import java.util.List;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.text.StringLocated;

public class PSystemErrorEmpty extends PSystemError {

	public PSystemErrorEmpty(UmlSource source, List<StringLocated> trace, ErrorUml singleError) {
		super(source);
		this.trace = trace;
		this.singleError = singleError;

	}

}

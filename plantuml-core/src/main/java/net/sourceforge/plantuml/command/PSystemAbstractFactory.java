package net.sourceforge.plantuml.command;

import java.util.List;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.ErrorUmlType;
import net.sourceforge.plantuml.api.PSystemFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.error.PSystemError;
import net.sourceforge.plantuml.error.PSystemErrorUtils;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.LineLocation;

public abstract class PSystemAbstractFactory implements PSystemFactory {

	public static final String EMPTY_DESCRIPTION = "Empty description";
	private final DiagramType type;

	protected PSystemAbstractFactory(DiagramType type) {
		this.type = type;
	}

	final protected PSystemError buildEmptyError(UmlSource source, LineLocation lineLocation,
			List<StringLocated> trace) {
		final ErrorUml err = new ErrorUml(ErrorUmlType.SYNTAX_ERROR, EMPTY_DESCRIPTION, 0, lineLocation);
		final PSystemError result = PSystemErrorUtils.buildV2(source, err, null, trace);
		return result;
	}

	final protected PSystemError buildExecutionError(UmlSource source, String stringError, LineLocation lineLocation,
			List<StringLocated> trace) {
		final ErrorUml err = new ErrorUml(ErrorUmlType.EXECUTION_ERROR, stringError, 0, lineLocation);
		final PSystemError result = PSystemErrorUtils.buildV2(source, err, null, trace);
		return result;
	}

	final public DiagramType getDiagramType() {
		return type;
	}

}

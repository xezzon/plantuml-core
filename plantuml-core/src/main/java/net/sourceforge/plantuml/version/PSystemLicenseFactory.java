package net.sourceforge.plantuml.version;

import java.io.IOException;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.command.PSystemSingleLineFactory;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.utils.Log;

public class PSystemLicenseFactory extends PSystemSingleLineFactory {

	@Override
	protected AbstractPSystem executeLine(UmlSource source, String line) {
		try {
			if (line.matches("(?i)^li[sc][ea]n[sc]e\\s*$")) {
				return PSystemLicense.create(source);
			}
		} catch (IOException e) {
			Log.error("Error " + e);
		}
		return null;
	}

}

// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.eggs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.command.PSystemSingleLineFactory;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemColorsFactory extends PSystemSingleLineFactory {

	@Override
	protected AbstractPSystem executeLine(UmlSource source, String line) {
		final Pattern pattern = Pattern.compile("^colors?\\s*(#?\\w+)?\\s*$");
		final Matcher matcher = pattern.matcher(line);
		if (matcher.matches()) {
			return new PSystemColors(source, matcher.group(1));
		}
		return null;
	}

}

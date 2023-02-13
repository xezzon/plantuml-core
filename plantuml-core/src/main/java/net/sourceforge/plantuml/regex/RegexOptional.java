package net.sourceforge.plantuml.regex;

import net.sourceforge.plantuml.text.StringLocated;

public class RegexOptional extends RegexComposed implements IRegex {

	public RegexOptional(IRegex partial) {
		super(partial);
	}

	@Override
	protected String getFullSlow() {
		final StringBuilder sb = new StringBuilder("(?:");
		sb.append(partials().get(0).getPattern());
		sb.append(")?");
		return sb.toString();
	}
	
	public boolean match(StringLocated full) {
		throw new UnsupportedOperationException();
	}


}

package net.sourceforge.plantuml.regex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sourceforge.plantuml.text.StringLocated;

public class RegexOr extends RegexComposed implements IRegex {

	private final String name;

	
	public RegexOr(IRegex... partial) {
		this(null, partial);
	}

	public RegexOr(String name, IRegex... partials) {
		super(partials);
		this.name = name;
	}

	@Override
	protected String getFullSlow() {
		final StringBuilder sb = new StringBuilder("(");
		if (name == null) {
			sb.append("?:");
		}
		for (IRegex p : partials()) {
			sb.append(p.getPattern());
			sb.append("|");
		}
		sb.setLength(sb.length() - 1);
		sb.append(')');
		return sb.toString();
	}

	protected int getStartCount() {
		return 1;
	}

	final public Map<String, RegexPartialMatch> createPartialMatch(Iterator<String> it) {
		final Map<String, RegexPartialMatch> result = new HashMap<String, RegexPartialMatch>();
		final String fullGroup = name == null ? null : it.next();
		result.putAll(super.createPartialMatch(it));
		if (name != null) {
			final RegexPartialMatch m = new RegexPartialMatch(name);
			m.add(fullGroup);
			result.put(name, m);
		}
		return result;
	}

	public boolean match(StringLocated full) {
		throw new UnsupportedOperationException();
	}

}

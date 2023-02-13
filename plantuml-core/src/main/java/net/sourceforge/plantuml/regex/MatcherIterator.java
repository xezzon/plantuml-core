package net.sourceforge.plantuml.regex;

import java.util.Iterator;

public class MatcherIterator implements Iterator<String> {

	private int cpt = 1;
	private final Matcher2 matcher;

	MatcherIterator(Matcher2 matcher) {
		this.matcher = matcher;
	}

	public boolean hasNext() {
		return cpt <= matcher.groupCount();
	}

	public String next() {
		return matcher.group(cpt++);
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}

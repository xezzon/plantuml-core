package net.sourceforge.plantuml.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import net.sourceforge.plantuml.text.StringLocated;

public abstract class RegexComposed implements IRegex {

//	protected static final AtomicInteger nbCreateMatches = new AtomicInteger();
//	protected static final AtomicInteger vtot = new AtomicInteger();
//	protected static final AtomicInteger vescaped = new AtomicInteger();

	private final List<IRegex> partials;

	protected final List<IRegex> partials() {
		return partials;
	}

	abstract protected String getFullSlow();

	private final AtomicReference<Pattern2> fullCached = new AtomicReference<>();

	private Pattern2 getPattern2() {
		Pattern2 result = fullCached.get();
		if (result == null) {
			final String fullSlow = getFullSlow();
			result = MyPattern.cmpile(fullSlow);
			fullCached.set(result);
		}
		return result;
	}

	final protected boolean isCompiled() {
		return fullCached.get() != null;
	}

	public RegexComposed(IRegex... partial) {
		this.partials = Collections.unmodifiableList(Arrays.asList(partial));
	}

	public Map<String, RegexPartialMatch> createPartialMatch(Iterator<String> it) {
		// nbCreateMatches.incrementAndGet();
		final Map<String, RegexPartialMatch> result = new HashMap<String, RegexPartialMatch>();
		for (IRegex r : partials)
			result.putAll(r.createPartialMatch(it));

		return result;
	}

	final public int count() {
		int cpt = getStartCount();
		for (IRegex r : partials)
			cpt += r.count();

		return cpt;
	}

	protected int getStartCount() {
		return 0;
	}

	public RegexResult matcher(String s) {
		final Matcher2 matcher = getPattern2().matcher(s);
		if (matcher.find() == false)
			return null;

		final Iterator<String> it = new MatcherIterator(matcher);
		return new RegexResult(createPartialMatch(it));
	}

	public boolean match(StringLocated s) {
		final String tmp = s.getString();
		final Matcher2 matcher = getPattern2().matcher(tmp);
		if (matcher == null)
			return false;

		return matcher.find();
	}

	final public String getPattern() {
		return getPattern2().pattern();
	}

	final protected List<IRegex> getPartials() {
		return partials;
	}

}

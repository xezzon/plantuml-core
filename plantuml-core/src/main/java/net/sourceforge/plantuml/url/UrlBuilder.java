package net.sourceforge.plantuml.url;

import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;

public class UrlBuilder {

	public static final IRegex MANDATORY = new RegexLeaf("URL", "(" + UrlBuilder.getRegexp() + ")");
	public static final IRegex OPTIONAL = new RegexOptional(MANDATORY);

	private static final String START_PART = "\\[\\[[%s]*";
	private static final String END_PART = "[%s]*\\]\\]";

	private static final String S_QUOTED = START_PART + //
			"[%g]([^%g]+)[%g]" + // Quoted part
			"(?:[%s]*\\{([^{}]*)\\})?" + // Optional tooltip
			"(?:[%s]([^%s\\{\\}\\[\\]][^\\[\\]]*))?" + // Optional label
			END_PART;

	private static final String S_ONLY_TOOLTIP = START_PART + //
			"\\{(.*)\\}" + // Tooltip
			END_PART;

	private static final String S_ONLY_TOOLTIP_AND_LABEL = START_PART + //
			"\\{([^{}]*)\\}" + // Tooltip
			"[%s]*" + //
			"([^\\[%s\\{\\}\\[\\]][^\\[\\]]*)" // Label
			+ END_PART;

	private static final String S_LINK_TOOLTIP_NOLABEL = START_PART + //
			"([^\\s%g{}\\[\\]]+?)" + // Link
			"[%s]*" + //
			"\\{(.+)\\}" + // Tooltip
			END_PART;

	private static final String S_LINK_WITH_OPTIONAL_TOOLTIP_WITH_OPTIONAL_LABEL = START_PART + //
			"([^%s%g\\[\\]]+?)" + // Link
			"(?:[%s]*\\{([^{}]*)\\})?" + // Optional tooltip
			"(?:[%s]([^%s\\{\\}\\[\\]][^\\[\\]]*))?" + // Optional label
			END_PART;

	public static String getRegexp() {
		return S_QUOTED + "|" + //
				S_ONLY_TOOLTIP + "|" + //
				S_ONLY_TOOLTIP_AND_LABEL + "|" + //
				S_LINK_TOOLTIP_NOLABEL + "|" + //
				S_LINK_WITH_OPTIONAL_TOOLTIP_WITH_OPTIONAL_LABEL;
	}

	private static final Pattern2 QUOTED = MyPattern.cmpile(S_QUOTED);
	private static final Pattern2 ONLY_TOOLTIP = MyPattern.cmpile(S_ONLY_TOOLTIP);
	private static final Pattern2 ONLY_TOOLTIP_AND_LABEL = MyPattern.cmpile(S_ONLY_TOOLTIP_AND_LABEL);
	private static final Pattern2 LINK_TOOLTIP_NOLABEL = MyPattern.cmpile(S_LINK_TOOLTIP_NOLABEL);
	private static final Pattern2 LINK_WITH_OPTIONAL_TOOLTIP_WITH_OPTIONAL_LABEL = MyPattern
			.cmpile(S_LINK_WITH_OPTIONAL_TOOLTIP_WITH_OPTIONAL_LABEL);

	private final String topurl;
	private UrlMode mode;

	public UrlBuilder(String topurl, UrlMode mode) {
		this.topurl = topurl;
		this.mode = mode;
	}

	public Url getUrl(String s) {
		Matcher2 m;
		m = QUOTED.matcher(s);
		if (matchesOrFind(m))
			return new Url(withTopUrl(m.group(1)), m.group(2), m.group(3));

		m = ONLY_TOOLTIP.matcher(s);
		if (matchesOrFind(m))
			return new Url("", m.group(1), null);

		m = ONLY_TOOLTIP_AND_LABEL.matcher(s);
		if (matchesOrFind(m))
			return new Url("", m.group(1), m.group(2));

		m = LINK_TOOLTIP_NOLABEL.matcher(s);
		if (matchesOrFind(m))
			return new Url(withTopUrl(m.group(1)), m.group(2), null);

		m = LINK_WITH_OPTIONAL_TOOLTIP_WITH_OPTIONAL_LABEL.matcher(s);
		if (matchesOrFind(m))
			return new Url(withTopUrl(m.group(1)), m.group(2), m.group(3));

		return null;

	}

	private boolean matchesOrFind(Matcher2 m) {
		if (mode == UrlMode.STRICT)
			return m.matches();
		else if (mode == UrlMode.ANYWHERE)
			return m.find();
		else
			throw new IllegalStateException();

	}

	private String withTopUrl(String url) {
		if (url.startsWith("http:") == false && url.startsWith("https:") == false && url.startsWith("file:") == false
				&& topurl != null)
			return topurl + url;

		return url;
	}

}

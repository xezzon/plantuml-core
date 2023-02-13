package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;

public class ComplementUrl implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexConcat( //
				new RegexLeaf("COMPLEMENT" + suffix, "(" + UrlBuilder.getRegexp() + ")")); //
	}

	public Failable<Url> getMe(GanttDiagram diagram, RegexResult arg, String suffix) {
		final String urlString = arg.get("COMPLEMENT" + suffix, 0);
		final UrlBuilder urlBuilder = new UrlBuilder("", UrlMode.STRICT);
		final Url url = urlBuilder.getUrl(urlString);
		return Failable.ok(url);
	}

}

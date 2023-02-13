package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandUrl extends SingleLineCommand2<AbstractEntityDiagram> {

	public CommandUrl() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandUrl.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("url"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexLeaf("of|for")), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "([%pLN_.]+|[%g][^%g]+[%g])"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOptional(new RegexLeaf("is")), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.MANDATORY, //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractEntityDiagram diagram, LineLocation location, RegexResult arg) {
		final String idShort = arg.get("CODE", 0);

		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(idShort), false);
		final Entity entity = quark.getData();
		if (entity == null)
			return CommandExecutionResult.error(quark.getName() + " does not exist");

		final String urlString = arg.get("URL", 0);

		final UrlBuilder urlBuilder = new UrlBuilder(diagram.getSkinParam().getValue("topurl"), UrlMode.STRICT);
		final Url url = urlBuilder.getUrl(urlString);
		entity.addUrl(url);
		return CommandExecutionResult.ok();
	}

}

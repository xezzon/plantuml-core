package net.sourceforge.plantuml.objectdiagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.objectdiagram.AbstractClassOrObjectDiagram;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandCreateEntityObject extends SingleLineCommand2<AbstractClassOrObjectDiagram> {

	public CommandCreateEntityObject() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCreateEntityObject.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "object"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NAME", "(?:[%g]([^%g]+)[%g][%s]+as[%s]+)?([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.+\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.OPTIONAL, //
				RegexLeaf.spaceZeroOrMore(), //
				ColorParser.exp1(), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractClassOrObjectDiagram diagram, LineLocation location,
			RegexResult arg) throws NoSuchColorException {
		final String idShort = arg.get("NAME", 1);

		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(idShort), false);
		final String displayString = arg.get("NAME", 0);
		final String stereotype = arg.get("STEREO", 0);

		if (quark.getData() != null)
			return CommandExecutionResult.error("Object already exists : " + quark.getData());

		Display display = Display.getWithNewlines(displayString);
		if (Display.isNull(display))
			display = Display.getWithNewlines(idShort).withCreoleMode(CreoleMode.SIMPLE_LINE);

		final Entity entity = diagram.reallyCreateLeaf(quark, display, LeafType.OBJECT, null);
		if (stereotype != null)
			entity.setStereotype(Stereotype.build(stereotype, diagram.getSkinParam().getCircledCharacterRadius(),
					diagram.getSkinParam().getFont(null, false, FontParam.CIRCLED_CHARACTER),
					diagram.getSkinParam().getIHtmlColorSet()));

		final String urlString = arg.get("URL", 0);
		if (urlString != null) {
			final UrlBuilder urlBuilder = new UrlBuilder(diagram.getSkinParam().getValue("topurl"), UrlMode.STRICT);
			final Url url = urlBuilder.getUrl(urlString);
			entity.addUrl(url);
		}
		final String s = arg.get("COLOR", 0);
		entity.setSpecificColorTOBEREMOVED(ColorType.BACK,
				s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s));
		return CommandExecutionResult.ok();
	}
}

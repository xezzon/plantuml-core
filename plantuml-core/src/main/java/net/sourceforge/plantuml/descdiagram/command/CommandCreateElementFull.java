package net.sourceforge.plantuml.descdiagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.classdiagram.command.CommandCreateClassMultilines;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.cucadiagram.Stereotag;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.descdiagram.DescriptionDiagram;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.graphic.USymbols;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.url.Url;
import net.sourceforge.plantuml.url.UrlBuilder;
import net.sourceforge.plantuml.url.UrlMode;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandCreateElementFull extends SingleLineCommand2<DescriptionDiagram> {

	public static final String ALL_TYPES = "person|artifact|actor/|actor|folder|card|file|package|rectangle|hexagon|label|node|frame|cloud|database|queue|stack|storage|agent|usecase/|usecase|component|boundary|control|entity|interface|circle|collections|port|portin|portout";

	public CommandCreateElementFull() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCreateElementFull.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("SYMBOL", "(?:(" + ALL_TYPES + "|\\(\\))[%s]+)?"), //
				color2().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOr(//
						new RegexLeaf("CODE1", CODE_WITH_QUOTE), //
						new RegexConcat(//
								new RegexLeaf("DISPLAY2", DISPLAY), //
								new RegexOptional( //
										new RegexConcat( //
												RegexLeaf.spaceOneOrMore(), //
												new RegexLeaf("STEREOTYPE2", "(\\<\\<.+\\>\\>)")//
										)), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("as"), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("CODE2", CODE)), //
						new RegexConcat(//
								new RegexLeaf("CODE3", CODE), //
								new RegexOptional( //
										new RegexConcat( //
												RegexLeaf.spaceOneOrMore(), //
												new RegexLeaf("STEREOTYPE3", "(\\<\\<.+\\>\\>)") //
										)), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("as"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("DISPLAY3", DISPLAY)), //
						new RegexConcat(//
								new RegexLeaf("DISPLAY4", DISPLAY_WITHOUT_QUOTE), //
								new RegexOptional( //
										new RegexConcat( //
												RegexLeaf.spaceOneOrMore(), //
												new RegexLeaf("STEREOTYPE4", "(\\<\\<.+\\>\\>)") //
										)), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("as"), //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("CODE4", CODE)) //
				), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS1", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREOTYPE", "(\\<\\<.+\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TAGS2", Stereotag.pattern() + "?"), //
				RegexLeaf.spaceZeroOrMore(), //
				UrlBuilder.OPTIONAL, //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), RegexLeaf.end());
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.BACK);
	}

	private static ColorParser color2() {
		return ColorParser.simpleColor(ColorType.BACK, "COLOR2");
	}

	private static final String CODE_CORE = "[%pLN_.]+|\\(\\)[%s]*[%pLN_.]+|\\(\\)[%s]*[%g][^%g]+[%g]|:[^:]+:/?|\\([^()]+\\)/?|\\[[^\\[\\]]+\\]";
	public static final String CODE = "(" + CODE_CORE + ")";
	public static final String CODE_WITH_QUOTE = "(" + CODE_CORE + "|[%g].+?[%g])";

	private static final String DISPLAY_CORE = "[%g].+?[%g]|:[^:]+:/?|\\([^()]+\\)/?|\\[[^\\[\\]]+\\]";
	public static final String DISPLAY = "(" + DISPLAY_CORE + ")";
	public static final String DISPLAY_WITHOUT_QUOTE = "(" + DISPLAY_CORE + "|[%pLN_.]+)";

	@Override
	final protected boolean isForbidden(CharSequence line) {
		if (line.toString().matches("^[\\p{L}0-9_.]+$"))
			return true;

		return false;
	}

	@Override
	protected CommandExecutionResult executeArg(DescriptionDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		String codeRaw = arg.getLazzy("CODE", 0);
		String displayRaw = arg.getLazzy("DISPLAY", 0);
		final char codeChar = getCharEncoding(codeRaw);
		final char codeDisplay = getCharEncoding(displayRaw);
		final String symbol;
		if (codeRaw.startsWith("()")) {
			symbol = "interface";
			codeRaw = StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(StringUtils.trin(codeRaw.substring(2)));
		} else if (codeChar == '(' || codeDisplay == '(') {
			if (arg.get("SYMBOL", 0) != null && arg.get("SYMBOL", 0).endsWith("/")) {
				symbol = "usecase/";
			} else if (displayRaw != null && displayRaw.endsWith(")/")) {
				displayRaw = displayRaw.substring(0, displayRaw.length() - 1);
				symbol = "usecase/";
			} else if (codeRaw.endsWith(")/")) {
				codeRaw = codeRaw.substring(0, codeRaw.length() - 1);
				symbol = "usecase/";
			} else {
				symbol = "usecase";
			}
		} else if (codeChar == ':' || codeDisplay == ':') {
			if (arg.get("SYMBOL", 0) != null && arg.get("SYMBOL", 0).endsWith("/")) {
				symbol = "actor/";
			} else if (displayRaw != null && displayRaw.endsWith(":/")) {
				displayRaw = displayRaw.substring(0, displayRaw.length() - 1);
				symbol = "actor/";
			} else if (codeRaw.endsWith(":/")) {
				codeRaw = codeRaw.substring(0, codeRaw.length() - 1);
				symbol = "actor/";
			} else {
				symbol = "actor";
			}
		} else if (codeChar == '[' || codeDisplay == '[') {
			symbol = "component";
		} else {
			symbol = arg.get("SYMBOL", 0);
		}

		final LeafType type;
		final USymbol usymbol;

		if (symbol == null) {
			type = LeafType.DESCRIPTION;
			usymbol = diagram.getSkinParam().actorStyle().toUSymbol();
		} else if (symbol.equalsIgnoreCase("portin")) {
			type = LeafType.PORTIN;
			usymbol = null;
		} else if (symbol.equalsIgnoreCase("portout")) {
			type = LeafType.PORTOUT;
			usymbol = null;
		} else if (symbol.equalsIgnoreCase("port")) {
			type = LeafType.PORTIN;
			usymbol = null;
		} else if (symbol.equalsIgnoreCase("usecase")) {
			type = LeafType.USECASE;
			usymbol = null;
		} else if (symbol.equalsIgnoreCase("usecase/")) {
			type = LeafType.USECASE_BUSINESS;
			usymbol = null;
		} else if (symbol.equalsIgnoreCase("circle")) {
			type = LeafType.CIRCLE;
			usymbol = null;
		} else {
			type = LeafType.DESCRIPTION;
			usymbol = USymbols.fromString(symbol, diagram.getSkinParam());
			if (usymbol == null)
				throw new IllegalStateException();

		}

		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(codeRaw), false);

		if (diagram.isGroup(quark))
			return CommandExecutionResult.error("This element (" + quark.getName() + ") is already defined");

		String display = displayRaw;
		if (display == null)
			display = quark.getName();

		display = StringUtils.eventuallyRemoveStartingAndEndingDoubleQuote(display);
		final String stereotype = arg.getLazzy("STEREOTYPE", 0);
		if (existsWithBadType3(diagram, quark, type, usymbol))
			return CommandExecutionResult.error("This element (" + quark.getName() + ") is already defined");

		if ((type == LeafType.PORTIN || type == LeafType.PORTOUT) && diagram.getCurrentGroup().isRoot())
			return CommandExecutionResult.error("Port can only be used inside an element and not at root level");

		Entity entity = quark.getData();
		if (entity == null)
			entity = diagram.reallyCreateLeaf(quark, Display.getWithNewlines(display), type, usymbol);

		entity.setDisplay(Display.getWithNewlines(display));
		entity.setUSymbol(usymbol);
		if (stereotype != null)
			entity.setStereotype(Stereotype.build(stereotype, diagram.getSkinParam().getCircledCharacterRadius(),
					diagram.getSkinParam().getFont(null, false, FontParam.CIRCLED_CHARACTER),
					diagram.getSkinParam().getIHtmlColorSet()));

		CommandCreateClassMultilines.addTags(entity, arg.getLazzy("TAGS", 0));

		final String urlString = arg.get("URL", 0);
		if (urlString != null) {
			final UrlBuilder urlBuilder = new UrlBuilder(diagram.getSkinParam().getValue("topurl"), UrlMode.STRICT);
			final Url url = urlBuilder.getUrl(urlString);
			entity.addUrl(url);
		}

		Colors colors = color().getColor(arg, diagram.getSkinParam().getIHtmlColorSet());
		final String s = arg.get("LINECOLOR", 1);

		final HColor lineColor = s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s);
		if (lineColor != null)
			colors = colors.add(ColorType.LINE, lineColor);

		entity.setColors(colors);

		return CommandExecutionResult.ok();
	}

	public static boolean existsWithBadType3(AbstractEntityDiagram diagram, Quark<Entity> code, LeafType type, USymbol usymbol) {
		if (code.getData() == null)
			return false;

		final Entity other = code.getData();
		if (other.getLeafType() != type)
			return true;

		if (usymbol != null && other.getUSymbol() != usymbol)
			return true;

		return false;
	}

	private char getCharEncoding(final String codeRaw) {
		return codeRaw != null && codeRaw.length() > 2 ? codeRaw.charAt(0) : 0;
	}
}

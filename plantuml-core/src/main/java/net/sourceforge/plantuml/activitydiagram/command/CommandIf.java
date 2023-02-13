package net.sourceforge.plantuml.activitydiagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.activitydiagram.ActivityDiagram;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.classdiagram.command.CommandLinkClass;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Link;
import net.sourceforge.plantuml.cucadiagram.LinkArg;
import net.sourceforge.plantuml.cucadiagram.LinkDecor;
import net.sourceforge.plantuml.cucadiagram.LinkType;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.Direction;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandIf extends SingleLineCommand2<ActivityDiagram> {

	public CommandIf() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandIf.class.getName(), RegexLeaf.start(), //
				new RegexOptional(//
						new RegexOr("FIRST", //
								new RegexLeaf("STAR", "(\\(\\*(top)?\\))"), //
								new RegexLeaf("CODE", "([%pLN_.]+)"), //
								new RegexLeaf("BAR", "(?:==+)[%s]*([%pLN_.]+)[%s]*(?:==+)"), //
								new RegexLeaf("QUOTED", "[%g]([^%g]+)[%g](?:[%s]+as[%s]+([%pLN_.]+))?"))), //
				RegexLeaf.spaceZeroOrMore(), //
				// new RegexOptional(new RegexLeaf("ARROW",
				// "([=-]+(?:(left|right|up|down|le?|ri?|up?|do?)(?=[-=.]))?[=-]*\\>)")), //
				new RegexOptional(new RegexConcat( //
						new RegexLeaf("ARROW_BODY1", "([-.]+)"), //
						new RegexLeaf("ARROW_STYLE1", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
						new RegexLeaf("ARROW_DIRECTION", "(\\*|left|right|up|down|le?|ri?|up?|do?)?"), //
						new RegexLeaf("ARROW_STYLE2", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
						new RegexLeaf("ARROW_BODY2", "([-.]*)"), //
						new RegexLeaf("\\>") //
				)), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexLeaf("BRACKET", "\\[([^\\]*]+[^\\]]*)\\]")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOr(//
						new RegexLeaf("IF1", "if[%s]*[%g]([^%g]*)[%g][%s]*(?:as[%s]+([%pLN_.]+)[%s]+)?"), //
						new RegexLeaf("IF2", "if[%s]+(.+?)")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexLeaf("then")), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram diagram, LineLocation location, RegexResult arg) {
		final Entity entity1 = CommandLinkActivity.getEntity(diagram, arg, true);
		if (entity1 == null)
			return CommandExecutionResult.error("No if possible at this point");

		final String ifCode;
		final String ifLabel;
		if (arg.get("IF2", 0) == null) {
			ifCode = arg.get("IF1", 1);
			ifLabel = arg.get("IF1", 0);
		} else {
			ifCode = null;
			ifLabel = arg.get("IF2", 0);
		}
		diagram.startIf(ifCode);

		int lenght = 2;

		if (arg.get("ARROW_BODY1", 0) != null) {
//			final String arrow = StringUtils.manageArrowForCuca(arg.get("ARROW", 0));
//			lenght = arrow.length() - 1;
			final String arrowBody1 = CommandLinkClass.notNull(arg.get("ARROW_BODY1", 0));
			final String arrowBody2 = CommandLinkClass.notNull(arg.get("ARROW_BODY2", 0));
			final String arrowDirection = CommandLinkClass.notNull(arg.get("ARROW_DIRECTION", 0));

			final String arrow = StringUtils.manageArrowForCuca(arrowBody1 + arrowDirection + arrowBody2 + ">");
			lenght = arrow.length() - 1;
		}

		final Entity branch = diagram.getCurrentContext().getBranch();

		final LinkArg linkArg = LinkArg.build(Display.getWithNewlines(arg.get("BRACKET", 0)), lenght);
		Link link = new Link(diagram.getEntityFactory(), diagram.getSkinParam().getCurrentStyleBuilder(), entity1,
				branch, new LinkType(LinkDecor.ARROW, LinkDecor.NONE), linkArg.withQuantifier(null, ifLabel)
						.withDistanceAngle(diagram.getLabeldistance(), diagram.getLabelangle()));
		if (arg.get("ARROW", 0) != null) {
			final Direction direction = StringUtils.getArrowDirection(arg.get("ARROW", 0));
			if (direction == Direction.LEFT || direction == Direction.UP)
				link = link.getInv();

		}

		link.applyStyle(arg.getLazzy("ARROW_STYLE", 0));
		diagram.addLink(link);

		return CommandExecutionResult.ok();
	}

}

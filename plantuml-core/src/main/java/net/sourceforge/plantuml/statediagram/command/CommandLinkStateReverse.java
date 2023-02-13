package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.utils.Direction;

public class CommandLinkStateReverse extends CommandLinkStateCommon {

	public CommandLinkStateReverse() {
		super(getRegex());
	}

	static RegexConcat getRegex() {
		return RegexConcat.build(CommandLinkStateReverse.class.getName(), RegexLeaf.start(), //
				getStatePattern("ENT2"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexConcat(
						//
						new RegexLeaf("ARROW_CIRCLE_END", "(o[%s]+)?"), //
						new RegexLeaf("\\<"), //
						new RegexLeaf("ARROW_BODY2", "(-*)"), //
						new RegexLeaf("ARROW_STYLE2", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
						new RegexLeaf("ARROW_DIRECTION", "(left|right|up|down|le?|ri?|up?|do?)?"), //
						new RegexLeaf("ARROW_STYLE1", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
						new RegexLeaf("ARROW_BODY1", "(-+)"), //
						new RegexLeaf("ARROW_CROSS_START", "(x)?")), //
				RegexLeaf.spaceZeroOrMore(), //
				getStatePattern("ENT1"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf(":"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("LABEL", "(.+)") //
						)), RegexLeaf.end());
	}

	@Override
	protected Direction getDefaultDirection() {
		return Direction.LEFT;
	}

}

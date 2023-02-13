package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.Player;
import net.sourceforge.plantuml.timingdiagram.TimeTick;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandChangeStateByTime extends CommandChangeState {

	public CommandChangeStateByTime() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandChangeStateByTime.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				TimeTickBuilder.expressionAtWithoutArobase("TIME"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("is"), //
				RegexLeaf.spaceZeroOrMore(), //
				getStateOrHidden(), //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf(":"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("COMMENT", "(.*?)") //
						)), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final Player player = diagram.getLastPlayer();
		if (player == null)
			return CommandExecutionResult.error("Missing @ line before this");

		final TimeTick tick = TimeTickBuilder.parseTimeTick("TIME", arg, diagram);
		diagram.addTime(tick, null);
		return addState(diagram, arg, player, tick);
	}

}

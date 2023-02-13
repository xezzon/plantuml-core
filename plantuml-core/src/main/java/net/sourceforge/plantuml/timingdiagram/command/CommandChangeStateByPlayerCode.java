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

public class CommandChangeStateByPlayerCode extends CommandChangeState {

	public CommandChangeStateByPlayerCode() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandChangeStateByPlayerCode.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("CODE", CommandTimeMessage.PLAYER_CODE), //
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
		final String code = arg.get("CODE", 0);
		final Player player = diagram.getPlayer(code);
		if (player == null)
			return CommandExecutionResult.error("Unkown \"" + code + "\"");

		final TimeTick now = diagram.getNow();
		return addState(diagram, arg, player, now);
	}

}

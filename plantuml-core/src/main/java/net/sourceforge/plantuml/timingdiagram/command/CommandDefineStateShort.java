package net.sourceforge.plantuml.timingdiagram.command;

import java.util.StringTokenizer;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.Player;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandDefineStateShort extends SingleLineCommand2<TimingDiagram> {

	public CommandDefineStateShort() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandDefineStateShort.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("PLAYER", "([%pLN_.@]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("has"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("STATE", "([%pLN_.@]+)"), //
				new RegexLeaf("STATES", "((,([%pLN_.@]+))*)"), RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String playerCode = arg.get("PLAYER", 0);
		final Player player = diagram.getPlayer(playerCode);
		if (player == null) {
			return CommandExecutionResult.error("Unknown " + playerCode);
		}
		final String stateCode = arg.get("STATE", 0);
		player.defineState(stateCode, stateCode);
		final String states = arg.get("STATES", 0);
		if (states != null) {
			for (StringTokenizer st = new StringTokenizer(states, ","); st.hasMoreTokens();) {
				final String token = st.nextToken();
				player.defineState(token, token);
			}
		}

		return CommandExecutionResult.ok();
	}
}

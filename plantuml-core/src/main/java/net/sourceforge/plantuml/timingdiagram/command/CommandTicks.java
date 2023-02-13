package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.Player;
import net.sourceforge.plantuml.timingdiagram.PlayerAnalog;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandTicks extends SingleLineCommand2<TimingDiagram> {

	public CommandTicks() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandTicks.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("PLAYER", "([%pLN_.@]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("ticks"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("num"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("on"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("multiple"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NUM", "([0-9]+)"), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String code = arg.get("PLAYER", 0);
		final Player player = diagram.getPlayer(code);
		if (player == null) {
			return CommandExecutionResult.error("No such participant " + code);
		}
		if (player instanceof PlayerAnalog) {
			((PlayerAnalog) player).setTicks(Integer.parseInt(arg.get("NUM", 0)));
		}
		return CommandExecutionResult.ok();
	}

}

package net.sourceforge.plantuml.sequencediagram.command;

import java.io.IOException;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandSkin extends SingleLineCommand2<TitledDiagram> {

	public static final CommandSkin ME = new CommandSkin();

	private CommandSkin() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSkin.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("skin"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("SKIN", "([\\w.]+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		try {
			return diagram.loadSkin(arg.get("SKIN", 0));
		} catch (IOException e) {
			return CommandExecutionResult.error("Skin read error");
		}
	}
}

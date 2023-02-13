package net.sourceforge.plantuml.salt;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAnything extends SingleLineCommand2<PSystemSalt> {

	public CommandAnything() {
		super(false, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAnything.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("ALL", "(.*)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(PSystemSalt diagram, LineLocation location, RegexResult arg) {
		final String s = arg.get("ALL", 0);
		if (diagram.isIamSalt() == false) {
			if (StringUtils.isEmpty(s)) {
				return CommandExecutionResult.ok();
			}
			return CommandExecutionResult.error("Not ready");
		}
		diagram.add(s);
		return CommandExecutionResult.ok();
	}

}

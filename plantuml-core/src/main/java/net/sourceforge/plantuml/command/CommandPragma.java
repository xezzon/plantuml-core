package net.sourceforge.plantuml.command;

import java.util.StringTokenizer;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandPragma extends SingleLineCommand2<TitledDiagram> {

	public static final CommandPragma ME = new CommandPragma();

	private CommandPragma() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandPragma.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("!pragma"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NAME", "([A-Za-z_][A-Za-z_0-9]*)"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("VALUE", "(.*)") //
						)), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram system, LineLocation location, RegexResult arg) {
		final String name = StringUtils.goLowerCase(arg.get("NAME", 0));
		final String value = arg.get("VALUE", 0);
		if (name.equalsIgnoreCase("svgsize")) {
			if (value.contains(" ")) {
				final StringTokenizer st = new StringTokenizer(value);
				system.getSkinParam().setSvgSize(st.nextToken(), st.nextToken());
			}
		} else {
			system.getPragma().define(name, value);

		}
		return CommandExecutionResult.ok();
	}

}

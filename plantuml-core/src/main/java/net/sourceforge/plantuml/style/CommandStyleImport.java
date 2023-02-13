package net.sourceforge.plantuml.style;

import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.style.parser.StyleParser;
import net.sourceforge.plantuml.style.parser.StyleParsingException;
import net.sourceforge.plantuml.utils.BlocLines;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandStyleImport extends SingleLineCommand2<TitledDiagram> {

	public static final CommandStyleImport ME = new CommandStyleImport();

	private CommandStyleImport() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandStyleImport.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("\\<style"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\w+"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("="), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("[%q%g]?"), //
				new RegexLeaf("PATH", "([^%q%g]*)"), //
				new RegexLeaf("[%q%g]?"), //
				new RegexLeaf("\\>"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram diagram, LineLocation location, RegexResult arg) {
		final String path = arg.get("PATH", 0);
		try {
			final SFile f = FileSystem.getInstance().getFile(path);
			BlocLines lines = null;
			if (f.exists()) {
				lines = BlocLines.load(f, location);
			} else {
				final InputStream internalIs = StyleLoader.class.getResourceAsStream("/skin/" + path);
				if (internalIs != null)
					lines = BlocLines.load(internalIs, location);

			}
			if (lines == null)
				return CommandExecutionResult.error("Cannot read: " + path);

			final StyleBuilder styleBuilder = diagram.getSkinParam().getCurrentStyleBuilder();
			for (Style modifiedStyle : StyleParser.parse(lines, styleBuilder))
				diagram.getSkinParam().muteStyle(modifiedStyle);

		} catch (StyleParsingException e) {
			return CommandExecutionResult.error("Error in style definition: " + e.getMessage());
		} catch (IOException e) {
			return CommandExecutionResult.error("Cannot read: " + path);
		}
		return CommandExecutionResult.ok();
	}
}

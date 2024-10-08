// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.style;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.skin.SkinParam;
import net.sourceforge.plantuml.style.parser.StyleParser;
import net.sourceforge.plantuml.style.parser.StyleParsingException;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandStyleMultilinesCSS extends CommandMultilines2<TitledDiagram> {

	public static final CommandStyleMultilinesCSS ME = new CommandStyleMultilinesCSS();

	private CommandStyleMultilinesCSS() {
		super(getRegexConcat(), MultilinesStrategy.REMOVE_STARTING_QUOTE, Trim.BOTH);
	}

	@Override
	public String getPatternEnd() {
		return "^[%s]*\\</?style\\>[%s]*$";
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandStyleMultilinesCSS.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\<style\\>"), //
				RegexLeaf.end() //
		);
	}

	protected CommandExecutionResult executeNow(TitledDiagram diagram, BlocLines lines) {
		try {
			final StyleBuilder styleBuilder = diagram.getSkinParam().getCurrentStyleBuilder();
			for (Style modifiedStyle : StyleParser.parse(lines.subExtract(1, 1), styleBuilder))
				diagram.getSkinParam().muteStyle(modifiedStyle);

			((SkinParam) diagram.getSkinParam()).applyPendingStyleMigration();
			return CommandExecutionResult.ok();
		} catch (StyleParsingException e) {
			return CommandExecutionResult.error("Error in style definition: " + e.getMessage());
		} catch (NoStyleAvailableException e) {
			// Logme.error(e);
			return CommandExecutionResult.error("General failure: no style available.");
		}
	}

}

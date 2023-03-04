// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ScaleHeight;
import net.sourceforge.plantuml.ScaleWidth;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandScaleWidthOrHeight extends SingleLineCommand2<AbstractPSystem> {

	public static final CommandScaleWidthOrHeight ME = new CommandScaleWidthOrHeight();

	private CommandScaleWidthOrHeight() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandScaleWidthOrHeight.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("scale"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("VALUE", "([0-9.]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WIDTH", "(width|height)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractPSystem diagram, LineLocation location, RegexResult arg) {
		final double size = Double.parseDouble(arg.get("VALUE", 0));
		final boolean width = "width".equalsIgnoreCase(arg.get("WIDTH", 0));
		if (width) {
			diagram.setScale(new ScaleWidth(size));
		} else {
			diagram.setScale(new ScaleHeight(size));
		}
		return CommandExecutionResult.ok();
	}

}

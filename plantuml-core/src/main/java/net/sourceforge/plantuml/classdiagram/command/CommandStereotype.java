// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.abel.Entity;
import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandStereotype extends SingleLineCommand2<ClassDiagram> {

	public CommandStereotype() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandStereotype.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("NAME", "([%pLN_.]+|[%g][^%g]+[%g])"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREO", "(\\<\\<.*\\>\\>)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ClassDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String name = arg.get("NAME", 0);
		final String stereotype = arg.get("STEREO", 0);

		final Quark<Entity> quark = diagram.quarkInContext(true, diagram.cleanId(name));
		final Entity entity = quark.getData();
		if (entity == null)
			return CommandExecutionResult.error("No such class " + quark.getName());

		entity.setStereotype(Stereotype.build(stereotype, diagram.getSkinParam().getCircledCharacterRadius(),
				diagram.getSkinParam().getFont(null, false, FontParam.CIRCLED_CHARACTER),
				diagram.getSkinParam().getIHtmlColorSet()));
		return CommandExecutionResult.ok();
	}

}

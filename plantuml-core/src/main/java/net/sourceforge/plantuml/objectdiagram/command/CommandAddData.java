package net.sourceforge.plantuml.objectdiagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.objectdiagram.AbstractClassOrObjectDiagram;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAddData extends SingleLineCommand2<AbstractClassOrObjectDiagram> {

	public CommandAddData() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAddData.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("NAME", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(":"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("DATA", "(.*)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractClassOrObjectDiagram diagram, LineLocation location,
			RegexResult arg) throws NoSuchColorException {
		final String name = arg.get("NAME", 0);
		final Quark<Entity> quark = diagram.quarkInContext(diagram.cleanId(name), false);
		final Entity entity = quark.getData();
		if (entity == null)
			return CommandExecutionResult.error("No such entity " + quark.getName());

		final String field = arg.get("DATA", 0);
		if (field.length() > 0 && VisibilityModifier.isVisibilityCharacter(field))
			diagram.setVisibilityModifierPresent(true);

		entity.getBodier().addFieldOrMethod(field);
		return CommandExecutionResult.ok();
	}
}

package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.plasma.Quark;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.statediagram.StateDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAddField extends SingleLineCommand2<StateDiagram> {

	public CommandAddField() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAddField.class.getName(), RegexLeaf.start(), //
				new RegexOr( //
						new RegexLeaf("CODE3", "([%pLN_.]+)"), //
						new RegexLeaf("CODE4", "[%g]([^%g]+)[%g]")), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(":"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("FIELD", "(.*)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(StateDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String codeString = arg.getLazzy("CODE", 0);

		final Quark<Entity> quark;
		if (diagram.getCurrentGroup().getName().equals(codeString))
			quark = diagram.getCurrentGroup().getQuark();
		else
			quark = diagram.quarkInContext(diagram.cleanId(codeString), false);

		Entity entity = quark.getData();
		if (entity == null)
			entity = diagram.reallyCreateLeaf(quark, Display.getWithNewlines(quark), LeafType.STATE, null);

		final String field = arg.get("FIELD", 0);

		entity.getBodier().addFieldOrMethod(field);
		return CommandExecutionResult.ok();

	}

}

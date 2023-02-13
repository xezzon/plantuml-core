package net.sourceforge.plantuml.descdiagram.command;

import net.sourceforge.plantuml.NewpagedDiagram;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNewpage extends SingleLineCommand2<UmlDiagram> {

	private final PSystemCommandFactory factory;

	public CommandNewpage(PSystemCommandFactory factory) {
		super(getRegexConcat());
		this.factory = factory;
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNewpage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("newpage"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, LineLocation location, RegexResult arg) {
		final int dpi = diagram.getSkinParam().getDpi();
		final UmlDiagram emptyDiagram = (UmlDiagram) factory.createEmptyDiagram(diagram.getSource(),
				diagram.getSkinParam().values());
		if (dpi != 96)
			emptyDiagram.setParam("dpi", "" + dpi);

		final NewpagedDiagram result = new NewpagedDiagram(diagram.getSource(), diagram, emptyDiagram);
		return CommandExecutionResult.newDiagram(result);
	}
}

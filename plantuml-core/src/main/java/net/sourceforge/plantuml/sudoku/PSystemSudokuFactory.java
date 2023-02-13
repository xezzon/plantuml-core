package net.sourceforge.plantuml.sudoku;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.PSystemSingleLineFactory;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;

public class PSystemSudokuFactory extends PSystemSingleLineFactory {

	final private static Pattern2 p = MyPattern.cmpile("^sudoku(?:[%s]+([0-9a-zA-Z]+))?[%s]*$");

	@Override
	protected AbstractPSystem executeLine(UmlSource source, String line) {
		final Matcher2 m = p.matcher(line);
		if (m.find() == false) {
			return null;
		}

		if (m.group(1) == null) {
			return new PSystemSudoku(source, null);
		}
		return new PSystemSudoku(source, Long.parseLong(StringUtils.goLowerCase(m.group(1)), 36));
	}

}

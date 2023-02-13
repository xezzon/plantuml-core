package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;

public class SubjectDayAsDate implements Subject {

	public static final Subject ME = new SubjectDayAsDate();

	private SubjectDayAsDate() {
	}

	public Failable<Day> getMe(GanttDiagram project, RegexResult arg) {
		if (arg.get("BDAY", 0) != null)
			return Failable.ok(resultB(arg));

		if (arg.get("ECOUNT", 0) != null)
			return Failable.ok(resultE(project, arg));

		throw new IllegalStateException();

	}

	private Day resultB(RegexResult arg) {
		final int day = Integer.parseInt(arg.get("BDAY", 0));
		final int month = Integer.parseInt(arg.get("BMONTH", 0));
		final int year = Integer.parseInt(arg.get("BYEAR", 0));
		return Day.create(year, month, day);
	}

	private Day resultE(GanttDiagram system, RegexResult arg) {
		final int day = Integer.parseInt(arg.get("ECOUNT", 0));
		return system.getStartingDate().addDays(day);
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new Close(), new Open(), new InColor());
	}

	class Close extends SentenceSimple {

		public Close() {
			super(SubjectDayAsDate.this, Verbs.isOrAre, new ComplementClose());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			project.closeDayAsDate((Day) subject, (String) complement);
			return CommandExecutionResult.ok();
		}
	}

	class Open extends SentenceSimple {
		public Open() {
			super(SubjectDayAsDate.this, Verbs.isOrAre, new ComplementOpen());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			project.openDayAsDate((Day) subject, (String) complement);
			return CommandExecutionResult.ok();
		}
	}

	class InColor extends SentenceSimple {

		public InColor() {
			super(SubjectDayAsDate.this, Verbs.isOrAre, new ComplementInColors2());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final HColor color = ((CenterBorderColor) complement).getCenter();
			project.colorDay((Day) subject, color);
			return CommandExecutionResult.ok();
		}

	}

	public IRegex toRegex() {
		return new RegexOr(toRegexB(), toRegexE());
	}

	private IRegex toRegexB() {
		return new RegexConcat( //
				new RegexLeaf("BYEAR", "([\\d]{4})"), //
				new RegexLeaf("\\D"), //
				new RegexLeaf("BMONTH", "([\\d]{1,2})"), //
				new RegexLeaf("\\D"), //
				new RegexLeaf("BDAY", "([\\d]{1,2})"));
	}

	private IRegex toRegexE() {
		return new RegexConcat( //
				new RegexLeaf("[dD]\\+"), //
				new RegexLeaf("ECOUNT", "([\\d]+)") //
		);
	}

}

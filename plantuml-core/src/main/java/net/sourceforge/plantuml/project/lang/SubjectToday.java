package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.Today;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class SubjectToday implements Subject {

	public static final Subject ME = new SubjectToday();

	private SubjectToday() {
	}

	public IRegex toRegex() {
		return new RegexConcat( //
				new RegexLeaf("today") //
		);
	}

	public Failable<Today> getMe(GanttDiagram project, RegexResult arg) {
		return Failable.ok(new Today());
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new InColor(), new IsDate());
	}

	class InColor extends SentenceSimple {

		public InColor() {
			super(SubjectToday.this, Verbs.isColored, new ComplementInColors());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Today task = (Today) subject;
			final CenterBorderColor colors = (CenterBorderColor) complement;
			project.setTodayColors(colors);
			return CommandExecutionResult.ok();

		}

	}

	class IsDate extends SentenceSimple {

		public IsDate() {
			super(SubjectToday.this, Verbs.is, new ComplementDate());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Day date = (Day) complement;
			return project.setToday(date);
		}

	}

}

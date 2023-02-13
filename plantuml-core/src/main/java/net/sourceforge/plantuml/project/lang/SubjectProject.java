package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class SubjectProject implements Subject {

	public static final Subject ME = new SubjectProject();

	private SubjectProject() {
	}

	public IRegex toRegex() {
		return new RegexLeaf("SUBJECT", "project");
	}

	public Failable<GanttDiagram> getMe(GanttDiagram project, RegexResult arg) {
		return Failable.ok(project);
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new Starts());
	}

	class Starts extends SentenceSimple {

		public Starts() {
			super(SubjectProject.this, Verbs.starts, new ComplementDate());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final Day start = (Day) complement;
			assert project == subject;
			project.setStartingDate(start);
			return CommandExecutionResult.ok();
		}

	}

}

package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.DaysAsDates;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.time.Day;

public class SentencePausesDates extends SentenceSimple {

	public SentencePausesDates() {
		super(SubjectTask.ME, Verbs.pauses, new ComplementDates());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final DaysAsDates pauses = (DaysAsDates) complement;
//		final Day startingDate = project.getStartingDate();
//		if (startingDate == null) {
//			return CommandExecutionResult.error("No starting date for the project");
//		}
		for (Day day : pauses) {
			task.addPause(day);
		}
		return CommandExecutionResult.ok();
	}

}

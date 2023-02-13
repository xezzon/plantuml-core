package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.time.Day;

public class SentenceTaskStartsAbsolute extends SentenceSimple {

	public SentenceTaskStartsAbsolute() {
		super(SubjectTask.ME, Verbs.starts3, new ComplementDate());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final Day start = (Day) complement;
//		final Day startingDate = project.getStartingDate();
//		if (startingDate == null) {
//			return CommandExecutionResult.error("No starting date for the project");
//		}
		task.setStart(start);
		return CommandExecutionResult.ok();
	}

}

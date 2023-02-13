package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;

public class SentenceIsDeleted extends SentenceSimple {

	public SentenceIsDeleted() {
		super(SubjectTask.ME, Verbs.isDeleted, new ComplementEmpty());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		return project.deleteTask(task);
	}

}

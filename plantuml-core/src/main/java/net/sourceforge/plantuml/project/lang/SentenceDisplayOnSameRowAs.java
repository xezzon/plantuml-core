package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;

public class SentenceDisplayOnSameRowAs extends SentenceSimple {

	public SentenceDisplayOnSameRowAs() {
		super(SubjectTask.ME, Verbs.displayOnSameRowAs, new ComplementNamed());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task1 = (Task) subject;
		final Task task2 = project.getExistingTask((String) complement);
		if (task2 == null)
			return CommandExecutionResult.error("No such task " + task2);

		task1.putInSameRowAs(task2);
		return CommandExecutionResult.ok();
	}

}

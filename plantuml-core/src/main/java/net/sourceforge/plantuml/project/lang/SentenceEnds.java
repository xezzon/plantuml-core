package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttConstraint;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.core.TaskInstant;

public class SentenceEnds extends SentenceSimple {

	public SentenceEnds() {
		super(SubjectTask.ME, Verbs.ends, new ComplementBeforeOrAfterOrAtTaskStartOrEnd());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final TaskInstant when = (TaskInstant) complement;
		task.setEnd(when.getInstantPrecise().decrement());
		project.addContraint(new GanttConstraint(project.getIHtmlColorSet(), project.getCurrentStyleBuilder(), when,
				new TaskInstant(task, TaskAttribute.END)));
		return CommandExecutionResult.ok();
	}

}

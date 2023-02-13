package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskInstant;

public class SentenceHappens extends SentenceSimple {

	public SentenceHappens() {
		super(SubjectTask.ME, Verbs.happens, new ComplementBeforeOrAfterOrAtTaskStartOrEnd());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		task.setLoad(Load.inWinks(1));
		final TaskInstant when = (TaskInstant) complement;
		task.setStart(when.getInstantTheorical());
		task.setDiamond(true);
		return CommandExecutionResult.ok();
	}

}

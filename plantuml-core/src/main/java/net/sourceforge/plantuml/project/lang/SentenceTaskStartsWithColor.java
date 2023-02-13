package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.GanttConstraint;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.core.TaskAttribute;
import net.sourceforge.plantuml.project.core.TaskInstant;

public class SentenceTaskStartsWithColor extends SentenceSimple {

	public SentenceTaskStartsWithColor() {
		super(SubjectTask.ME, Verbs.starts2,
				new PairOfSomething(new ComplementBeforeOrAfterOrAtTaskStartOrEnd(), new ComplementWithColorLink()));
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram diagram, Object subject, Object complement) {
		final Task task = (Task) subject;
		final TaskInstant when;

		final Object[] pairs = (Object[]) complement;
		when = (TaskInstant) pairs[0];
		final CenterBorderColor complement22 = (CenterBorderColor) pairs[1];

		task.setStart(when.getInstantPrecise());
		if (when.isTask()) {
			final HColor color = complement22.getCenter();
			final GanttConstraint link = new GanttConstraint(diagram.getIHtmlColorSet(),
					diagram.getCurrentStyleBuilder(), when, new TaskInstant(task, TaskAttribute.START), color);
			link.applyStyle(complement22.getStyle());
			diagram.addContraint(link);
		}
		return CommandExecutionResult.ok();

	};
}

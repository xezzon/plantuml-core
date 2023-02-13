package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;

public class SentenceIsColored extends SentenceSimple {

	public SentenceIsColored() {
		super(SubjectTask.ME, Verbs.isColored, new ComplementInColors());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final CenterBorderColor colors = (CenterBorderColor) complement;
		task.setColors(colors);
		return CommandExecutionResult.ok();
	}

}

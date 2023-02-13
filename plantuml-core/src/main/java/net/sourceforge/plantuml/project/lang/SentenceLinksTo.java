package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.url.Url;

public class SentenceLinksTo extends SentenceSimple {

	public SentenceLinksTo() {
		super(SubjectTask.ME, Verbs.linksTo, new ComplementUrl());
	}

	@Override
	public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
		final Task task = (Task) subject;
		final Url url = (Url) complement;
		task.setUrl(url);
		return CommandExecutionResult.ok();
	}

}

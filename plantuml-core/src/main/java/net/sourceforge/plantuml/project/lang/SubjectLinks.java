package net.sourceforge.plantuml.project.lang;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

// Removed
public class SubjectLinks implements Subject {
	
	private SubjectLinks() {
	}


	public IRegex toRegex() {
		return new RegexLeaf("SUBJECT", "links?");
	}

	public Failable<GanttDiagram> getMe(GanttDiagram project, RegexResult arg) {
		return Failable.ok(project);
	}

	public Collection<? extends SentenceSimple> getSentences() {
		return Arrays.asList(new InColor());
	}

	public class InColor extends SentenceSimple {

		public InColor() {
			super(SubjectLinks.this, Verbs.areColored, new ComplementInColors());
		}

		@Override
		public CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement) {
			final CenterBorderColor colors = (CenterBorderColor) complement;
			// project.setLinksColor(colors.getCenter());
			return CommandExecutionResult.ok();

		}

	}
}

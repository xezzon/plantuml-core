package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public abstract class SentenceSimple implements Sentence {

	protected final Subject subjectii;
	private final IRegex verb;
	protected final Something complementii;

	public SentenceSimple(Subject subject, IRegex verb, Something complement) {
		this.subjectii = subject;
		this.verb = verb;
		this.complementii = complement;
	}

	public String getSignature() {
		return subjectii.getClass() + "/" + verb.getPattern() + "/" + complementii.getClass();
	}

	public final IRegex toRegex() {
		if (complementii instanceof ComplementEmpty)
			return new RegexConcat(//
					RegexLeaf.start(), //
					subjectii.toRegex(), //
					RegexLeaf.spaceOneOrMore(), //
					verb, //
					RegexLeaf.end());

		return new RegexConcat(//
				RegexLeaf.start(), //
				subjectii.toRegex(), //
				RegexLeaf.spaceOneOrMore(), //
				verb, //
				RegexLeaf.spaceOneOrMore(), //
				complementii.toRegex("0"), //
				RegexLeaf.end());
	}

	public final CommandExecutionResult execute(GanttDiagram project, RegexResult arg) {
		final Failable<? extends Object> subject = subjectii.getMe(project, arg);
		if (subject.isFail())
			return CommandExecutionResult.error(subject.getError());

		final Failable<? extends Object> complement = complementii.getMe(project, arg, "0");
		if (complement.isFail())
			return CommandExecutionResult.error(complement.getError());

		return execute(project, subject.get(), complement.get());

	}

	public abstract CommandExecutionResult execute(GanttDiagram project, Object subject, Object complement);

	public IRegex getVerbRegex() {
		return verb;
	}

}

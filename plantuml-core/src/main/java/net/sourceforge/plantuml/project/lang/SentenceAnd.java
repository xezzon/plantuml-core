package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class SentenceAnd implements Sentence {

	private final SentenceSimple sentence1;
	private final SentenceSimple sentence2;

	public SentenceAnd(SentenceSimple sentence1, SentenceSimple sentence2) {
		this.sentence1 = sentence1;
		this.sentence2 = sentence2;
	}

	public IRegex toRegex() {
		return new RegexConcat(//
				RegexLeaf.start(), //
				sentence1.subjectii.toRegex(), //
				RegexLeaf.spaceOneOrMore(), //
				sentence1.getVerbRegex(), //
				RegexLeaf.spaceOneOrMore(), //
				sentence1.complementii.toRegex("1"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("and"), //
				RegexLeaf.spaceOneOrMore(), //
				sentence2.getVerbRegex(), //
				RegexLeaf.spaceOneOrMore(), //
				sentence2.complementii.toRegex("2"), //
				RegexLeaf.end());
	}

	public final CommandExecutionResult execute(GanttDiagram project, RegexResult arg) {
		final Failable<? extends Object> subject = sentence1.subjectii.getMe(project, arg);
		if (subject.isFail()) {
			return CommandExecutionResult.error(subject.getError());
		}
		final Failable<? extends Object> complement1 = sentence1.complementii.getMe(project, arg, "1");
		if (complement1.isFail()) {
			return CommandExecutionResult.error(complement1.getError());
		}
		final CommandExecutionResult result1 = sentence1.execute(project, subject.get(), complement1.get());
		if (result1.isOk() == false) {
			return result1;
		}
		final Failable<? extends Object> complement2 = sentence2.complementii.getMe(project, arg, "2");
		if (complement2.isFail()) {
			return CommandExecutionResult.error(complement2.getError());
		}
		return sentence2.execute(project, subject.get(), complement2.get());

	}

}

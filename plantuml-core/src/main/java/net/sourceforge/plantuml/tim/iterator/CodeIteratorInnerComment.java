package net.sourceforge.plantuml.tim.iterator;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;

public class CodeIteratorInnerComment extends AbstractCodeIterator {

	public CodeIteratorInnerComment(CodeIterator source) {
		super(source);
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		final StringLocated result = source.peek();
		if (result == null) {
			return null;
		}
		return result.removeInnerComment();

	}
}

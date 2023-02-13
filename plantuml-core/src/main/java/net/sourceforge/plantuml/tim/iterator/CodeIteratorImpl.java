package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.EaterException;

public class CodeIteratorImpl implements CodeIterator {

	private final List<StringLocated> list;
	private int current = 0;
	private int countJump = 0;

	static class Position implements CodePosition {
		final int pos;

		Position(int pos) {
			this.pos = pos;
		}

//		@Override
//		public String toString() {
//			return "-->" + list.get(pos);
//		}
	}

	public CodeIteratorImpl(List<StringLocated> list) {
		this.list = list;
	}

	public StringLocated peek() {
		if (current == list.size()) {
			return null;
		}
		if (current > list.size()) {
			throw new IllegalStateException();
		}
		return list.get(current);
	}

	public void next() {
		if (current >= list.size()) {
			throw new IllegalStateException();
		}
		assert current < list.size();
		current++;
		assert current <= list.size();
	}

	public CodePosition getCodePosition() {
		return new Position(current);
	}

	public void jumpToCodePosition(CodePosition newPosition) throws EaterException {
		this.countJump++;
		if (this.countJump > 999) {
			throw EaterException.unlocated("Infinite loop?");
		}
		final Position pos = (Position) newPosition;
		this.current = pos.pos;

	}

}

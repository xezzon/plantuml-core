package net.sourceforge.plantuml.version;

import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;

public interface IteratorCounter2 extends Iterator<StringLocated> {

	public int currentNum();

	public IteratorCounter2 cloneMe();

	public StringLocated peek();

	public StringLocated peekPrevious();

	public void copyStateFrom(IteratorCounter2 other);

	public List<StringLocated> getTrace();

}

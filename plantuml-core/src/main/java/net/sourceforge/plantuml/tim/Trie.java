package net.sourceforge.plantuml.tim;

public interface Trie {

	public void add(String s);

	public String getLonguestMatchStartingIn(String s);

}

package net.sourceforge.plantuml.hcl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HclSource implements Iterable<Character> {

	final private List<Character> all = new ArrayList<>();

	public void add(String line) {
		if (line.trim().startsWith("#"))
			return;
		for (char c : line.toCharArray())
			all.add(c);
	}

	@Override
	public Iterator<Character> iterator() {
		return Collections.unmodifiableList(all).iterator();
	}

}

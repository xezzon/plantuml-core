package net.sourceforge.plantuml.preproc2;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.preproc.ReadLine;

public class ReadFilterAnd implements ReadFilter {

	private final Collection<ReadFilter> all = new ArrayList<>();

	public void add(ReadFilter filter) {
		all.add(filter);
	}

	public ReadLine applyFilter(ReadLine current) {
		for (ReadFilter f : all)
			current = f.applyFilter(current);

		return current;
	}

}

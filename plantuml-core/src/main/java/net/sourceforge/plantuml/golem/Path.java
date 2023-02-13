package net.sourceforge.plantuml.golem;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private final List<TileArea> all = new ArrayList<>();

	public static Path build(TileArea start, TileArea dest) {
		return new Path(start, dest);
	}

	private Path(TileArea start, TileArea dest) {
		this.all.add(start);
		this.all.add(dest);
	}

	public TileArea getStart() {
		return all.get(0);
	}

	public TileArea getDest() {
		return all.get(all.size() - 1);
	}

}

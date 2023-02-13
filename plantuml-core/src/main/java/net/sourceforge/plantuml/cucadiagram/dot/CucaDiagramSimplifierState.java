package net.sourceforge.plantuml.cucadiagram.dot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.cucadiagram.GroupType;
import net.sourceforge.plantuml.cucadiagram.ICucaDiagram;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.svek.GroupMakerState;
import net.sourceforge.plantuml.svek.IEntityImage;

public final class CucaDiagramSimplifierState {

	private final ICucaDiagram diagram;
	private final StringBounder stringBounder;

	public CucaDiagramSimplifierState(ICucaDiagram diagram, List<String> dotStrings, StringBounder stringBounder)
			throws IOException, InterruptedException {
		this.diagram = diagram;
		this.stringBounder = stringBounder;
		boolean changed;
		do {
			changed = false;
			final Collection<Entity> groups = getOrdered(diagram.getRootGroup());
			for (Entity g : groups)
				if (g.isAutarkic()) {
					final IEntityImage img = computeImage(g);
					g.overrideImage(img, g.getGroupType() == GroupType.CONCURRENT_STATE ? LeafType.STATE_CONCURRENT
							: LeafType.STATE);

					changed = true;
				}

		} while (changed);
	}

	private Collection<Entity> getOrdered(Entity root) {
		final Collection<Entity> ordered = new LinkedHashSet<>();
		ordered.add(root);
		int size = 1;
		while (true) {
			size = ordered.size();
			addOneLevel(ordered);
			if (size == ordered.size())
				break;

		}
		final List<Entity> result = new ArrayList<>();
		for (Entity g : ordered)
			if (g.isRoot() == false)
				result.add(0, g);

		return result;
	}

	private void addOneLevel(Collection<Entity> currents) {
		for (Entity g : new ArrayList<>(currents))
			for (Entity child : reverse(g.groups()))
				currents.add(child);

	}

	private List<Entity> reverse(Collection<Entity> source) {
		final List<Entity> result = new ArrayList<>();
		for (Entity g : source)
			result.add(0, g);

		return result;
	}

	private IEntityImage computeImage(Entity g) throws IOException, InterruptedException {
		final GroupMakerState maker = new GroupMakerState(diagram, g, stringBounder);
		return maker.getImage();
	}

}

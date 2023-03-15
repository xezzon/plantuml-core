// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.compress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public class SlotSet implements Iterable<Slot> {

	private final List<Slot> all = new ArrayList<>();

	public SlotSet filter(double start, double end) {
		final SlotSet result = new SlotSet();
		for (Slot slot : all) {
			final Slot intersec = slot.intersect(start, end);
			if (intersec != null) 
				result.all.add(intersec);
			
		}
		return result;
	}

	public void addAll(SlotSet other) {
		this.all.addAll(other.all);
	}

	public void addSlot(double start, double end) {
		final List<Slot> collisions = new ArrayList<>();
		Slot newSlot = new Slot(start, end);
		for (final Iterator<Slot> it = all.iterator(); it.hasNext();) {
			final Slot s = it.next();
			if (s.intersect(newSlot)) {
				it.remove();
				collisions.add(s);
			}
		}
		for (Slot s : collisions) 
			newSlot = newSlot.merge(s);
		
		all.add(newSlot);
	}

	public SlotSet smaller(double margin) {
		final SlotSet result = new SlotSet();
		for (Slot sl : all) {
			if (sl.size() <= 2 * margin) 
				continue;
			
			result.addSlot(sl.getStart() + margin, sl.getEnd() - margin);
		}
		return result;
	}

	@Override
	public String toString() {
		return all.toString();
	}

	public List<Slot> getSlots() {
		return Collections.unmodifiableList(all);
	}

	public Iterator<Slot> iterator() {
		return getSlots().iterator();
	}

	public SlotSet reverse() {
		final SlotSet result = new SlotSet();
		Collections.sort(all);
		Slot last = null;
		for (Slot slot : all) {
			if (last != null) 
				result.addSlot(last.getEnd(), slot.getStart());
			
			last = slot;
		}
		return result;
	}

	public void drawDebugX(UGraphic ug, double size) {
		for (Slot slot : all) {
			final URectangle rect = URectangle.build(slot.getEnd() - slot.getStart(), size);
			ug.apply(UTranslate.dx(slot.getStart())).draw(rect);
		}
	}

}

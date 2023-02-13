package net.sourceforge.plantuml.nwdiag.next;

import java.util.Objects;

public class NBar implements Staged {

	private NBox parent;
	private NStage start;
	private NStage end;

	@Override
	public String toString() {
		return start + "->" + end;
	}

	public final NBox getParent() {
		return parent;
	}

	public final void setParent(NBox parent) {
		this.parent = parent;
	}

	@Override
	public final NStage getStart() {
		return start;
	}

	@Override
	public final NStage getEnd() {
		return end;
	}

	public void addStage(NStage stage) {
		Objects.requireNonNull(stage);
		if (start == null && end == null) {
			this.start = stage;
			this.end = stage;
		} else {
			this.start = NStage.getMin(this.start, stage);
			this.end = NStage.getMax(this.end, stage);
		}
	}

	@Override
	public int getNWidth() {
		return 1;
	}

	@Override
	public boolean contains(NStage stage) {
		return stage.compareTo(start) >= 0 && stage.compareTo(end) <= 0;
	}

}

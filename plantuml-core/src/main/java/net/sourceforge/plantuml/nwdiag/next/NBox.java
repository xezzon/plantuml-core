package net.sourceforge.plantuml.nwdiag.next;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NBox implements Staged {

	private final List<NBar> bars = new ArrayList<>();
	private final NTetris<NBar> tetris = new NTetris<>();

	public void add(NBar bar) {
		if (this.bars.contains(bar))
			return;

		this.bars.add(bar);
		this.tetris.add(bar);
	}

	@Override
	public NStage getStart() {
		NStage result = bars.get(0).getStart();
		for (int i = 1; i < bars.size(); i++)
			result = NStage.getMin(result, bars.get(i).getStart());

		return result;
	}

	@Override
	public NStage getEnd() {
		NStage result = bars.get(0).getEnd();
		for (int i = 1; i < bars.size(); i++)
			result = NStage.getMax(result, bars.get(i).getEnd());

		return result;
	}

	@Override
	public int getNWidth() {
		return tetris.getNWidth();
	}

	public Map<NBar, Integer> getPositions() {
		return tetris.getPositions();
	}

	@Override
	public boolean contains(NStage stage) {
		return stage.compareTo(getStart()) >= 0 && stage.compareTo(getEnd()) <= 0;
	}

}

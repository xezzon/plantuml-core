package net.sourceforge.plantuml.nwdiag.next;

public class NStage implements Comparable<NStage> {

	private final int number;

	public NStage(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "S" + number;
	}

	@Override
	public int compareTo(NStage other) {
		return Integer.compare(this.number, other.number);
	}

	public int getNumber() {
		return number;
	}

	public static NStage getMin(NStage stage1, NStage stage2) {
		if (stage1.number < stage2.number)
			return stage1;

		return stage2;
	}

	public static NStage getMax(NStage stage1, NStage stage2) {
		if (stage1.number > stage2.number)
			return stage1;

		return stage2;
	}

}

package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public abstract class AbstractConnection implements Connection {

	private final Ftile ftile1;
	private final Ftile ftile2;

	public AbstractConnection(Ftile ftile1, Ftile ftile2) {
		this.ftile1 = ftile1;
		this.ftile2 = ftile2;
	}

	@Override
	public String toString() {
		return "[" + ftile1 + "]->[" + ftile2 + "]";
	}

	final public Ftile getFtile1() {
		return ftile1;
	}

	final public Ftile getFtile2() {
		return ftile2;
	}

	final public HorizontalAlignment arrowHorizontalAlignment() {
		if (ftile1 != null) {
			return ftile1.arrowHorizontalAlignment();
		}
		if (ftile2 != null) {
			return ftile2.arrowHorizontalAlignment();
		}
		return HorizontalAlignment.LEFT;
	}

}

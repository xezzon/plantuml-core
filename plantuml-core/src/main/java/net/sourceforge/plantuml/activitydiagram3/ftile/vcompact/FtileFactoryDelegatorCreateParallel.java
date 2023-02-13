package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.ForkStyle;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;

public final class FtileFactoryDelegatorCreateParallel extends FtileFactoryDelegator {

	public FtileFactoryDelegatorCreateParallel(FtileFactory factory) {
		super(factory);
	}

	@Override
	public Ftile createParallel(List<Ftile> all, ForkStyle style, String label, Swimlane in, Swimlane out) {

		AbstractParallelFtilesBuilder builder;
		if (style == ForkStyle.SPLIT)
			builder = new ParallelBuilderSplit(skinParam(), getStringBounder(), all);
		else if (style == ForkStyle.MERGE)
			builder = new ParallelBuilderMerge(skinParam(), getStringBounder(), all);
		else if (style == ForkStyle.FORK)
			builder = new ParallelBuilderFork(skinParam(), getStringBounder(), label, in, out, all);
		else
			throw new IllegalStateException();

		final Ftile inner = super.createParallel(builder.list99, style, label, in, out);
		return builder.build(inner);
	}

}

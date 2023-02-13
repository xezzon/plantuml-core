package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileWithUrl;
import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileBox;
import net.sourceforge.plantuml.url.Url;

public class FtileFactoryDelegatorAddUrl extends FtileFactoryDelegator {

	public FtileFactoryDelegatorAddUrl(FtileFactory factory) {
		super(factory);
	}

	@Override
	public Ftile addUrl(Ftile ftile, Url url) {
		if (ftile instanceof FtileBox) {
			return new FtileWithUrl(ftile, url);
		}
		return ftile;
	}
}

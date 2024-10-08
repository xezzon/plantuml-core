// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.svek.EntityDomain;

class USymbolEntityDomain extends USymbolSimpleAbstract {

	@Override
	public SName getSName() {
		return SName.entity;
	}

	@Override
	protected TextBlock getDrawing(final Fashion symbolContext) {
		return new EntityDomain(symbolContext.withDeltaShadow(symbolContext.isShadowing() ? 4.0 : 0.0));
	}
}

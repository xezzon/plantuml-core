// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.svek;

import java.util.concurrent.atomic.AtomicInteger;

public class ColorSequence {

	private final AtomicInteger cpt = new AtomicInteger(1);

	public int getValue() {
		return cpt.addAndGet(1);
	}

}

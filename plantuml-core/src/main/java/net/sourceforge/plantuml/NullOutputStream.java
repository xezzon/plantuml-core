package net.sourceforge.plantuml;

import java.io.IOException;
import java.io.OutputStream;

// Modified by Maxime Sinclair
public class NullOutputStream extends OutputStream {

	/**
	 * Writes to nowhere
	 */
	@Override
	public void write(int b) throws IOException {
		// Do nothing silently
	}

	/**
	 * Overridden for performance reason
	 */
	@Override
	public void write(byte b[]) throws IOException {
		// Do nothing silently
	}

	/**
	 * Overridden for performance reason
	 */
	@Override
	public void write(byte b[], int off, int len) throws IOException {
		// Do nothing silently
	}

}

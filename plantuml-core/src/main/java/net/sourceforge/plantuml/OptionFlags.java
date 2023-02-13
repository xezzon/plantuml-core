package net.sourceforge.plantuml;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SecurityUtils;
import net.sourceforge.plantuml.utils.Log;

public class OptionFlags {

	private static final OptionFlags singleton = new OptionFlags();
	static public final boolean STRICT_SELFMESSAGE_POSITION = true;
	static public final boolean USE_INTERFACE_EYE1 = false;
	static public final boolean USE_INTERFACE_EYE2 = false;
	static public final boolean FORCE_TEOZ = false;

	private boolean replaceWhiteBackgroundByTransparent;

	public static OptionFlags getInstance() {
		return singleton;
	}

	public final boolean isReplaceWhiteBackgroundByTransparent() {
		return replaceWhiteBackgroundByTransparent;
	}

	public final void setReplaceWhiteBackgroundByTransparent(boolean replaceWhiteBackgroundByTransparent) {
		this.replaceWhiteBackgroundByTransparent = replaceWhiteBackgroundByTransparent;
	}

}

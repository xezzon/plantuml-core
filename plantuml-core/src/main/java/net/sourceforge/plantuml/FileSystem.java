package net.sourceforge.plantuml;

import java.io.IOException;

import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SecurityUtils;
import net.sourceforge.plantuml.utils.Log;

public class FileSystem {

	private final static FileSystem singleton = new FileSystem();

	private ThreadLocal<String> currentDir = new ThreadLocal<>();

	private FileSystem() {
		reset();
	}

	public static FileSystem getInstance() {
		return singleton;
	}

	public void setCurrentDir(SFile dir) {
		if (dir == null) {
			this.currentDir.set(null);
		} else {
			Log.info("Setting current dir: " + dir.getAbsolutePath());
			this.currentDir.set(dir.getAbsolutePath());
		}
	}

	public SFile getCurrentDir() {

		return null;
	}

	public SFile getFile(String nameOrPath) throws IOException {
		 return null;

	}


	public void reset() {
		setCurrentDir(new SFile("."));
	}

}

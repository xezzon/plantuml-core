package net.sourceforge.plantuml;

import java.io.IOException;

import net.sourceforge.plantuml.security.SFile;

public class AParentFolderRegular implements AParentFolder {

	private final SFile dir;

	public AParentFolderRegular(SFile dir) {
		this.dir = dir;
		// Log.info("Creating AParentFolderRegular " + dir);
	}

	@Override
	public String toString() {
		return "AParentFolderRegular::" + (dir == null ? "NULL" : dir.getPrintablePath());
	}

	public AFile getAFile(String nameOrPath) throws IOException {
		final SFile filecurrent;
		// Log.info("AParentFolderRegular::looking for " + nameOrPath);
		// Log.info("AParentFolderRegular::dir = " + dir);
		if (dir == null) {
			filecurrent = new SFile(nameOrPath);
		} else {
			filecurrent = dir.getAbsoluteFile().file(nameOrPath);
		}
		// Log.info("AParentFolderRegular::Filecurrent " + filecurrent);
		if (filecurrent.exists()) {
			return new AFileRegular(filecurrent.getCanonicalFile());
		}
		return null;
	}

}

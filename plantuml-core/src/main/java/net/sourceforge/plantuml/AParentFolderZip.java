package net.sourceforge.plantuml;

import java.io.IOException;

import net.sourceforge.plantuml.security.SFile;

public class AParentFolderZip implements AParentFolder {

	private final SFile zipFile;
	private final String parent;

	@Override
	public String toString() {
		return "AParentFolderZip::" + zipFile + " " + parent;
	}

	public AParentFolderZip(SFile zipFile, String entry) {
		this.zipFile = zipFile;
		final int idx = entry.lastIndexOf('/');
		if (idx == -1) {
			parent = "";
		} else {
			parent = entry.substring(0, idx + 1);
		}
	}

	public AFile getAFile(String nameOrPath) throws IOException {
		return new AFileZipEntry(zipFile, merge(parent + nameOrPath));
	}

	String merge(String full) {
		// full = full.replaceFirst("\\.", "Z");
		while (true) {
			int len = full.length();
			full = full.replaceFirst("[^/]+/\\.\\./", "");
			if (full.length() == len) {
				return full;
			}
		}
	}

}

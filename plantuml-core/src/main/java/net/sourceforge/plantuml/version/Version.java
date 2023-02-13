package net.sourceforge.plantuml.version;

import java.util.Date;

import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SURL;

public class Version {

	private static final int MAJOR_SEPARATOR = 1000000;

	public static int version() {
		return 1202301;
	}

	public static int versionPatched() {
		if (beta() != 0) {
			return version() + 1;
		}
		return version();
	}

	public static String versionString() {
		if (beta() != 0) {
			return dotted(version() + 1) + "beta" + beta();
		}
		return dotted(version());
	}

	public static String fullDescription() {
		return "PlantUML version " + Version.versionString() + " (" + Version.compileTimeString() + ")";
	}

	private static String dotted(int nb) {
		final String minor = "" + nb % MAJOR_SEPARATOR;
		final String major = "" + nb / MAJOR_SEPARATOR;
		return major + "." + minor.substring(0, 4) + "." + Integer.parseInt(minor.substring(4));
	}

	public static String versionString(int size) {
		final StringBuilder sb = new StringBuilder(versionString());
		while (sb.length() < size) {
			sb.append(' ');
		}
		return sb.toString();
	}

	public static int beta() {
		final int beta = 4;
		return beta;
	}

	public static String etag() {
		return Integer.toString(version() % MAJOR_SEPARATOR - 201670, 36) + Integer.toString(beta(), 36);
	}

	public static String turningId() {
		return etag();
	}

	public static long compileTime() {
		return 1674997136325L;
	}

	public static String compileTimeString() {
		if (beta() != 0) {
			return "Unknown compile time";
		}
		return new Date(Version.compileTime()).toString();
	}


}

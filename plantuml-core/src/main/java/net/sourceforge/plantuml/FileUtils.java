package net.sourceforge.plantuml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.utils.Log;

// Used by the Eclipse Plugin, so do not change package location.
public class FileUtils {

	private static AtomicInteger counter;

	public static void resetCounter() {
		counter = new AtomicInteger(0);
	}

	static public String readSvg(SFile svgFile) throws IOException {
		final BufferedReader br = svgFile.openBufferedReader();
		if (br == null)
			return null;

		return readSvg(br, false, true);
	}

	static public String readSvg(InputStream is) throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return readSvg(br, false, false);
	}

	static public void copyInternal(final InputStream fis, final OutputStream fos, boolean close) throws IOException {
		final byte[] buf = new byte[10240];
		int len;
		while ((len = fis.read(buf)) > 0)
			fos.write(buf, 0, len);

		if (close) {
			fos.close();
			fis.close();
		}
	}

	private static String readSvg(BufferedReader br, boolean withNewline, boolean withClose) throws IOException {
		final StringBuilder sb = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) {
			sb.append(s);
			if (withNewline)
				sb.append("\n");

		}
		if (withClose)
			br.close();

		return sb.toString();
	}

	static public String readText(InputStream is) throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return readSvg(br, true, true);
	}


}

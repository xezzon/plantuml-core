package net.sourceforge.plantuml.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleFormatter extends Formatter {

	@Override
	public synchronized String format(LogRecord record) {
		final String throwable;
		if (record.getThrown() == null) {
			throwable = "";
		} else {
			final StringWriter sw = new StringWriter();
			final PrintWriter pw = new PrintWriter(sw);
			pw.println();
			record.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString().trim();
		}

		final String message = record.getMessage();
		final StringBuilder sb = new StringBuilder();

		if (message.trim().length() > 0) {
			sb.append(message);
			sb.append(System.lineSeparator());
		}

		if (throwable.length() > 0) {
			sb.append(throwable);
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}
}

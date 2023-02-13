package net.sourceforge.plantuml.utils;

import net.sourceforge.plantuml.OptionFlags;

public abstract class Log {

	private static final long start = System.currentTimeMillis();

	public synchronized static void debug(String s) {
	}

	public synchronized static void info(String s) {
	}

	public synchronized static void error(String s) {
		System.err.println(s);
	}

	private static String format(String s) {
		final long delta = System.currentTimeMillis() - start;
		// final HealthCheck healthCheck = Performance.getHealthCheck();
		// final long cpu = healthCheck.jvmCpuTime() / 1000L / 1000L;
		// final long dot = healthCheck.dotTime().getSum();

		final long freeMemory = Runtime.getRuntime().freeMemory();
		final long maxMemory = Runtime.getRuntime().maxMemory();
		final long totalMemory = Runtime.getRuntime().totalMemory();
		final long usedMemory = totalMemory - freeMemory;
		final int threadActiveCount = Thread.activeCount();

		final StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(delta / 1000L);
		sb.append(".");
		sb.append(String.format("%03d", delta % 1000L));
		// if (cpu != -1) {
		// sb.append(" - ");
		// sb.append(cpu / 1000L);
		// sb.append(".");
		// sb.append(String.format("%03d", cpu % 1000L));
		// }
		// sb.append(" - ");
		// sb.append(dot / 1000L);
		// sb.append(".");
		// sb.append(String.format("%03d", dot % 1000L));
		// sb.append("(");
		// sb.append(healthCheck.dotTime().getNb());
		// sb.append(")");
		sb.append(" - ");
		final long total = totalMemory / 1024 / 1024;
		final long free = freeMemory / 1024 / 1024;
		sb.append(total);
		sb.append(" Mo) ");
		sb.append(free);
		sb.append(" Mo - ");
		sb.append(s);
		return sb.toString();

	}

	public static void println(Object s) {
		// if (header2.get() == null) {
		// System.err.println("L = " + s);
		// } else {
		// System.err.println(header2.get() + " " + s);
		// }
	}

	// private static final ThreadLocal<String> header2 = new ThreadLocal<>();
	//
	public static void header(String s) {
		// header2.set(s);
	}
}

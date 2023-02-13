package net.sourceforge.plantuml.timingdiagram;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class TimingFormat {
	private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
	private static final GregorianCalendar gc = new GregorianCalendar(TimingFormat.GMT);

	public static final TimingFormat DECIMAL = new TimingFormat(null);
	public static final TimingFormat HOUR = new TimingFormat(null);
	public static final TimingFormat DATE = new TimingFormat(null);

	private final SimpleDateFormat sdf;

	private TimingFormat(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public static TimingFormat create(SimpleDateFormat sdf) {
		return new TimingFormat(sdf);
	}

	public String formatTime(BigDecimal time) {
		if (this == HOUR || this == DATE || sdf != null)
			return formatTime(time.longValueExact());

		return time.toPlainString();
	}

	public String formatTime(long time) {
		if (sdf != null)
			return sdf.format(time * 1000L);

		if (this == HOUR) {
			final int s = (int) time % 60;
			final int m = (int) (time / 60) % 60;
			final int h = (int) (time / 3600);
			return String.format("%d:%02d:%02d", h, m, s);
		}
		if (this == DATE) {
			final int yyyy;
			final int mm;
			final int dd;
			synchronized (gc) {
				gc.setTimeInMillis(time * 1000L);
				yyyy = gc.get(Calendar.YEAR);
				mm = gc.get(Calendar.MONTH) + 1;
				dd = gc.get(Calendar.DAY_OF_MONTH);
			}
			// return String.format("%04d/%02d/%02d", yyyy, mm, dd);
			return String.format("%02d/%02d", mm, dd);
		}
		return "" + time;
	}

	public static TimeTick createDate(int yyyy, int mm, int dd, TimingFormat format) {
		final long timeInMillis;
		synchronized (gc) {
			gc.setTimeInMillis(0);
			gc.set(yyyy, mm - 1, dd);
			timeInMillis = gc.getTimeInMillis() / 1000L;
		}
		return new TimeTick(new BigDecimal(timeInMillis), format);
	}

}

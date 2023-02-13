package net.sourceforge.plantuml.sequencediagram;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DottedNumber {

	private final List<Integer> nums;
	private final List<String> separators;

	private DottedNumber(List<Integer> nums, List<String> separators) {
		this.nums = nums;
		this.separators = separators;
	}

	public static DottedNumber create(String value) {
		final Pattern p = Pattern.compile("(\\d+)|(\\D+)");
		final Matcher m = p.matcher(value);
		final List<Integer> nums = new ArrayList<>();
		final List<String> separators = new ArrayList<>();
		while (m.find()) {
			final String part = m.group();
			if (isDigit(part.charAt(0))) {
				nums.add(Integer.parseInt(part));
			} else {
				separators.add(part);
			}
		}
		return new DottedNumber(nums, separators);
	}

	private static boolean isDigit(final char c) {
		return c >= '0' && c <= '9';
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.size(); i++) {
			sb.append(nums.get(i));
			if (i < separators.size()) {
				sb.append(separators.get(i));
			}
		}
		return sb.toString();
	}

	public void incrementMinor(int step) {
		final int last = nums.size() - 1;
		final int newValue = nums.get(last) + step;
		nums.set(last, newValue);
	}

	public void incrementIntermediate() {
		final int intermediate = nums.size() == 1 ? 0 : nums.size() - 2;
		incrementIntermediate(intermediate);
	}

	public void incrementIntermediate(int position) {
		final int intermediate = position;
		final int newValue = nums.get(intermediate) + 1;
		for (int i = intermediate + 1; i < nums.size(); i++) {
			nums.set(i, 1);
		}
		nums.set(intermediate, newValue);
	}

	public String format(DecimalFormat format) {
		if (nums.size() == 1 && separators.size() == 0) {
			return format.format(nums.get(0));
		}
		return "<b>" + toString() + "</b>";
	}

}

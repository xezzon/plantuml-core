package net.sourceforge.plantuml.creole.legacy;

import java.util.Objects;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.creole.CreoleContext;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.Stripe;
import net.sourceforge.plantuml.creole.StripeStyle;
import net.sourceforge.plantuml.creole.StripeStyleType;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.text.BackSlash;
import net.sourceforge.plantuml.utils.CharHidder;

public class CreoleStripeSimpleParser {

	final private String line;
	final private StripeStyle style;
	private final CreoleMode modeSimpleLine;

	private final FontConfiguration fontConfiguration;
	private final ISkinSimple skinParam;

	public CreoleStripeSimpleParser(String line, CreoleContext creoleContext, FontConfiguration fontConfiguration,
			ISkinSimple skinParam, CreoleMode mode) {
		if (line.contains("" + BackSlash.hiddenNewLine()))
			throw new IllegalArgumentException(line);

		this.fontConfiguration = fontConfiguration;
		this.modeSimpleLine = mode;
		this.skinParam = Objects.requireNonNull(skinParam);

		if (mode == CreoleMode.NO_CREOLE) {
			this.line = line;
			this.style = new StripeStyle(StripeStyleType.NORMAL, 0, '\0');
			return;
		}

		final Pattern2 p4 = MyPattern.cmpile("^--([^-]*)--$");
		final Matcher2 m4 = p4.matcher(line);
		if (m4.find()) {
			this.line = m4.group(1);
			this.style = new StripeStyle(StripeStyleType.HORIZONTAL_LINE, 0, '-');
			return;
		}

		final Pattern2 p5 = MyPattern.cmpile("^==([^=]*)==$");
		final Matcher2 m5 = p5.matcher(line);
		if (m5.find()) {
			this.line = m5.group(1);
			this.style = new StripeStyle(StripeStyleType.HORIZONTAL_LINE, 0, '=');
			return;
		}
		final Pattern2 p5b = MyPattern.cmpile("^===*==$");
		final Matcher2 m5b = p5b.matcher(line);
		if (m5b.find()) {
			this.line = "";
			this.style = new StripeStyle(StripeStyleType.HORIZONTAL_LINE, 0, '=');
			return;
		}

		final Pattern2 p7 = MyPattern.cmpile("^\\.\\.([^\\.]*)\\.\\.$");
		final Matcher2 m7 = p7.matcher(line);
		if (m7.find()) {
			this.line = m7.group(1);
			this.style = new StripeStyle(StripeStyleType.HORIZONTAL_LINE, 0, '.');
			return;
		}

		if (mode == CreoleMode.FULL) {
			final Pattern2 p1 = MyPattern.cmpile("^(\\*+)([^*]+(?:[^*]|\\*\\*[^*]+\\*\\*)*)$");
			final Matcher2 m1 = p1.matcher(line);
			if (m1.find()) {
				this.line = StringUtils.trin(m1.group(2));
				final int order = m1.group(1).length() - 1;
				this.style = new StripeStyle(StripeStyleType.LIST_WITHOUT_NUMBER, order, '\0');
				return;
			}
		}

		if (mode == CreoleMode.FULL) {
			final Pattern2 p1 = MyPattern.cmpile("^(\\*+)([%s].+)$");
			final Matcher2 m1 = p1.matcher(line);
			if (m1.find()) {
				this.line = StringUtils.trin(m1.group(2));
				final int order = m1.group(1).length() - 1;
				this.style = new StripeStyle(StripeStyleType.LIST_WITHOUT_NUMBER, order, '\0');
				return;
			}
		}

		if (mode == CreoleMode.FULL) {
			final Pattern2 p2 = MyPattern.cmpile("^(#+)(.+)$");
			final Matcher2 m2 = p2.matcher(CharHidder.hide(line));
			if (m2.find()) {
				this.line = StringUtils.trin(CharHidder.unhide(m2.group(2)));
				final int order = CharHidder.unhide(m2.group(1)).length() - 1;
				this.style = new StripeStyle(StripeStyleType.LIST_WITH_NUMBER, order, '\0');
				return;
			}
		}

		final Pattern2 p3 = MyPattern.cmpile("^(=+)(.+)$");
		final Matcher2 m3 = p3.matcher(line);
		if (m3.find()) {
			this.line = StringUtils.trin(m3.group(2));
			final int order = m3.group(1).length() - 1;
			this.style = new StripeStyle(StripeStyleType.HEADING, order, '\0');
			return;
		}

		this.line = line;
		this.style = new StripeStyle(StripeStyleType.NORMAL, 0, '\0');

	}

	public Stripe createStripe(CreoleContext context) {
		final StripeSimple result = new StripeSimple(fontConfiguration, style, context, skinParam, modeSimpleLine);
		result.analyzeAndAdd(line);
		return result;
	}

}

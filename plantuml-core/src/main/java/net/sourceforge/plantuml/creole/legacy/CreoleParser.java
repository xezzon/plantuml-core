package net.sourceforge.plantuml.creole.legacy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.EmbeddedDiagram;
import net.sourceforge.plantuml.SpriteContainerEmpty;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.creole.CreoleContext;
import net.sourceforge.plantuml.creole.CreoleMode;
import net.sourceforge.plantuml.creole.Parser;
import net.sourceforge.plantuml.creole.Sheet;
import net.sourceforge.plantuml.creole.SheetBuilder;
import net.sourceforge.plantuml.creole.Stripe;
import net.sourceforge.plantuml.creole.atom.Atom;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.color.NoSuchColorRuntimeException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;

public class CreoleParser implements SheetBuilder {

	private final FontConfiguration fontConfiguration;
	private final ISkinSimple skinParam;
	private final HorizontalAlignment horizontalAlignment;
	private final CreoleMode creoleMode;
	private final FontConfiguration stereotype;

	public CreoleParser(FontConfiguration fontConfiguration, HorizontalAlignment horizontalAlignment,
			ISkinSimple skinParam, CreoleMode creoleMode, FontConfiguration stereotype) {
		this.stereotype = stereotype;
		this.creoleMode = creoleMode;
		this.fontConfiguration = fontConfiguration;
		this.skinParam = Objects.requireNonNull(skinParam);
		this.horizontalAlignment = horizontalAlignment;
	}

	private Stripe createStripe(String line, CreoleContext context, Stripe lastStripe,
			FontConfiguration fontConfiguration) {
		if (lastStripe instanceof StripeRaw) {
			final StripeRaw code = (StripeRaw) lastStripe;
			if (code.isTerminated()) {
				lastStripe = null;
			} else {
				final boolean terminated = code.addAndCheckTermination(line);
				return null;
			}
		}

		if (lastStripe instanceof StripeTable && isTableLine(line)) {
			final StripeTable table = (StripeTable) lastStripe;
			table.analyzeAndAddLine(line);
			return null;
		} else if (lastStripe instanceof StripeTree && Parser.isTreeStart(StringUtils.trinNoTrace(line))) {
			final StripeTree tree = (StripeTree) lastStripe;
			tree.analyzeAndAdd(line);
			return null;
		} else if (isTableLine(line)) {
			return new StripeTable(fontConfiguration, skinParam, line);
		} else if (Parser.isTreeStart(line)) {
			return new StripeTree(fontConfiguration, skinParam, line);
		} else if (Parser.isCodeStart(line)) {
			return new StripeCode(fontConfiguration.changeFamily(Parser.MONOSPACED));
		}
		return new CreoleStripeSimpleParser(line, context, fontConfiguration, skinParam, creoleMode)
				.createStripe(context);
	}

	public static boolean isTableLine(String line) {
		return line.matches("^(\\<#\\w+(,#?\\w+)?\\>)?\\|(\\=)?.*\\|$");
	}

	public static boolean doesStartByColor(String line) {
		return line.matches("^\\=?\\s*(\\<#\\w+(,#?\\w+)?\\>).*");
	}

	private final Map<Display, Sheet> cache = new HashMap<>();

	public Sheet createSheet(Display display) {
		Sheet result = cache.get(display);
		if (result == null) {
			result = createSheetSlow(display, false);
			cache.put(display, result);
		}
		return result;
	}

	private Sheet createSheetSlow(Display display, boolean checkColor) {
		final Sheet sheet = new Sheet(horizontalAlignment);
		if (Display.isNull(display) == false) {
			final CreoleContext context = new CreoleContext();
			final Iterator<CharSequence> it = display.iterator();
			while (it.hasNext()) {
				final CharSequence cs = it.next();
				final Stripe stripe;
				final String type = EmbeddedDiagram.getEmbeddedType(StringUtils.trinNoTrace(cs));
				if (type != null) {
					final Atom embeddedDiagram = EmbeddedDiagram.createAndSkip(type, it, skinParam);
					if (checkColor)
						stripe = null;
					else {
						stripe = new Stripe() {
							public Atom getLHeader() {
								return null;
							}

							public List<Atom> getAtoms() {
								return Arrays.asList(embeddedDiagram);
							}
						};
					}
				} else if (cs instanceof Stereotype) {
					if (display.showStereotype())
						for (String st : ((Stereotype) cs).getLabels(skinParam.guillemet()))
							sheet.add(createStripe(st, context, sheet.getLastStripe(), stereotype));

					continue;
				} else {
					stripe = createStripe(skinParam.guillemet().manageGuillemet(cs.toString()), context,
							sheet.getLastStripe(), fontConfiguration);
				}

				if (stripe != null)
					sheet.add(stripe);

			}
		}
		return sheet;
	}

	public static void checkColor(Display result) throws NoSuchColorException {
		FontConfiguration fc = FontConfiguration.blackBlueTrue(UFont.byDefault(10));
		try {
			new CreoleParser(fc, HorizontalAlignment.LEFT, new SpriteContainerEmpty(), CreoleMode.FULL, fc)
					.createSheetSlow(result, true);
		} catch (NoSuchColorRuntimeException e) {
			throw new NoSuchColorException();
		}
	}
}

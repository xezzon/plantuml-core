package net.sourceforge.plantuml.salt.factory;

import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.Terminator;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementMenuBar;

public class ElementFactoryMenu extends AbstractElementFactoryComplex {

	public ElementFactoryMenu(DataSource dataSource, SaltDictionary dictionary) {
		super(dataSource, dictionary);
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final String header = getDataSource().next().getElement();
		assert header.startsWith("{*");

		final UFont font = UFont.byDefault(12);
		final ElementMenuBar result = new ElementMenuBar(font, getDictionary());

		String subentry = null;

		while (getDataSource().peek(0).getElement().equals("}") == false) {
			final Terminated<String> t = getDataSource().next();
			final String s = t.getElement();
			if (subentry == null) {
				result.addEntry(s);
			} else if (subentry.length() == 0) {
				subentry = s;
			} else {
				result.addSubEntry(subentry, s);
			}
			if (t.getTerminator() == Terminator.NEWLINE) {
				subentry = "";
			}
		}
		final Terminated<String> next = getDataSource().next();
		return new Terminated<Element>(result, next.getTerminator());
	}

	public boolean ready() {
		final String text = getDataSource().peek(0).getElement();
		if (text.equals("{*")) {
			return true;
		}
		return false;
	}
}

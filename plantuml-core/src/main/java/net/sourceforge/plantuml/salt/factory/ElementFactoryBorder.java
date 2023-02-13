package net.sourceforge.plantuml.salt.factory;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementBorder;

public class ElementFactoryBorder extends AbstractElementFactoryComplex {

	public ElementFactoryBorder(DataSource dataSource, SaltDictionary dictionary) {
		super(dataSource, dictionary);
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final String header = getDataSource().next().getElement();
		assert header.startsWith("{");

//		TableStrategy strategy = TableStrategy.DRAW_NONE;
//		if (header.length() == 2) {
//			strategy = TableStrategy.fromChar(header.charAt(1));
//		}

		final ElementBorder result = new ElementBorder();

		while (getDataSource().peek(0).getElement().equals("}") == false) {
			final String pos = getDataSource().next().getElement();
			switch (pos.charAt(0)) {
			case 'N':
				result.setNorth(getNextElement().getElement());
				break;
			case 'S':
				result.setSouth(getNextElement().getElement());
				break;
			case 'E':
				result.setEast(getNextElement().getElement());
				break;
			case 'W':
				result.setWest(getNextElement().getElement());
				break;
			case 'C':
				result.setCenter(getNextElement().getElement());
				break;
			default:
				throw new IllegalStateException();

			}
		}
		final Terminated<String> next = getDataSource().next();
		return new Terminated<Element>(result, next.getTerminator());
	}

	public boolean ready() {
		final String text = getDataSource().peek(0).getElement();
		if (text.equals("{") || text.equals("{+") || text.equals("{#") || text.equals("{!") || text.equals("{-")) {
			final String text1 = getDataSource().peek(1).getElement();
			if (text1.matches("[NSEW]=")) {
				return true;
			}
			return false;
		}
		return false;
	}
}

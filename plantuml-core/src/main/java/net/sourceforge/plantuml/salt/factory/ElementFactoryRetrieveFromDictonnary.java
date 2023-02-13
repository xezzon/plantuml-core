package net.sourceforge.plantuml.salt.factory;

import java.util.Objects;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.SaltDictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;

public class ElementFactoryRetrieveFromDictonnary implements ElementFactory {

	private final SaltDictionary dictionary;
	private final DataSource dataSource;

	public ElementFactoryRetrieveFromDictonnary(DataSource dataSource, SaltDictionary dictionary) {
		this.dataSource = dataSource;
		this.dictionary = dictionary;
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final Terminated<String> next = dataSource.next();

		String name = next.getElement();
		name = name.substring(2, name.length() - 2);
		final Element retrieve = Objects.requireNonNull(dictionary.get(name), name);

		return new Terminated<>(retrieve, next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		return text.matches("\\<\\<\\w+\\>\\>");
	}
}

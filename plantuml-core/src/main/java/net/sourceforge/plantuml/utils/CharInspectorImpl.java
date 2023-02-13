package net.sourceforge.plantuml.utils;

public class CharInspectorImpl implements CharInspector {

	final private BlocLines data;
	final private boolean insertNewlines;
	private int line = 0;
	private int pos = 0;

	CharInspectorImpl(BlocLines input, boolean insertNewlines) {
		this.data = input;
		this.insertNewlines = insertNewlines;
	}

	@Override
	public char peek(int ahead) {
		if (line == -1)
			return 0;
		final String currentLine = getCurrentLine();
		if (pos + ahead >= currentLine.length())
			return '\0';
		return currentLine.charAt(pos + ahead);
	}

	private String getCurrentLine() {
		if (insertNewlines)
			return data.getAt(line).getTrimmed().getString() + "\n";
		return data.getAt(line).getTrimmed().getString();
	}

	@Override
	public void jump() {
		if (line == -1)
			throw new IllegalStateException();
		pos++;
		if (pos >= getCurrentLine().length()) {
			line++;
			pos = 0;
		}
		while (line < data.size() && getCurrentLine().length() == 0)
			line++;
		if (line >= data.size())
			line = -1;
	}
}

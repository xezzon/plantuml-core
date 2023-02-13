package net.sourceforge.plantuml.preproc;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.AParentFolder;
import net.sourceforge.plantuml.text.BackSlash;
import net.sourceforge.plantuml.tim.expression.TValue;

public class Define {

	private final DefineSignature signature;
	private final String definition;
	private final String definitionQuoted;
	private final boolean emptyParentheses;
	private Pattern pattern;
	private final AParentFolder currentDir;

	public Define(String key, List<String> lines, boolean emptyParentheses, AParentFolder currentDir) {
		this.currentDir = currentDir;
		this.emptyParentheses = emptyParentheses;
		if (lines == null) {
			this.definition = null;
			this.definitionQuoted = null;
		} else {
			final StringBuilder sb = new StringBuilder();
			for (final Iterator<String> it = lines.iterator(); it.hasNext();) {
				sb.append(it.next());
				if (it.hasNext()) {
					sb.append('\n');
				}
			}
			this.definition = sb.toString();
			this.definitionQuoted = Matcher.quoteReplacement(definition);
		}
		this.signature = new DefineSignature(key, this.definitionQuoted);

	}

	@Override
	public String toString() {
		return signature.toString();
	}

	public String apply(String line) {
		if (definition == null) {
			return line;
		}
		// if (getFunctionName().indexOf('_') >= 0 && line.indexOf('_') == -1) {
		// return line;
		// }
		if (/* line.length() < getFunctionName().length() || */line.contains(getFunctionName()) == false) {
			return line;
		}
		if (signature.isMethod()) {
			if (line.indexOf('(') == -1) {
				return line;
			}
			line = apply1(line);
		} else {
			line = apply2(line);
		}
		return line;
	}

	private String apply2(String line) {
		if (pattern == null) {
			final String regex = "\\b" + signature.getKey() + "\\b" + (emptyParentheses ? "(\\(\\))?" : "");
			pattern = Pattern.compile(regex);
		}

		line = BackSlash.translateBackSlashes(line);
		line = pattern.matcher(line).replaceAll(definitionQuoted);
		line = BackSlash.untranslateBackSlashes(line);
		return line;
	}

	private String apply1(String line) {
		for (Variables vars : signature.getVariationVariables()) {
			line = vars.applyOn(line);
		}
		return line;
	}

	public final String getFunctionName() {
		return signature.getFonctionName();
	}

	public TValue asTVariable() {
		return TValue.fromString(definition);
	}
}

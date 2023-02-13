package net.sourceforge.plantuml.preproc2;

import java.io.IOException;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.StartUtils;

public class ReadFilterMergeLines implements ReadFilter {

	public ReadLine applyFilter(final ReadLine source) {
		return new ReadLine() {

			private boolean manageEndingBackslash = true;

			public void close() throws IOException {
				source.close();
			}

			public StringLocated readLine() throws IOException {
				StringLocated result = source.readLine();
				if (result != null && StartUtils.isArobaseStartDiagram(result.getString())
						&& isDitaa(result.getString())) {
					this.manageEndingBackslash = false;
				}
				if (result != null && StartUtils.isArobaseEndDiagram(result.getString())) {
					this.manageEndingBackslash = true;
				}

				ReadLine sourceWithoutComment = null;

				while (result != null && manageEndingBackslash && StringUtils.endsWithBackslash(result.getString())) {
					if (sourceWithoutComment == null) {
						sourceWithoutComment = new ReadFilterQuoteComment().applyFilter(source);
					}
					final StringLocated next = sourceWithoutComment.readLine();
					if (next == null) {
						break;
					} else {
						result = result.mergeEndBackslash(next);
					}
				}
				return result;
			}

			private boolean isDitaa(String string) {
				return DiagramType.getTypeFromArobaseStart(StringUtils.trinNoTrace((string))) == DiagramType.DITAA;
			}
		};
	}

}

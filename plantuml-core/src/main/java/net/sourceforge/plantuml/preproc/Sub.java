package net.sourceforge.plantuml.preproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterStartsub;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TMemory;

public class Sub {

	private final String name;
	private final List<StringLocated> lines = new ArrayList<>();
//	private boolean indentationDone = false;

	public Sub(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString() + " " + name;
	}

	public void add(StringLocated s) {
//		if (indentationDone) {
//			throw new IllegalStateException();
//		}
		this.lines.add(s);
	}

	public final List<StringLocated> lines() {
//		if (indentationDone == false) {
//			CodeIteratorImpl.indentNow(lines);
//			indentationDone = true;
//		}
		return Collections.unmodifiableList(lines);
	}

	public static Sub fromFile(ReadLine reader, String blocname, TContext context, TMemory memory)
			throws IOException, EaterException {
		Sub result = null;
		StringLocated s = null;
		boolean skip = false;
		while ((s = reader.readLine()) != null) {
			final TLineType type = s.getTrimmed().getType();
			if (type == TLineType.STARTSUB) {
				final EaterStartsub eater = new EaterStartsub(s.getTrimmed());
				eater.analyze(context, memory);
				if (eater.getSubname().equals(blocname)) {
					skip = false;
					if (result == null) {
						result = new Sub(blocname);
					}
				}
				continue;
			}
			if (type == TLineType.ENDSUB && result != null) {
				skip = true;
			}
			if (result != null && skip == false) {
				result.add(s);
			}
		}
		return result;
	}

}

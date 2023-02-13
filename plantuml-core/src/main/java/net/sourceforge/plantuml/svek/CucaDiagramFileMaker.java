package net.sourceforge.plantuml.svek;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public interface CucaDiagramFileMaker {

	public ImageData createFile(OutputStream os, List<String> dotStrings, FileFormatOption fileFormatOption)
			throws IOException;

	public void createOneGraphic(UGraphic ug);
}

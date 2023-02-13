package net.sourceforge.plantuml.sudoku;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;

public class PSystemSudoku extends AbstractPSystem {

	final private ISudoku sudoku;

	@Override
	final protected ImageData exportDiagramNow(OutputStream os, int num, FileFormatOption fileFormat)
			throws IOException {
		final GraphicsSudoku sud = new GraphicsSudoku(sudoku);

		if (fileFormat.getFileFormat() == FileFormat.SVG)
			return sud.writeImageSvg(os);

		return sud.writeImagePng(os);
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Sudoku)");
	}

	public PSystemSudoku(UmlSource source, Long seed) {
		super(source);
		sudoku = new SudokuDLX(seed);
	}

}

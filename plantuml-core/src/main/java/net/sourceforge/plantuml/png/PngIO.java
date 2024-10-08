// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.png;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SImageIO;
import net.sourceforge.plantuml.utils.Log;

public class PngIO {


	public static void write(RenderedImage image, ColorMapper mapper, OutputStream os, String metadata, int dpi)
			throws IOException {
		write(image, mapper, os, metadata, dpi, null);
	}

	private static void write(RenderedImage image, ColorMapper mapper, OutputStream os, String metadata, int dpi,
			String debugData) throws IOException {

			SImageIO.write(image, "png", os);

	}

//	/** writes a BufferedImage of type TYPE_INT_ARGB to PNG using PNGJ */
//	public static void writeARGB(BufferedImage bi, OutputStream os, String metadata) {
//		// if (bi.getType() != BufferedImage.TYPE_INT_ARGB)
//		// throw new PngjException("This method expects  BufferedImage.TYPE_INT_ARGB");
//		ImageInfo imi = new ImageInfo(bi.getWidth(), bi.getHeight(), 8, false);
//		PngChunkTEXT chunkText = new PngChunkTEXT(imi, "copyleft", copyleft);
//		// PngChunkTEXT chunkTextDebug = new PngChunkTEXT(imi, "debug", "debugData");
//		PngChunkITXT meta = new PngChunkITXT(imi);
//		meta.setKeyVal("plantuml", metadata);
//		meta.setCompressed(true);
//
//		PngWriter pngw = new PngWriter(os, imi);
//		pngw.setCompLevel(9);// maximum compression, not critical usually
//		// pngw.setFilterType(FilterType.FILTER_ADAPTIVE_FAST); // see what you prefer here
//		// pngw.setFilterType(FilterType.FILTER_ADAPTIVE_MEDIUM); // see what you prefer here
//		pngw.setFilterType(FilterType.FILTER_ADAPTIVE_FULL); // see what you prefer here
//		pngw.queueChunk(chunkText);
//		// // pngw.queueChunk(chunkTextDebug);
//		pngw.queueChunk(meta);
//		DataBufferInt db = ((DataBufferInt) bi.getRaster().getDataBuffer());
//		SinglePixelPackedSampleModel samplemodel = (SinglePixelPackedSampleModel) bi.getSampleModel();
//		if (db.getNumBanks() != 1)
//			throw new PngjException("This method expects one bank");
//		ImageLineInt line = new ImageLineInt(imi);
//		for (int row = 0; row < imi.rows; row++) {
//			int elem = samplemodel.getOffset(0, row);
//			for (int col = 0, j = 0; col < imi.cols; col++) {
//				int sample = db.getElem(elem++);
//				line.scanline[j++] = (sample & 0xFF0000) >> 16; // R
//				line.scanline[j++] = (sample & 0xFF00) >> 8; // G
//				line.scanline[j++] = (sample & 0xFF); // B
//				// line.scanline[j++] = (((sample & 0xFF000000) >> 24) & 0xFF); // A
//			}
//			pngw.writeRow(line, row);
//		}
//		pngw.end();
//	}

}

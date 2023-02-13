package net.sourceforge.plantuml.ugraphic;

import static net.sourceforge.plantuml.SkinParam.DEFAULT_PRESERVE_ASPECT_RATIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;

import com.plantuml.wasm.WasmLog;

import net.sourceforge.plantuml.AnnotatedBuilder;
import net.sourceforge.plantuml.AnnotatedWorker;
import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.CornerParam;
import net.sourceforge.plantuml.EmptyImageBuilder;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.FileUtils;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.LineParam;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.Pragma;
import net.sourceforge.plantuml.Scale;
import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.api.ImageDataComplex;
import net.sourceforge.plantuml.api.ImageDataSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.color.HColorSimple;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.MinMax;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SImageIO;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.style.ClockwiseTopRightBottomLeft;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.svg.LengthAdjust;
import net.sourceforge.plantuml.text.SvgCharSizeHack;
import net.sourceforge.plantuml.ugraphic.g2d.UGraphicG2d;
import net.sourceforge.plantuml.ugraphic.hand.UGraphicHandwritten;
import net.sourceforge.plantuml.ugraphic.svg.UGraphicSvg;
import net.sourceforge.plantuml.url.CMapData;
import net.sourceforge.plantuml.url.Url;

public class ImageBuilder {

	private boolean annotations;
	private HColor backcolor = getDefaultHBackColor();

	private XDimension2D dimension;
	private final FileFormatOption fileFormatOption;
	private UDrawable udrawable;
	private ClockwiseTopRightBottomLeft margin = ClockwiseTopRightBottomLeft.none();
	private String metadata;
	private long seed = 42;
	private ISkinParam skinParam;
	private StringBounder stringBounder;
	private int status = 0;
	private TitledDiagram titledDiagram;
	private boolean randomPixel;
	private String warningOrError;

	public static ImageBuilder imageBuilder(FileFormatOption fileFormatOption) {
		return new ImageBuilder(fileFormatOption);
	}

	public static ImageBuilder plainImageBuilder(UDrawable drawable, FileFormatOption fileFormatOption) {
		return imageBuilder(fileFormatOption).drawable(drawable);
	}

	public static ImageBuilder plainPngBuilder(UDrawable drawable) {
		return imageBuilder(new FileFormatOption(FileFormat.PNG)).drawable(drawable);
	}

	private ImageBuilder(FileFormatOption fileFormatOption) {
		this.fileFormatOption = fileFormatOption;
		this.stringBounder = fileFormatOption.getDefaultStringBounder(SvgCharSizeHack.NO_HACK);
	}

	public ImageBuilder annotations(boolean annotations) {
		this.annotations = annotations;
		return this;
	}

	public ImageBuilder backcolor(HColor backcolor) {
		this.backcolor = backcolor;
		return this;
	}

	public ImageBuilder blackBackcolor() {
		return backcolor(HColors.BLACK);
	}

	public ImageBuilder dimension(XDimension2D dimension) {
		this.dimension = dimension;
		return this;
	}

	private int getDpi() {
		return skinParam == null ? 96 : skinParam.getDpi();
	}

	public ImageBuilder drawable(UDrawable drawable) {
		this.udrawable = drawable;
		if (backcolor == null && drawable instanceof TextBlockBackcolored)
			backcolor = ((TextBlockBackcolored) drawable).getBackcolor();

		return this;
	}

	public ImageBuilder margin(ClockwiseTopRightBottomLeft margin) {
		this.margin = margin;
		return this;
	}

	public ImageBuilder metadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public ImageBuilder randomPixel() {
		this.randomPixel = true;
		return this;
	}

	public ImageBuilder seed(long seed) {
		this.seed = seed;
		return this;
	}

	public ImageBuilder status(int status) {
		this.status = status;
		return this;
	}

	private String getSvgLinkTarget() {
		if (fileFormatOption.getSvgLinkTarget() != null)
			return fileFormatOption.getSvgLinkTarget();
		else if (skinParam != null)
			return skinParam.getSvgLinkTarget();
		else
			return null;

	}

	public ImageBuilder warningOrError(String warningOrError) {
		this.warningOrError = warningOrError;
		return this;
	}

	public ImageBuilder styled(TitledDiagram diagram) {
		skinParam = diagram.getSkinParam();
		stringBounder = fileFormatOption.getDefaultStringBounder(skinParam);
		annotations = true;
		backcolor = diagram.calculateBackColor();
		margin = calculateMargin(diagram);
		metadata = fileFormatOption.isWithMetadata() ? diagram.getMetadata() : null;
		seed = diagram.seed();
		titledDiagram = diagram;
		warningOrError = diagram.getWarningOrError();
		return this;
	}

	public ImageData write(OutputStream os) throws IOException {
		if (annotations && titledDiagram != null) {
			if (!(udrawable instanceof TextBlock))
				throw new IllegalStateException("udrawable is not a TextBlock");
			final AnnotatedBuilder builder = new AnnotatedBuilder(titledDiagram, skinParam, stringBounder);
			final AnnotatedWorker annotatedWorker = new AnnotatedWorker(titledDiagram, skinParam, stringBounder,
					builder);
			udrawable = annotatedWorker.addAdd((TextBlock) udrawable);
		}

		 return writeImageInternal(os);
	}

	public byte[] writeByteArray() throws IOException {
		try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			write(baos);
			return baos.toByteArray();
		}
	}

		 private ImageData writeImageInternal(OutputStream os) throws IOException {
		XDimension2D dim = getFinalDimension();
		double dx = 0;
		double dy = 0;
		final Scale scale = titledDiagram == null ? null : titledDiagram.getScale();
		final double scaleFactor = (scale == null ? 1 : scale.getScale(dim.getWidth(), dim.getHeight())) * getDpi()
				/ 96.0;
		if (scaleFactor <= 0)
			throw new IllegalStateException("Bad scaleFactor");
		WasmLog.log("...image drawing...");
		 UGraphic ug = createUGraphic(dim, dx, dy, scaleFactor,
		 titledDiagram == null ? new Pragma() : titledDiagram.getPragma());
		maybeDrawBorder(ug, dim);
		if (randomPixel)
			drawRandomPoint(ug);

		ug = handwritten(ug.apply(new UTranslate(margin.getLeft(), margin.getTop())));
		udrawable.drawU(ug);
		ug.flushUg();
		ug.writeToStream(os, metadata, 96);
		os.flush();

		if (ug instanceof UGraphicG2d) {
			final Set<Url> urls = ((UGraphicG2d) ug).getAllUrlsEncountered();
			if (urls.size() > 0) {
				final CMapData cmap = CMapData.cmapString(urls, scaleFactor);
				return new ImageDataComplex(dim, cmap, warningOrError, status);
			}
		}
		return createImageData(dim);
	}

	private void maybeDrawBorder(UGraphic ug, XDimension2D dim) {
		if (skinParam == null)
			return;

		final HColor color = new Rose().getHtmlColor(skinParam, ColorParam.diagramBorder);

		UStroke stroke = skinParam.getThickness(LineParam.diagramBorder, null);
		if (stroke == null && color != null)
			stroke = new UStroke();
		if (stroke == null)
			return;

		final URectangle rectangle = new URectangle(dim.getWidth() - stroke.getThickness(),
				dim.getHeight() - stroke.getThickness())
						.rounded(skinParam.getRoundCorner(CornerParam.diagramBorder, null));

		ug.apply(color == null ? HColors.BLACK : color).apply(stroke).draw(rectangle);
	}

	private void drawRandomPoint(UGraphic ug2) {
		final Random rnd = new Random();
		final int red = rnd.nextInt(40);
		final int green = rnd.nextInt(40);
		final int blue = rnd.nextInt(40);
		final Color c = new Color(red, green, blue);
		final HColor color = HColors.simple(c);
		ug2.apply(color).apply(color.bg()).draw(new URectangle(1, 1));
	}

	private XDimension2D getFinalDimension() {
		if (dimension == null) {
			final LimitFinder limitFinder = LimitFinder.create(stringBounder, true);
			udrawable.drawU(limitFinder);
			dimension = new XDimension2D(limitFinder.getMaxX() + 1 + margin.getLeft() + margin.getRight(),
					limitFinder.getMaxY() + 1 + margin.getTop() + margin.getBottom());
		}
		return dimension;
	}

	private UGraphic handwritten(UGraphic ug) {
		if (skinParam != null && skinParam.handwritten())
			return new UGraphicHandwritten(ug);

		return ug;
	}


		 private UGraphic createUGraphic(final XDimension2D dim, double dx, double dy,
		 double scaleFactor, Pragma pragma) {
		final ColorMapper colorMapper = fileFormatOption.getColorMapper();
		switch (fileFormatOption.getFileFormat()) {
		case PNG:
		case RAW:
		 return createUGraphicPNG(scaleFactor, dim, dx, dy,
		 fileFormatOption.getWatermark(), fileFormatOption.getFileFormat());
		case SVG:
			final boolean interactive = "true".equalsIgnoreCase(pragma.getValue("svginteractive"));
			return createUGraphicSVG(scaleFactor, dim, interactive);
		default:
			throw new UnsupportedOperationException(fileFormatOption.getFileFormat().toString());
		}
	}

	private UGraphic createUGraphicSVG(double scaleFactor, XDimension2D dim, boolean interactive) {
		final String hoverPathColorRGB = getHoverPathColorRGB();
		final LengthAdjust lengthAdjust = skinParam == null ? LengthAdjust.defaultValue() : skinParam.getlengthAdjust();
		final String preserveAspectRatio = getPreserveAspectRatio();
		final boolean svgDimensionStyle = skinParam == null || skinParam.svgDimensionStyle();
		final String svgLinkTarget = getSvgLinkTarget();
		final UGraphicSvg ug = new UGraphicSvg(backcolor, svgDimensionStyle, dim, fileFormatOption.getColorMapper(),
				false, scaleFactor, svgLinkTarget, hoverPathColorRGB, seed, preserveAspectRatio, stringBounder,
				lengthAdjust, interactive);
		return ug;

	}

	 private UGraphic createUGraphicPNG(double scaleFactor, final XDimension2D
	 dim, double dx, double dy, String watermark, FileFormat format) {
		Color pngBackColor = new Color(0, 0, 0, 0);

		if (this.backcolor instanceof HColorSimple)
			pngBackColor = this.backcolor.toColor(fileFormatOption.getColorMapper());

		if (OptionFlags.getInstance().isReplaceWhiteBackgroundByTransparent()
				&& (Color.WHITE.equals(pngBackColor) || Color.BLACK.equals(pngBackColor)))
			pngBackColor = new Color(0, 0, 0, 0);

		final EmptyImageBuilder builder = new EmptyImageBuilder(watermark, (int) (dim.getWidth() * scaleFactor),
				(int) (dim.getHeight() * scaleFactor), pngBackColor, stringBounder);
		final Graphics2D graphics2D = builder.getGraphics2D();

		 final UGraphicG2d ug = new UGraphicG2d(backcolor,
		 fileFormatOption.getColorMapper(), stringBounder, graphics2D,
		 scaleFactor, dx, dy, format);
		ug.setBufferedImage(builder.getBufferedImage());
		final BufferedImage im = ug.getBufferedImage();
		if (this.backcolor instanceof HColorGradient)
			ug.apply(this.backcolor.bg())
					.draw(new URectangle(im.getWidth() / scaleFactor, im.getHeight() / scaleFactor));

		return ug;
	}

	static private HColor getDefaultHBackColor() {
		return HColors.WHITE;
	}

	private String getHoverPathColorRGB() {
		if (fileFormatOption.getHoverColor() != null) {
			return fileFormatOption.getHoverColor();
		} else if (skinParam != null) {
			final HColor color = skinParam.hoverPathColor();
			if (color != null)
				return color.toRGB(fileFormatOption.getColorMapper());

		}
		return null;
	}

	private static ClockwiseTopRightBottomLeft calculateMargin(TitledDiagram diagram) {
		final Style style = StyleSignatureBasic.of(SName.root, SName.document)
				.getMergedStyle(diagram.getSkinParam().getCurrentStyleBuilder());
		if (style.hasValue(PName.Margin))
			return style.getMargin();

		return diagram.getDefaultMargins();
	}

	public String getPreserveAspectRatio() {
		if (fileFormatOption.getPreserveAspectRatio() != null)
			return fileFormatOption.getPreserveAspectRatio();
		else if (skinParam != null)
			return skinParam.getPreserveAspectRatio();
		else
			return DEFAULT_PRESERVE_ASPECT_RATIO;

	}

	private ImageDataSimple createImageData(XDimension2D dim) {
		return new ImageDataSimple(dim, status);
	}

}

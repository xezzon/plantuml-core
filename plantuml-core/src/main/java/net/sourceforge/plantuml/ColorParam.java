package net.sourceforge.plantuml;


import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;


public enum ColorParam {
	background(HColors.WHITE, true, ColorType.BACK), 
	hyperlink(HColors.BLUE),

	activityBackground(HColors.MY_YELLOW, true, ColorType.BACK), 
	activityBorder(HColors.MY_RED, ColorType.LINE),

	classBackground(HColors.MY_YELLOW, true, ColorType.BACK),

	classBorder(HColors.MY_RED, ColorType.LINE),

	arrowHead(HColors.MY_RED, null),

	stateBorder(HColors.MY_RED, ColorType.LINE),

	noteBackground(HColors.COL_FBFB77, true, ColorType.BACK), 
	noteBorder(HColors.MY_RED, ColorType.LINE),

	diagramBorder(null, ColorType.LINE),

	actorBackground(HColors.MY_YELLOW, true, ColorType.BACK), 
	actorBorder(HColors.MY_RED, ColorType.LINE),
	sequenceGroupBodyBackground(HColors.RED, true, ColorType.BACK),
	sequenceReferenceHeaderBackground(HColors.COL_EEEEEE, true, ColorType.BACK),
	sequenceReferenceBackground(HColors.WHITE, true, ColorType.BACK),
	sequenceLifeLineBorder(HColors.MY_RED, ColorType.LINE),
	sequenceNewpageSeparator(HColors.BLACK, ColorType.LINE), 
	sequenceBoxBorder(HColors.MY_RED, ColorType.LINE),

	iconPrivate(HColors.COL_C82930), 
	iconPrivateBackground(HColors.COL_F24D5C),
	iconPackage(HColors.COL_1963A0), 
	iconPackageBackground(HColors.COL_4177AF),
	iconProtected(HColors.COL_B38D22), 
	iconProtectedBackground(HColors.COL_FFFF44),
	iconPublic(HColors.COL_038048), 
	iconPublicBackground(HColors.COL_84BE84),
	iconIEMandatory(HColors.BLACK),

	arrowLollipop(HColors.WHITE),

	machineBackground(HColors.WHITE), 
	machineBorder(HColors.BLACK, ColorType.LINE),
	requirementBackground(HColors.WHITE), 
	requirementBorder(HColors.BLACK, ColorType.LINE),
	designedBackground(HColors.WHITE), 
	designedBorder(HColors.BLACK, ColorType.LINE),
	domainBackground(HColors.WHITE), 
	domainBorder(HColors.BLACK, ColorType.LINE),
	lexicalBackground(HColors.WHITE), 
	lexicalBorder(HColors.BLACK, ColorType.LINE),
	biddableBackground(HColors.WHITE), 
	biddableBorder(HColors.BLACK, ColorType.LINE);

	private final boolean isBackground;
	private final HColor defaultValue;
	private final ColorType colorType;

	private ColorParam(HColor defaultValue, ColorType colorType) {
		this(defaultValue, false, colorType);
	}

	private ColorParam(HColor defaultValue) {
		this(defaultValue, false, null);
	}

	private ColorParam() {
		this(null, false, null);
	}

	private ColorParam(boolean isBackground) {
		this(null, isBackground, null);
	}

	private ColorParam(HColor defaultValue, boolean isBackground, ColorType colorType) {
		this.isBackground = isBackground;
		this.defaultValue = defaultValue;
		this.colorType = colorType;
		if (colorType == ColorType.BACK && isBackground == false) {
			System.err.println(this);
			throw new IllegalStateException();
		}
	}

	protected boolean isBackground() {
		return isBackground;
	}

	public final HColor getDefaultValue() {
		return defaultValue;
	}

	public ColorType getColorType() {
		return colorType;
	}
}

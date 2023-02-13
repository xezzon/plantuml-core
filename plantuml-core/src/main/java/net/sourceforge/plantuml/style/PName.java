package net.sourceforge.plantuml.style;

public enum PName {
	Shadowing, //
	FontName, //
	FontColor, //
	FontSize, //
	FontStyle, //
	BackGroundColor, //
	RoundCorner, //
	LineThickness, //
	DiagonalCorner, //
	HyperLinkColor, //
	HyperlinkUnderlineStyle, //
	HyperlinkUnderlineThickness, //
	HeadColor, //
	LineColor, //
	LineStyle, //
	Padding, //
	Margin, //
	MaximumWidth, //
	MinimumWidth, //
	ExportedName, //
	Image, //
	HorizontalAlignment, //
	ShowStereotype, //
	ImagePosition;

	public static PName getFromName(String name, StyleScheme scheme) {
		for (PName prop : values())
			if (prop.name().equalsIgnoreCase(name))
				return prop;

		return null;
	}

}

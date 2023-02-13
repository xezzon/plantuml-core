package net.sourceforge.plantuml.klimt;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface MutableImage {

	public BufferedImage getImage();

	public MutableImage withScale(double scale);

	public MutableImage muteColor(Color newColor);

	public MutableImage muteTransparentColor(Color newColor);

	public MutableImage monochrome();

	double getScale();

}

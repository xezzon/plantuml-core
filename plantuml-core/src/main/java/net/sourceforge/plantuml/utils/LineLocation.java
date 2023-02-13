package net.sourceforge.plantuml.utils;

/**
 * Indicates the location of a line of code within a resource.
 * The resource maybe a local file or a remote URL.
 *
 */
public interface LineLocation extends Comparable<LineLocation> {
	
	/**
	 * Position of the line, starting at 0.
	 */
	public int getPosition();
	
	/**
	 * A description of the resource.
	 * If the resource is a file, this is the complete path of the file.
	 */
	public String getDescription();
	
	/**
	 * Get the parent of this location.
	 * If this resource has been included by a !include or !includeurl directive,
	 * this return the location of the !include line.
	 */
	public LineLocation getParent();

}

package net.sourceforge.plantuml.real;

public interface Real {

	public void printCreationStackTrace();

	public String getName();

	public double getCurrentValue();

	public Real addFixed(double delta);

	public Real addAtLeast(double delta);

	public void ensureBiggerThan(Real other);

//	public Real getMaxAbsolute();
//
//	public Real getMinAbsolute();
//
}

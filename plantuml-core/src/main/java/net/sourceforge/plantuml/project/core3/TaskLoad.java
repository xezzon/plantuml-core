package net.sourceforge.plantuml.project.core3;

public interface TaskLoad {

	public long getStart();

	public long getEnd();

	public Histogram getLoad();

}

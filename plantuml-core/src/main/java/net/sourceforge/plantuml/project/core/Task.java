package net.sourceforge.plantuml.project.core;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.lang.CenterBorderColor;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.DayOfWeek;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.url.Url;

public interface Task extends Moment {

	public TaskCode getCode();

	public Load getLoad();

	public void setLoad(Load load);

	public void setStart(Day start);

	public void setEnd(Day end);

	public void setColors(CenterBorderColor... colors);

	public void addResource(Resource resource, int percentage);

	public void setDiamond(boolean diamond);

	public boolean isDiamond();

	public void setCompletion(int completion);

	public void setUrl(Url url);

	public void putInSameRowAs(Task row);

	public Task getRow();

	public void addPause(Day pause);

	public void addPause(DayOfWeek pause);

	public void setNote(Display note);

	public StyleBuilder getStyleBuilder();

}

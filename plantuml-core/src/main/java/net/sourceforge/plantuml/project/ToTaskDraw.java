// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.project;

import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.project.draw.TaskDraw;

public interface ToTaskDraw {

	public TaskDraw getTaskDraw(Task task);

	public LoadPlanable getDefaultPlan();

	public HColorSet getIHtmlColorSet();

}

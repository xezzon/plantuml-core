package net.sourceforge.plantuml.mindmap;

public enum IdeaShape {
	BOX, NONE;

	public static IdeaShape fromDesc(String s) {
		if ("_".equals(s)) {
			return NONE;
		}
		return BOX;
	}
}

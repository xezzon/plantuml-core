package net.sourceforge.plantuml.core;

import net.sourceforge.plantuml.utils.StartUtils;

public enum DiagramType {
	UML, BPM, DITAA, DOT, PROJECT, JCCKIT, SALT, FLOW, CREOLE, JUNGLE, CUTE, MATH, LATEX, DEFINITION, GANTT, NW,
	MINDMAP, WBS, WIRE, JSON, GIT, BOARD, YAML, HCL, EBNF, REGEX, UNKNOWN;

	static public DiagramType getTypeFromArobaseStart(String s) {
		s = s.toLowerCase();
		// if (s.startsWith("@startuml2")) {
		// return UML2;
		// }
		if (StartUtils.startsWithSymbolAnd("startwire", s))
			return WIRE;

		if (StartUtils.startsWithSymbolAnd("startbpm", s))
			return BPM;

		if (StartUtils.startsWithSymbolAnd("startuml", s))
			return UML;

		if (StartUtils.startsWithSymbolAnd("startdot", s))
			return DOT;

		if (StartUtils.startsWithSymbolAnd("startjcckit", s))
			return JCCKIT;

		if (StartUtils.startsWithSymbolAnd("startditaa", s))
			return DITAA;

		if (StartUtils.startsWithSymbolAnd("startproject", s))
			return PROJECT;

		if (StartUtils.startsWithSymbolAnd("startsalt", s))
			return SALT;

		if (StartUtils.startsWithSymbolAnd("startflow", s))
			return FLOW;

		if (StartUtils.startsWithSymbolAnd("startcreole", s))
			return CREOLE;

		if (StartUtils.startsWithSymbolAnd("starttree", s))
			return JUNGLE;

		if (StartUtils.startsWithSymbolAnd("startcute", s))
			return CUTE;

		if (StartUtils.startsWithSymbolAnd("startmath", s))
			return MATH;

		if (StartUtils.startsWithSymbolAnd("startlatex", s))
			return LATEX;

		if (StartUtils.startsWithSymbolAnd("startdef", s))
			return DEFINITION;

		if (StartUtils.startsWithSymbolAnd("startgantt", s))
			return GANTT;

		if (StartUtils.startsWithSymbolAnd("startnwdiag", s))
			return NW;

		if (StartUtils.startsWithSymbolAnd("startmindmap", s))
			return MINDMAP;

		if (StartUtils.startsWithSymbolAnd("startwbs", s))
			return WBS;

		if (StartUtils.startsWithSymbolAnd("startjson", s))
			return JSON;

		if (StartUtils.startsWithSymbolAnd("startgit", s))
			return GIT;

		if (StartUtils.startsWithSymbolAnd("startboard", s))
			return BOARD;

		if (StartUtils.startsWithSymbolAnd("startyaml", s))
			return YAML;

		if (StartUtils.startsWithSymbolAnd("starthcl", s))
			return HCL;

		if (StartUtils.startsWithSymbolAnd("startebnf", s))
			return EBNF;

		if (StartUtils.startsWithSymbolAnd("startregex", s))
			return REGEX;

		return UNKNOWN;
	}
}

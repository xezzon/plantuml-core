package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;

public class Verbs {

	public static IRegex are = new RegexLeaf("are");

	public static IRegex areColored = new RegexLeaf("are[%s]+colou?red");

	public static IRegex displayOnSameRowAs = new RegexLeaf("displays?[%s]+on[%s]+same[%s]+row[%s]+as");

	public static IRegex ends = new RegexLeaf("ends");

	public static IRegex ends2 = new RegexLeaf("ends[%s]*(the[%s]*|on[%s]*|at[%s]*)*");

	public static IRegex happens = new RegexLeaf("happens?[%s]*(at[%s]*|the[%s]*|on[%s]*)*");

	public static IRegex pauses = new RegexLeaf("pauses?[%s]*(at[%s]*|the[%s]*|on[%s]*|from[%s]*)*");

	public static IRegex isDeleted = new RegexLeaf("is[%s]+deleted");

	public static IRegex is = new RegexLeaf("is");

	public static IRegex isColored = new RegexLeaf("is[%s]+colou?red");

	public static IRegex isColoredForCompletion = new RegexLeaf("is[%s]+colou?red[%s]+for[%s]+completion");

	public static IRegex isOff = new RegexConcat(new RegexLeaf("is"), //
			RegexLeaf.spaceOneOrMore(), //
			new RegexLeaf("off"), //
			RegexLeaf.spaceOneOrMore(), //
			new RegexOr(//
					new RegexLeaf("on"), //
					new RegexLeaf("for"), //
					new RegexLeaf("the"), //
					new RegexLeaf("at") //
			));

	public static IRegex isOn = new RegexConcat(new RegexLeaf("is"), //
			RegexLeaf.spaceOneOrMore(), //
			new RegexLeaf("on"), //
			RegexLeaf.spaceOneOrMore(), //
			new RegexOr(//
					new RegexLeaf("on"), //
					new RegexLeaf("for"), //
					new RegexLeaf("the"), //
					new RegexLeaf("at") //
			) //
	);

	public static IRegex isOrAre = new RegexLeaf("(is|are)");

	public static IRegex isOrAreNamed = new RegexLeaf("(is|are)[%s]+named");

	public static IRegex lasts = new RegexLeaf("(lasts|requires)");

	public static IRegex linksTo = new RegexLeaf("links to");

	public static IRegex occurs = new RegexLeaf("occurs?");

	public static IRegex starts3 = new RegexLeaf("starts[%s]*(the[%s]*|on[%s]*|at[%s]*)*");

	public static IRegex starts2 = new RegexLeaf("starts");

	public static IRegex starts = new RegexConcat(new RegexLeaf("start"), //
			new RegexOptional(new RegexLeaf("s")), //
			RegexLeaf.spaceZeroOrMore(), //
			new RegexOptional(new RegexOr(//
					new RegexLeaf("on"), //
					new RegexLeaf("for"), //
					new RegexLeaf("the"), //
					new RegexLeaf("at") //
			)) //
	);

	public static IRegex just = new RegexLeaf("just");

	public static IRegex justBefore = new RegexLeaf("just[%s]*before");

	public static IRegex justAfter = new RegexLeaf("just[%s]*after");

}

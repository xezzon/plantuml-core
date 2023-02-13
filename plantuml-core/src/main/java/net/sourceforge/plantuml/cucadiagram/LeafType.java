package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.StringUtils;

public enum LeafType {

	EMPTY_PACKAGE,

	ABSTRACT_CLASS, CLASS, INTERFACE, ANNOTATION, PROTOCOL, STRUCT, EXCEPTION, METACLASS, STEREOTYPE, LOLLIPOP_FULL, LOLLIPOP_HALF, NOTE, TIPS,
	OBJECT, MAP, JSON, ASSOCIATION, ENUM, CIRCLE,

	USECASE, USECASE_BUSINESS,

	DESCRIPTION,

	ARC_CIRCLE,

	ACTIVITY, BRANCH, SYNCHRO_BAR, CIRCLE_START, CIRCLE_END, POINT_FOR_ASSOCIATION, ACTIVITY_CONCURRENT,

	STATE, STATE_CONCURRENT, PSEUDO_STATE, DEEP_HISTORY, STATE_CHOICE, STATE_FORK_JOIN,

	BLOCK, ENTITY,

	DOMAIN, REQUIREMENT,

	PORTIN, PORTOUT,

	STILL_UNKNOWN;

	public static LeafType getLeafType(String type) {
		type = StringUtils.goUpperCase(type);
		if (type.startsWith("ABSTRACT"))
			return LeafType.ABSTRACT_CLASS;

		if (type.startsWith("DIAMOND"))
			return LeafType.STATE_CHOICE;

		if (type.startsWith("STATIC"))
			return LeafType.CLASS;

		return LeafType.valueOf(type);
	}

	public boolean isLikeClass() {
		return this == LeafType.ANNOTATION || this == LeafType.ABSTRACT_CLASS || this == LeafType.CLASS
				|| this == LeafType.INTERFACE || this == LeafType.ENUM || this == LeafType.ENTITY
				|| this == LeafType.PROTOCOL || this == LeafType.STRUCT || this == LeafType.EXCEPTION
				|| this == LeafType.METACLASS || this == LeafType.STEREOTYPE;
	}

	public String toHtml() {
		final String html = StringUtils.goLowerCase(toString().replace('_', ' '));
		return StringUtils.capitalize(html);
	}

//	public boolean manageModifier() {
//		if (this == ANNOTATION || this == ABSTRACT_CLASS || this == CLASS || this == INTERFACE || this == ENUM
//				|| this == OBJECT || this == ENTITY) {
//			return true;
//		}
//		return false;
//	}
}

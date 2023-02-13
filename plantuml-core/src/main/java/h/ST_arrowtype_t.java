package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_arrowtype_t extends UnsupportedStarStruct {

	public int type;
	public double lenfact;
	public CFunction gen;

	@Override
	public void ___(__struct__ other) {
		ST_arrowtype_t other2 = (ST_arrowtype_t) other;
		type = other2.type;
		lenfact = other2.lenfact;
		gen = other2.gen;
	}


}

// typedef struct arrowtype_t {
// int type;
// double lenfact; /* ratio of length of this arrow type to standard arrow */
// void (*gen) (GVJ_t * job, pointf p, pointf u, double arrowsize, double penwidth, int flag); /* generator function for
// type */
// } arrowtype_t;

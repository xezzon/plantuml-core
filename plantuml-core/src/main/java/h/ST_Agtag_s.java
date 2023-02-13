package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_Agtag_s extends UnsupportedStarStruct {
	public int objtype;
	public int mtflock;
	public int attrwf;
	public int seq;
	public int id;

	@Override
	public String toString() {
		return "id=" + id + " " + super.toString();
	}

	@Override
	public ST_Agtag_s copy() {
		final ST_Agtag_s result = new ST_Agtag_s();
		result.objtype = objtype;
		result.mtflock = mtflock;
		result.attrwf = attrwf;
		result.seq = seq;
		result.id = id;
		return result;
	}

	@Override
	public void ___(__struct__ other) {
		final ST_Agtag_s other2 = (ST_Agtag_s) other;
		objtype = other2.objtype;
		mtflock = other2.mtflock;
		attrwf = other2.attrwf;
		seq = other2.seq;
		id = other2.id;
	}

}

// struct Agtag_s {
// unsigned objtype:2; /* see literal tags below */
// unsigned mtflock:1; /* move-to-front lock, see above */
// unsigned attrwf:1; /* attrs written (parity, write.c) */
// unsigned seq:(sizeof(unsigned) * 8 - 4); /* sequence no. */
// unsigned long id; /* client ID */
// };

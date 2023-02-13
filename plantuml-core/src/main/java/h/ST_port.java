package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_port extends UnsupportedStarStruct {

	public final ST_pointf p = new ST_pointf();
	public double theta;
	public ST_boxf bp;
	public boolean defined;
	public boolean constrained;
	public boolean clip;
	public boolean dyna;
	public int order;
	public int side;
	public CString name;




	@Override
	public void ___(__struct__ other) {
		ST_port other2 = (ST_port) other;
		this.p.___(other2.p);
		this.theta = other2.theta;
		this.bp = other2.bp;
		this.defined = other2.defined;
		this.constrained = other2.constrained;
		this.clip = other2.clip;
		this.dyna = other2.dyna;
		this.order = other2.order;
		this.side = other2.side;
		this.name = other2.name;
	}

	@Override
	public ST_port copy() {
		final ST_port result = new ST_port();
		result.p.___(this.p);
		result.theta = this.theta;
		result.bp = this.bp;
		result.defined = this.defined;
		result.constrained = this.constrained;
		result.clip = this.clip;
		result.dyna = this.dyna;
		result.order = this.order;
		result.side = this.side;
		result.name = this.name;
		return result;
	}



}

// typedef struct port { /* internal edge endpoint specification */
// pointf p; /* aiming point relative to node center */
// double theta; /* slope in radians */
// boxf *bp; /* if not null, points to bbox of
// * rectangular area that is port target
// */
// boolean defined; /* if true, edge has port info at this end */
// boolean constrained; /* if true, constraints such as theta are set */
// boolean clip; /* if true, clip end to node/port shape */
// boolean dyna; /* if true, assign compass point dynamically */
// unsigned char order; /* for mincross */
// unsigned char side; /* if port is on perimeter of node, this
// * contains the bitwise OR of the sides (TOP,
// * BOTTOM, etc.) it is on.
// */
// char *name; /* port name, if it was explicitly given, otherwise NULL */
// } port;

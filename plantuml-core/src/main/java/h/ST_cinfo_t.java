package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_cinfo_t extends UnsupportedStarStruct {

	public final ST_boxf bb = new ST_boxf();
	public CArray<ST_object_t> objp;



	
	@Override
	public void ___(__struct__ value) {
		final ST_cinfo_t other = (ST_cinfo_t) value;
		this.bb.___(other.bb);
		this.objp = other.objp;
	}


	@Override
	public ST_cinfo_t copy() {
		final ST_cinfo_t result = new ST_cinfo_t();
		result.bb.___((__struct__) this.bb);
		result.objp = this.objp;
		return result;
	}




}

// typedef struct {
// boxf bb;
// object_t* objp;
// } cinfo_t;

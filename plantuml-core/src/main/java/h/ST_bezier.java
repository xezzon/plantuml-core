package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_bezier extends UnsupportedStarStruct {


	public CArray<ST_pointf> list;

	public int size;
	public int sflag, eflag;

	public final ST_pointf sp = new ST_pointf(), ep = new ST_pointf();


	@Override
	public void ___(__struct__ other) {
		ST_bezier this2 = (ST_bezier) other;
		this.list = this2.list;
		this.size = this2.size;
		this.sflag = this2.sflag;
		this.eflag = this2.eflag;
		this.sp.___((__struct__) this2.sp);
		this.ep.___((__struct__) this2.ep);
	}


}

// typedef struct bezier {
// pointf *list;
// int size;
// int sflag, eflag;
// pointf sp, ep;
// } bezier;

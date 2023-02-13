package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_point extends UnsupportedStarStruct {


	public int x, y;



	@Override
	public __struct__ copy() {
		final ST_point result = new ST_point();
		result.x = this.x;
		result.y = this.y;
		return result;
	}

	@Override
	public void ___(__struct__ other) {
		ST_point this2 = (ST_point) other;
		this.x = this2.x;
		this.y = this2.y;
	}

}

// typedef struct { int x, y; } point;

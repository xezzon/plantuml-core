package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_Rect_t extends UnsupportedStarStruct {

	public final int[] boundary = new int[2 * 2];


	@Override
	public void ___(__struct__ other) {
		ST_Rect_t other2 = (ST_Rect_t) other;
		this.boundary[0] = other2.boundary[0];
		this.boundary[1] = other2.boundary[1];
		this.boundary[2] = other2.boundary[2];
		this.boundary[3] = other2.boundary[3];
	}

	// typedef struct Rect {
	// int boundary[2*2];
	// } Rect_t;
}

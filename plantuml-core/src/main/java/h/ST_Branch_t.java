package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_Branch_t extends UnsupportedStarStruct {

	// Warning : could be a "ST_Leaf_t" from C Version
	public final ST_Rect_t rect = new ST_Rect_t();
	public ST_Node_t___or_object_t child; // "data" : ST_object_t



	@Override
	public void ___(__struct__ other) {
		ST_Branch_t this2 = (ST_Branch_t) other;
		this.rect.___((__struct__) this2.rect);
		this.child = this2.child;
	}

	// typedef struct Branch {
	// Rect_t rect;
	// struct Node *child;
	// } Branch_t;
}

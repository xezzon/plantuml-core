package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_pack_info extends UnsupportedStarStruct {

	public int sz;
	public int margin;
	public int doSplines;
	// "pack_mode mode",
	public EN_pack_mode mode;
	public __ptr__ fixed;
	// "boolean *fixed",
	// "packval_t* vals",
	public __ptr__ vals;
	public int flags;


}

// typedef struct {
// float aspect; /* desired aspect ratio */
// int sz; /* row/column size size */
// unsigned int margin; /* margin left around objects, in points */
// int doSplines; /* use splines in constructing graph shape */
// pack_mode mode; /* granularity and method */
// boolean *fixed; /* fixed[i] == true implies g[i] should not be moved */
// packval_t* vals; /* for arrays, sort numbers */
// int flags;
// } pack_info;

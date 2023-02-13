package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;

final public class ST_shape_desc extends UnsupportedStarStruct {


	public CString name;
	public ST_shape_functions fns;
	public ST_polygon_t polygon;
	public boolean usershape;



}

// typedef struct shape_desc { /* read-only shape descriptor */
// char *name; /* as read from graph file */
// shape_functions *fns;
// polygon_t *polygon; /* base polygon info */
// boolean usershape;
// } shape_desc;

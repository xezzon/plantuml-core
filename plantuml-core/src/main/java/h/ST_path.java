package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_path extends UnsupportedStarStruct {


	final public ST_port start = new ST_port(), end = new ST_port();
	public int nbox;
	public ST_boxf boxes[];

	public ST_Agedge_s data;



}

// typedef struct path { /* internal specification for an edge spline */
// port start, end;
// int nbox; /* number of subdivisions */
// boxf *boxes; /* rectangular regions of subdivision */
// void *data;
// } path;

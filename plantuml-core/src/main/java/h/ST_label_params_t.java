package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_label_params_t extends UnsupportedStarStruct {

	public final ST_boxf bb = new ST_boxf();
	public boolean force;


	
}

// typedef struct {
// boxf bb; /* Bounding box of all objects */
// unsigned char force; /* If true, all labels must be placed */
// } label_params_t;

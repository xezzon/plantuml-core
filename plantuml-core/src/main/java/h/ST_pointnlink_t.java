package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_pointnlink_t extends UnsupportedStarStruct {

	public ST_pointf pp;
	public ST_pointnlink_t link;



	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_pointnlink_t other2 = (ST_pointnlink_t) other;
		return this == other2;
	}



}

// typedef struct pointnlink_t {
// Ppoint_t *pp;
// struct pointnlink_t *link;
// } pointnlink_t;

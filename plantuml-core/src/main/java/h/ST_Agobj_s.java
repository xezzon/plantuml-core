package h;

import smetana.core.UnsupportedStarStruct;

public class ST_Agobj_s extends UnsupportedStarStruct {

	public final ST_Agtag_s tag = new ST_Agtag_s();
	public ST_Agrec_s data;

}

// struct Agobj_s {
// Agtag_t tag;
// Agrec_t *data;
// };

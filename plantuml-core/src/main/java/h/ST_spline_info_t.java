package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_spline_info_t extends UnsupportedStarStruct {


	public int LeftBound, RightBound, Splinesep, Multisep;
	public ST_boxf Rank_box[];


}

// typedef struct {
// int LeftBound, RightBound, Splinesep, Multisep;
// boxf* Rank_box;
// } spline_info_t;

package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;

final public class ST_tedge_t extends UnsupportedStarStruct {


	public ST_pointnlink_t pnl0p;
	public ST_pointnlink_t pnl1p;

	public CArray<ST_triangle_t> lrp;
	public CArray<ST_triangle_t> rtp;





}

// typedef struct tedge_t {
// pointnlink_t *pnl0p;
// pointnlink_t *pnl1p;
// struct triangle_t *ltp;
// struct triangle_t *rtp;
// } tedge_t;

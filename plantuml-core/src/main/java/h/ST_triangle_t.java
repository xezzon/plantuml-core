package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_triangle_t extends UnsupportedStarStruct {

	public int mark;

	public final ST_tedge_t e[] = new ST_tedge_t[] { new ST_tedge_t(), new ST_tedge_t(), new ST_tedge_t() };



}

// typedef struct triangle_t {
// int mark;
// struct tedge_t e[3];
// } triangle_t;

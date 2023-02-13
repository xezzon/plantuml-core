package h;

import smetana.core.CArray;
import smetana.core.UnsupportedStarStruct;

final public class ST_polygon_t extends UnsupportedStarStruct implements SHAPE_INFO {

	public boolean regular;
	public int peripheries;
	public int sides;
	public double orientation;
	public double distortion;
	public double skew;
	public int option;
	public CArray<ST_pointf> vertices;

}

// typedef struct polygon_t { /* mutable shape information for a node */
// int regular; /* true for symmetric shapes */
// int peripheries; /* number of periphery lines */
// int sides; /* number of sides */
// double orientation; /* orientation of shape (+ve degrees) */
// double distortion; /* distortion factor - as in trapezium */
// double skew; /* skew factor - as in parallelogram */
// int option; /* ROUNDED, DIAGONAL corners, etc. */
// pointf *vertices; /* array of vertex points */
// } polygon_t;

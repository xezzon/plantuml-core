package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_PartitionVars extends UnsupportedStarStruct {

	public final int[] partition = new int[64 + 1];
	public final int[] taken = new int[64 + 1];
	public final int[] count = new int[2];
	public final ST_Rect_t[] cover = new ST_Rect_t[] { new ST_Rect_t(), new ST_Rect_t() };
	public final int[] area = new int[2];

	// struct PartitionVars {
	// int partition[64 + 1];
	// int taken[64 + 1];
	// int count[2];
	// struct Rect cover[2];
	// int area[2];
	// };

}

package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_SplitQ_t extends UnsupportedStarStruct {


	// Sorry guys :-)
	public final ST_Branch_t BranchBuf[] = new ST_Branch_t[] { new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(), new ST_Branch_t(),
			new ST_Branch_t(), new ST_Branch_t() };

	public final ST_Rect_t CoverSplit = new ST_Rect_t();
	public int CoverSplitArea;

	public final ST_PartitionVars Partitions[] = new ST_PartitionVars[] { new ST_PartitionVars() };



	@Override
	public ST_Rect_t castTo(Class dest) {
		if (dest == ST_Rect_t.class) {
			return CoverSplit;
		}
		throw new UnsupportedOperationException();
	}



	// typedef struct split_q_s {
	// struct Branch BranchBuf[64 + 1];
	// struct Rect CoverSplit;
	// unsigned int CoverSplitArea;
	// struct PartitionVars Partitions[1];
	// } SplitQ_t;
}

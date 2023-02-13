package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__struct__;

final public class ST_boxf extends UnsupportedStarStruct {

	public final ST_pointf LL = new ST_pointf();
	public final ST_pointf UR = new ST_pointf();

	@Override
	public String toString() {
		return "LL=" + LL + " UR=" + UR;
	}

	public static ST_boxf[] malloc(int nb) {
		final ST_boxf result[] = new ST_boxf[nb];
		for (int i = 0; i < nb; i++) {
			result[i] = new ST_boxf();
		}
		return result;
	}

	@Override
	public ST_boxf copy() {
		final ST_boxf result = new ST_boxf();
		result.LL.___((__struct__) this.LL);
		result.UR.___((__struct__) this.UR);
		return result;
	}

	@Override
	public void ___(__struct__ value) {
		final ST_boxf other = (ST_boxf) value;
		this.LL.___(other.LL);
		this.UR.___(other.UR);
	}

}

// typedef struct { pointf LL, UR; } boxf;

package h;

import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;
import smetana.core.__struct__;

final public class ST_pointf extends UnsupportedStarStruct {

	public static ST_pointf pointfof(double x, double y) {
		final ST_pointf result = new ST_pointf();
		result.x = x;
		result.y = y;
		return result;
	}

	public static ST_pointf add_pointf(final ST_pointf p, final ST_pointf q) {
		final ST_pointf result = new ST_pointf();
		result.x = p.x + q.x;
		result.y = p.y + q.y;
		return result;
	}

	public double x;
	public double y;

	@Override
	public boolean isSameThan(__ptr__ other) {
		return this == (ST_pointf) other;
	}
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}


	@Override
	public ST_pointf copy() {
		final ST_pointf result = new ST_pointf();
		result.x = this.x;
		result.y = this.y;
		return result;
	}

	@Override
	public void ___(__struct__ other) {
		final ST_pointf other2 = (ST_pointf) other;
		this.x = other2.x;
		this.y = other2.y;
	}

}

// typedef struct pointf_s { double x, y; } pointf;

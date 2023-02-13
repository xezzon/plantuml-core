package h;

import smetana.core.OFFSET;
import smetana.core.__ptr__;
import smetana.core.__struct__;
import smetana.core.debug.SmetanaDebug;

final public class ST_Agraph_s extends ST_Agobj_s {

	public final ST_Agobj_s base = this;
	public final ST_Agdesc_s desc = new ST_Agdesc_s();
	public final ST_dtlink_s link = new ST_dtlink_s(this);

	public ST_dt_s n_seq; /* the node set in sequence */
	public ST_dt_s n_id; /* the node set indexed by ID */
	public ST_dt_s e_seq; /* holders for edge sets */
	public ST_dt_s e_id; /* holders for edge sets */
	public ST_dt_s g_dict; /* subgraphs - descendants */
	public ST_Agraph_s parent; /* subgraphs - ancestors */
	public ST_Agraph_s root; /* subgraphs - ancestors */
	public ST_Agclos_s clos; /* shared resources */

	public String NAME;
	private static int CPT = 0;

	@Override
	public String toString() {
		return super.toString() + " " + NAME;
	}
	
	public ST_Agraph_s() {
		this.NAME = "G" + CPT;
		CPT++;
		SmetanaDebug.LOG("creation " + this);
	}


	@Override
	public Object getTheField(OFFSET offset) {
		if (offset == null || offset.getSign()==0) {
			return this;
		}
		if (offset.getField().equals("link")) {
			return link;
		}
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void ___(__struct__ arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_Agraph_s other2 = (ST_Agraph_s) other;
		return this == other2;
	}

}

// struct Agraph_s {
// Agobj_t base;
// Agdesc_t desc;
// Dtlink_t link;
// Dict_t *n_seq; /* the node set in sequence */
// Dict_t *n_id; /* the node set indexed by ID */
// Dict_t *e_seq, *e_id; /* holders for edge sets */
// Dict_t *g_dict; /* subgraphs - descendants */
// Agraph_t *parent, *root; /* subgraphs - ancestors */
// Agclos_t *clos; /* shared resources */
// };

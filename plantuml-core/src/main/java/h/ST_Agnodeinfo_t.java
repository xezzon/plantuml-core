// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package h;

final public class ST_Agnodeinfo_t extends ST_Agrec_s {

	public ST_shape_desc shape;
	public SHAPE_INFO shape_info;
	// public ST_polygon_t shape_info;
	public final ST_pointf coord = new ST_pointf();
	public double width, height;

	// "boxf bb",
	public double ht, lw, rw;
	public ST_textlabel_t label;
	public ST_textlabel_t xlabel;
	// "void *alg",
	public ST_Agedge_s alg = null;
	// "char state",
	// "unsigned char gui_state",
	// "boolean clustnode",
	// "unsigned char pinned",
	public int id, heapindex, hops;
	// "double *pos, dist",
	public int showboxes;

	public boolean has_port;
	// "node_t* rep",
	// "node_t *set",
	public int node_type, mark, onstack;
	public int ranktype, weight_class;
	public ST_Agnode_s next;
	public ST_Agnode_s prev;
	// "elist in, out, flat_out, flat_in, other",
	public final ST_elist in = new ST_elist();
	public final ST_elist out = new ST_elist();
	public final ST_elist flat_out = new ST_elist();
	public final ST_elist flat_in = new ST_elist();
	public final ST_elist other = new ST_elist();
	public ST_Agraph_s clust;
	public int UF_size;

	public ST_Agnode_s UF_parent;

	public ST_Agnode_s inleaf, outleaf;
	public int rank, order;
	public double mval;
	public final ST_elist save_in = new ST_elist();
	public final ST_elist save_out = new ST_elist();
	public final ST_elist tree_in = new ST_elist();
	public final ST_elist tree_out = new ST_elist();
	public ST_Agedge_s par;
	public int low, lim;
	public int priority;


	
}

// typedef struct Agnodeinfo_t {
// Agrec_t hdr;
// shape_desc *shape;
// void *shape_info;
// pointf coord;
// double width, height; /* inches */
// boxf bb;
// double ht, lw, rw;
// textlabel_t *label;
// textlabel_t *xlabel;
// void *alg;
// char state;
// unsigned char gui_state; /* Node state for GUI ops */
// boolean clustnode;
//
//
// unsigned char pinned;
// int id, heapindex, hops;
// double *pos, dist;
//
//
// unsigned char showboxes;
// boolean has_port;
// node_t* rep;
// node_t *set;
//
// /* fast graph */
// char node_type, mark, onstack;
// char ranktype, weight_class;
// node_t *next, *prev;
// elist in, out, flat_out, flat_in, other;
// graph_t *clust;
//
// /* for union-find and collapsing nodes */
// int UF_size;
// node_t *UF_parent;
// node_t *inleaf, *outleaf;
//
// /* for placing nodes */
// int rank, order; /* initially, order = 1 for ordered edges */
// double mval;
// elist save_in, save_out;
//
// /* for network-simplex */
// elist tree_in, tree_out;
// edge_t *par;
// int low, lim;
// int priority;
//
// double pad[1];
//
//
// } Agnodeinfo_t;

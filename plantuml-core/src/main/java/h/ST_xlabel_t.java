package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_xlabel_t extends UnsupportedStarStruct {

	final public ST_pointf sz = new ST_pointf();
	final public ST_pointf pos = new ST_pointf();

	public ST_textlabel_t lbl;
	public int set;



}

// typedef struct {
// pointf sz; /* Size of label (input) */
// pointf pos; /* Position of lower-left corner of label (output) */
// void *lbl; /* Pointer to label in the graph */
// unsigned char set; /* True if the position has been set (input/output) */
// } xlabel_t;

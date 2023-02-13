package h;

import smetana.core.CArray;
import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_textlabel_t extends UnsupportedStarStruct {

	public CString text, fontname, fontcolor;
	public int charset;
	public double fontsize;

	public final ST_pointf dimen = new ST_pointf();
	public final ST_pointf space = new ST_pointf();
	public final ST_pointf pos = new ST_pointf();

	public CArray<ST_textspan_t> span;
	public int nspans;

	public final __ptr__ html__ = null;
	public int valign;

	public int set;

	public boolean html;

	@Override
	public String toString() {
		return text + " " + dimen;
	}

	@Override
	public boolean isSameThan(__ptr__ other) {
		ST_textlabel_t other2 = (ST_textlabel_t) other;
		return this == other2;
	}

}

// typedef struct textlabel_t {
// char *text, *fontname, *fontcolor;
// int charset;
// double fontsize;
// pointf dimen; /* the diagonal size of the label (estimated by layout) */
// pointf space; /* the diagonal size of the space for the label */
// /* the rendered label is aligned in this box */
// /* space does not include pad or margin */
// pointf pos; /* the center of the space for the label */
// union {
// struct {
// textspan_t *span;
// short nspans;
// } txt;
// htmllabel_t *html;
// } u;
// char valign; /* 't' 'c' 'b' */
// boolean set; /* true if position is set */
// boolean html; /* true if html label */
// } textlabel_t;

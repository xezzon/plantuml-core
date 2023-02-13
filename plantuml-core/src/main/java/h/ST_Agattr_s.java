package h;

import java.util.List;

import smetana.core.CString;

final public class ST_Agattr_s extends ST_Agrec_s {

	public ST_dt_s dict; /* shared dict to interpret attr field */
	public List<CString> str; /* the attribute string values */

}

// struct Agattr_s { /* dynamic string attributes */
// Agrec_t h; /* common data header */
// Dict_t *dict; /* shared dict to interpret attr field */
// char **str; /* the attribute string values */
// };

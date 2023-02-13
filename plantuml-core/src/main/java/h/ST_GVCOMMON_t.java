package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;
import smetana.core.__ptr__;

final public class ST_GVCOMMON_t extends UnsupportedStarStruct {


	public __ptr__ info;
	// "char *cmdname",
	// "int verbose",
	// "boolean config, auto_outfile_names",
	// "void (*errorfn) (const char *fmt, ...)",
	public CFunction errorfn;
	// "const char **show_boxes",
	// "const char **lib",
	// "int viewNum",
	// "const lt_symlist_t *builtins",
	public __ptr__ builtins;
	public boolean demand_loading;


}

// typedef struct GVCOMMON_s {
// char **info;
// char *cmdname;
// int verbose;
// boolean config, auto_outfile_names;
// void (*errorfn) (const char *fmt, ...);
// const char **show_boxes; /* emit code for correct box coordinates */
// const char **lib;
//
// /* rendering state */
// int viewNum; /* current view - 1 based count of views,
// all pages in all layers */
// const lt_symlist_t *builtins;
// int demand_loading;
// } GVCOMMON_t;

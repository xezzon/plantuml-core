package h;

import smetana.core.CString;
import smetana.core.UnsupportedStarStruct;

final public class ST_gvplugin_installed_t extends UnsupportedStarStruct {


	public EN_layout_type id;
	public CString type;
	public int quality;

	public ST_gvlayout_engine_s engine;
	public ST_gvlayout_features_t features;



}

// typedef struct {
// int id; /* an id that is only unique within a package
// of plugins of the same api.
// A renderer-type such as "png" in the cairo package
// has an id that is different from the "ps" type
// in the same package */
// const char *type; /* a string name, such as "png" or "ps" that
// distinguishes different types withing the same
// (renderer in this case) */
// int quality; /* an arbitrary integer used for ordering plugins of
// the same type from different packages */
// void *engine; /* pointer to the jump table for the plugin */
// void *features; /* pointer to the feature description
// void* because type varies by api */
// } gvplugin_installed_t;

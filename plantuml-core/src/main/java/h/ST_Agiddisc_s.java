package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;

final public class ST_Agiddisc_s extends UnsupportedStarStruct {

	public CFunction open;
	public CFunction map;
	public CFunction alloc;
	public CFunction free;
	public CFunction print;
	public CFunction close;
	public CFunction idregister;



}

// struct Agiddisc_s { /* object ID allocator */
// void *(*open) (Agraph_t * g, Agdisc_t*); /* associated with a graph */
// long (*map) (void *state, int objtype, char *str, unsigned long *id,
// int createflag);
// long (*alloc) (void *state, int objtype, unsigned long id);
// void (*free) (void *state, int objtype, unsigned long id);
// char *(*print) (void *state, int objtype, unsigned long id);
// void (*close) (void *state);
// void (*idregister) (void *state, int objtype, void *obj);
// };

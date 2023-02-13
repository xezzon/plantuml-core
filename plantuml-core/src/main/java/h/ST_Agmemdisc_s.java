package h;

import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;

final public class ST_Agmemdisc_s extends UnsupportedStarStruct {

	public CFunction open;
	public CFunction alloc;
	public CFunction resize;
	public CFunction free;
	public CFunction close;


}

// struct Agmemdisc_s { /* memory allocator */
// void *(*open) (Agdisc_t*); /* independent of other resources */
// void *(*alloc) (void *state, size_t req);
// void *(*resize) (void *state, void *ptr, size_t old, size_t req);
// void (*free) (void *state, void *ptr);
// void (*close) (void *state);
// };

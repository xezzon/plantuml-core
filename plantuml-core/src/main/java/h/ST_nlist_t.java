package h;

import smetana.core.CArrayOfStar;
import smetana.core.UnsupportedStarStruct;

final public class ST_nlist_t extends UnsupportedStarStruct {

	public CArrayOfStar<ST_Agnode_s> list;
	public int size;


}

// typedef struct nlist_t {
// node_t **list;
// int size;
// } nlist_t;

package h;
import smetana.core.CFunction;
import smetana.core.UnsupportedStarStruct;

final public class ST_Agiodisc_s extends UnsupportedStarStruct {
	
	public CFunction afread;
	public CFunction putstr;
	public CFunction flush;


}

// struct Agiodisc_s {
//     int (*afread) (void *chan, char *buf, int bufsize);
//     int (*putstr) (void *chan, const char *str);
//     int (*flush) (void *chan);	/* sync */
//     /* error messages? */
// };

package gen.lib.dotgen;
import static gen.lib.cgraph.attr__c.agget;
import static smetana.core.Macro.N;
import static smetana.core.Macro.UNSUPPORTED;
import static smetana.core.debug.SmetanaDebug.ENTERING;
import static smetana.core.debug.SmetanaDebug.LEAVING;

import gen.annotation.Original;
import gen.annotation.Reviewed;
import gen.annotation.Unused;
import h.ST_Agraph_s;
import h.ST_aspect_t;
import smetana.core.CString;

public class aspect__c {
	
private static final int DEF_PASSES = 5;



//3 25p8yyx732fa1dwxany5vkmqv
// void rank3(graph_t * g, aspect_t * asp) 
@Unused
@Original(version="2.38.0", path="lib/dotgen/aspect.c", name="rank3", key="25p8yyx732fa1dwxany5vkmqv", definition="void rank3(graph_t * g, aspect_t * asp)")
public static Object rank3(Object... arg_) {
UNSUPPORTED("ey9g3c61eb7j5dnz3gbhvflt3"); // void rank3(graph_t * g, aspect_t * asp)


throw new UnsupportedOperationException();
}




//3 5srsfxqlego6qiyj5mm8m4ql2
// aspect_t* setAspect (Agraph_t * g, aspect_t* adata) 
@Reviewed(when = "12/11/2020")
@Original(version="2.38.0", path="lib/dotgen/aspect.c", name="setAspect", key="5srsfxqlego6qiyj5mm8m4ql2", definition="aspect_t* setAspect (Agraph_t * g, aspect_t* adata)")
public static ST_aspect_t setAspect(ST_Agraph_s g, ST_aspect_t adata) {
ENTERING("5srsfxqlego6qiyj5mm8m4ql2","setAspect");
try {
    double rv;
    CString p;
    int r, passes = DEF_PASSES;
    p = agget (g, new CString("aspect"));
    if (N(p) || UNSUPPORTED("(r = sscanf (p, %lf,%d, &rv, &passes)) <= 0)")==null) {
	adata.nextIter = 0;
	adata.badGraph = 0;
	return null;
    }
UNSUPPORTED("bq1l9wqiw8bcls8ptb8o9rhqi"); //     agerr (AGWARN, "the aspect attribute has been disabled due to implementation flaws - attribute ignored.\n");
UNSUPPORTED("1hvtnh8r9fb47ewad86tcf0n0"); //     adata->nextIter = 0;
UNSUPPORTED("xx058mix2zyckbi0esnoc56v"); //     adata->badGraph = 0;
UNSUPPORTED("o68dp934ebg4cplebgc5hv4v"); //     return NULL;
UNSUPPORTED("ikxv7u2qba6riujs2unqsitx"); //     if (rv < 1.0) rv = 1.0;
UNSUPPORTED("acmmvkcaf8bc8wb6kdx9s2d3a"); //     else if (rv > 20.0) rv = 20.0;
UNSUPPORTED("18wyf3ih8fg4x4s3c006nx8zx"); //     adata->targetAR = rv;
UNSUPPORTED("2875r01nyy2cpm1c439zwu6tf"); //     adata->nextIter = -1;
UNSUPPORTED("9ssjc7jsfl2jgw01gwnr3ftal"); //     adata->nPasses = passes;
UNSUPPORTED("xx058mix2zyckbi0esnoc56v"); //     adata->badGraph = 0;
UNSUPPORTED("620q0efwdrg8vw3ggwpmxqiwv"); //     if (Verbose) 
UNSUPPORTED("a7heh0decro63skjh8xq4buo7"); //         fprintf(stderr, "Target AR = %g\n", adata->targetAR);
UNSUPPORTED("b1cn763x4de1h91cdv3d4o3lb"); //     return adata;
UNSUPPORTED("c24nfmv9i7o5eoqaymbibp7m7"); // }

throw new UnsupportedOperationException();
} finally {
LEAVING("5srsfxqlego6qiyj5mm8m4ql2","setAspect");
}
}


}

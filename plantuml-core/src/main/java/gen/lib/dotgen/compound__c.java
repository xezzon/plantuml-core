package gen.lib.dotgen;
import static smetana.core.Macro.UNSUPPORTED;

import gen.annotation.Original;
import gen.annotation.Unused;

public class compound__c {


//3 8dlmdg7jixgrpxke9pgbbnous
// void dot_compoundEdges(graph_t * g) 
@Unused
@Original(version="2.38.0", path="lib/dotgen/compound.c", name="dot_compoundEdges", key="8dlmdg7jixgrpxke9pgbbnous", definition="void dot_compoundEdges(graph_t * g)")
public static Object dot_compoundEdges(Object... arg_) {
UNSUPPORTED("7ugy3v610tnicjpszcs8fvlvb"); // void dot_compoundEdges(graph_t * g)
UNSUPPORTED("erg9i1970wdri39osu8hx2a6e"); // {
UNSUPPORTED("5gypxs09iuryx5a2eho9lgdcp"); //     edge_t *e;
UNSUPPORTED("cjx5v6hayed3q8eeub1cggqca"); //     node_t *n;
UNSUPPORTED("aice9tv6l0gls54iyk8147m5u"); //     Dt_t* clustMap = mkClustMap (g);
UNSUPPORTED("44thr6ep72jsj3fksjiwdx3yr"); //     for (n = agfstnode(g); n; n = agnxtnode(g, n)) {
UNSUPPORTED("e20lm4qtccvgsfq5fzjv6sjyl"); // 	for (e = agfstout(g, n); e; e = agnxtout(g, e)) {
UNSUPPORTED("1urljgadk0bknazngl2w39evt"); // 	    makeCompoundEdge(g, e, clustMap);
UNSUPPORTED("flupwh3kosf3fkhkxllllt1"); // 	}
UNSUPPORTED("dvgyxsnyeqqnyzq696k3vskib"); //     }
UNSUPPORTED("11epc7udwyi67f5gszznmfwn8"); //     dtclose(clustMap);
UNSUPPORTED("c24nfmv9i7o5eoqaymbibp7m7"); // }

throw new UnsupportedOperationException();
}


}

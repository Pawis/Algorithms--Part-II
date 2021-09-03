import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private final Digraph g;

    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException();
        this.g = new Digraph(G);

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v >= g.V() || v < 0)
            throw new IllegalArgumentException();
        if (w >= g.V() || w < 0)
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(g, w);
        int shortestDist = g.V() + 1;
        for (int i = 0; i < g.V(); i++) {
            if (bfdw.hasPathTo(i) && bfdv.hasPathTo(i))
                if (bfdw.distTo(i) + bfdv.distTo(i) < shortestDist) {
                    shortestDist = bfdw.distTo(i) + bfdv.distTo(i);
                }
        }
        if (shortestDist == g.V() + 1) {
            return -1;

        }
        else {
            return shortestDist;
        }

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v >= g.V() || v < 0)
            throw new IllegalArgumentException();
        if (w >= g.V() || w < 0)
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(g, w);
        int shortestDist = g.V() + 1;
        int ancestor = 0;
        for (int i = 0; i < g.V(); i++) {
            if (bfdw.hasPathTo(i) && bfdv.hasPathTo(i))
                if (bfdw.distTo(i) + bfdv.distTo(i) < shortestDist) {
                    shortestDist = bfdw.distTo(i) + bfdv.distTo(i);
                    ancestor = i;
                }
        }
        if (shortestDist == g.V() + 1) {
            return -1;

        }
        else {
            return ancestor;
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (check(v, w))
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(g, w);
        int shortestDist = g.V() + 1;
        for (int i = 0; i < g.V(); i++) {
            if (bfdw.hasPathTo(i) && bfdv.hasPathTo(i))
                if (bfdw.distTo(i) + bfdv.distTo(i) < shortestDist) {
                    shortestDist = bfdw.distTo(i) + bfdv.distTo(i);
                }
        }
        if (shortestDist == g.V() + 1) {
            return -1;

        }
        else {
            return shortestDist;
        }

    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (check(v, w))
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(g, w);
        int shortestDist = g.V() + 1;
        int ancestor = 0;
        for (int i = 0; i < g.V(); i++) {
            if (bfdw.hasPathTo(i) && bfdv.hasPathTo(i))
                if (bfdw.distTo(i) + bfdv.distTo(i) < shortestDist) {
                    shortestDist = bfdw.distTo(i) + bfdv.distTo(i);
                    ancestor = i;
                }
        }
        if (shortestDist == g.V() + 1) {
            return -1;

        }
        else {
            return ancestor;
        }

    }

    private boolean check(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            return true;
        for (Integer a : v) {
            if (a == null)
                return true;
            else if (a >= g.V() || a < 0)
                return true;
        }
        for (Integer a : w) {
            if (a == null)
                return true;
            else if (a >= g.V() || a < 0)
                return true;
        }
        return false;
    }


    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);


        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
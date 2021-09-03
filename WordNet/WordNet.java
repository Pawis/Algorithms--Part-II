import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class WordNet {
    
    private final HashSet<String> noun = new HashSet<>();
    private final ArrayList<HashSet<String>> synset = new ArrayList<>();
    private final ArrayList<String> synsetsFull = new ArrayList<>();
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException();
        dataStructure(synsets);
        createGraph(hypernyms);

    }

    private void dataStructure(String synsets) {
        final In syn = new In(synsets);
        while (syn.hasNextLine()) {
            String[] parts = syn.readLine().split(",");
            String[] nouns = parts[1].split("\\s");
            HashSet<String> list = new HashSet<>(Arrays.asList(nouns));
            this.synsetsFull.add(parts[1]);
            this.synset.add(list);
            this.noun.addAll(Arrays.asList(nouns));
        }
    }

    private void createGraph(String hypernyms) {
        final In hyp = new In(hypernyms);
        Digraph g = new Digraph(synset.size());
        while (hyp.hasNextLine()) {
            String[] parts = hyp.readLine().split(",");
            for (int i = 1; i < parts.length; i++) {
                g.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[i]));
            }
        }
        boolean root = false;
        int moreRoots = 0;
        for (int i = 0; i < g.V(); i++) {
            Stack<Integer> stak = new Stack<>();
            for (Integer a : g.adj(i)) {
                stak.push(a);
            }
            if (stak.isEmpty()) {
                moreRoots++;
                root = true;
            }
        }

        Topological t = new Topological(g);
        if (!t.hasOrder() || !root || moreRoots > 1) {
            throw new IllegalArgumentException();
        }
        this.sap = new SAP(g);
    }


    // returns all Word.WordNet nouns
    public Iterable<String> nouns() {
        return noun;
    }

    // is the word a Word.WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        return noun.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException();
        if (!noun.contains(nounA) || !noun.contains(nounB))
            throw new IllegalArgumentException();
        Stack<Integer> sybsetA = new Stack<>();
        Stack<Integer> sybsetB = new Stack<>();
        for (int i = 0; i < synset.size(); i++) {
            if (synset.get(i).contains(nounA))
                sybsetA.push(i);
            if (synset.get(i).contains(nounB))
                sybsetB.push(i);
        }
        return sap.length(sybsetA, sybsetB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException();
        if (!noun.contains(nounA) || !noun.contains(nounB))
            throw new IllegalArgumentException();
        Stack<Integer> sybsetA = new Stack<>();
        Stack<Integer> sybsetB = new Stack<>();
        for (int i = 0; i < synset.size(); i++) {
            if (synset.get(i).contains(nounA))
                sybsetA.push(i);
            if (synset.get(i).contains(nounB))
                sybsetB.push(i);
        }

        return synsetsFull.get(sap.ancestor(sybsetA, sybsetB));
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        while (!StdIn.isEmpty()) {
            String v = StdIn.readString();
            String w = StdIn.readString();
            String common = wordnet.sap(v, w);
            int dist = wordnet.distance(v, w);
            System.out.println(
                    "SAP for " + v + " and " + w + " = " + common + "\n" + "Distance = " + dist);

        }

    }

}
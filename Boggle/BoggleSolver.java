/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.SET;

public class BoggleSolver {
    private SET<String> words;
    private final StringBuilder bob = new StringBuilder();
    private final BetterTrie trie;
    private boolean[][] marked;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        this.words = new SET<>();
        this.trie = new BetterTrie(Alphabet.UPPERCASE);
        String[] dict = dictionary.clone();
        for (int i = 0; i < dict.length; i++) {
            trie.put(new StringBuilder(dict[i]), i);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        this.words = new SET<>();
        String[][] boggle = new String[board.rows()][board.cols()];
        this.marked = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle[i].length; j++) {
                if (String.valueOf(board.getLetter(i, j)).equals("Q"))
                    boggle[i][j] = "QU";
                else
                    boggle[i][j] = String.valueOf(board.getLetter(i, j));

            }
        }
        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle[i].length; j++) {
                dfs(boggle, i, j);
            }
        }
        return words;
    }

    private void dfs(String[][] boggle, int i, int j) {
        marked[i][j] = true;
        bob.append(boggle[i][j]);
        if (!outOfBoundCheck(i - 1, j - 1)) {
            if (trie.keysWithPrefix(bob.append(boggle[i - 1][j - 1]))) {
                if (boggle[i - 1][j - 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i - 1, j - 1);
            }
            else {
                if (boggle[i - 1][j - 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i - 1, j)) {
            if (trie.keysWithPrefix(bob.append(boggle[i - 1][j]))) {
                if (boggle[i - 1][j].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i - 1, j);
            }
            else {
                if (boggle[i - 1][j].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i - 1, j + 1)) {
            if (trie.keysWithPrefix(bob.append(boggle[i - 1][j + 1]))) {
                if (boggle[i - 1][j + 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i - 1, j + 1);
            }
            else {
                if (boggle[i - 1][j + 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i, j - 1)) {
            if (trie.keysWithPrefix(bob.append(boggle[i][j - 1]))) {
                if (boggle[i][j - 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i, j - 1);
            }
            else {
                if (boggle[i][j - 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i, j + 1)) {
            if (trie.keysWithPrefix(bob.append(boggle[i][j + 1]))) {
                if (boggle[i][j + 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i, j + 1);
            }
            else {
                if (boggle[i][j + 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i + 1, j - 1)) {
            if (trie.keysWithPrefix(bob.append(boggle[i + 1][j - 1]))) {
                if (boggle[i + 1][j - 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i + 1, j - 1);
            }
            else {
                if (boggle[i + 1][j - 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i + 1, j)) {
            if (trie.keysWithPrefix(bob.append(boggle[i + 1][j]))) {
                if (boggle[i + 1][j].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i + 1, j);
            }
            else {
                if (boggle[i + 1][j].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (!outOfBoundCheck(i + 1, j + 1)) {
            if (trie.keysWithPrefix(bob.append(boggle[i + 1][j + 1]))) {
                if (boggle[i + 1][j + 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
                dfs(boggle, i + 1, j + 1);
            }
            else {
                if (boggle[i + 1][j + 1].equals("QU"))
                    bob.setLength(bob.length() - 2);
                else
                    bob.setLength(bob.length() - 1);
            }
        }
        if (trie.contains(bob) && bob.length() > 2) {
            words.add(bob.toString());
        }
        if (bob.length() > 0) {
            if (boggle[i][j].equals("QU")) {
                bob.setLength(bob.length() - 2);
            }
            else
                bob.setLength(bob.length() - 1);
        }
        marked[i][j] = false;

    }

    private boolean outOfBoundCheck(int i, int j) {
        return i >= marked.length || j >= marked[0].length || i < 0 || j < 0 || marked[i][j];
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (!trie.contains(new StringBuilder(word)) || word.length() < 3)
            return 0;
        else if (word.length() == 3 || word.length() == 4)
            return 1;
        else if ((word.length() == 5))
            return 2;
        else if ((word.length() == 6))
            return 3;
        else if ((word.length() == 7))
            return 5;
        else
            return 11;

    }

    public static void main(String[] args) {
/*
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);


        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);


 */
/*

        int k = 0;
        for (int j = 0; j < 11; j++) {
            int i = 0;
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1000) {
                BoggleBoard board = new BoggleBoard();
                solver.getAllValidWords(board);
                i++;
            }
            k = i + k;
        }
        System.out.println(k / 10);



 */
    }

}
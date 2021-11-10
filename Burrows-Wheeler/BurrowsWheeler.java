/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output

    public static void transform() {
        String a = BinaryStdIn.readString();
        CircularSuffixArray cs = new CircularSuffixArray(a);
        char[] next = new char[cs.length()];
        int first = 0;
        for (int i = 0; i < cs.length(); i++) {
            if (cs.index(i) == 0) {
                next[i] = a.charAt(a.length() - 1);
                first = i;
            }
            else {
                next[i] = a.charAt(cs.index(i) - 1);
            }
        }

        BinaryStdOut.write(first);
        for (int i = 0; i < next.length; i++) {
            BinaryStdOut.write(next[i]);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        //StringBuilder bob = new StringBuilder();
        int first = BinaryStdIn.readInt();
        String b = BinaryStdIn.readString();
        int[][] org = new int[b.length()][2];
        for (int i = 0; i < b.length(); i++) {
            org[i][0] = b.charAt(i);
            org[i][1] = i;
        }
        countingSort(org);
        StringBuilder bob = new StringBuilder();
        for (int i = 0; i < org.length; i++) {
            bob.append((char) org[first][0]);
            first = (int) org[first][1];
        }
        BinaryStdOut.write(bob.toString());
        BinaryStdOut.close();
        BinaryStdIn.close();

    }

    private static void countingSort(int[][] a) {
        int N = a.length;
        int[][] aux = new int[N][2];
        int[] count = new int[256 + 1];
        // Compute frequency counts.
        for (int i = 0; i < N; i++)
            count[a[i][0] + 1]++;
        // Transform counts to indices.
        for (int r = 0; r < 256; r++)
            count[r + 1] += count[r];
        // Distribute the records.
        for (int i = 0; i < N; i++) {
            aux[count[a[i][0]]][0] = a[i][0];
            aux[count[a[i][0]]++][1] = a[i][1];
        }
        // Copy back.
        // System.out.println((int) aux[0][1]);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < 2; j++) {
                a[i][j] = aux[i][j];
            }
    }


    private static void countingSort(char[] a) {
        int N = a.length;
        char[] aux = new char[N];
        int[] count = new int[256 + 1];
        // Compute frequency counts.
        for (int i = 0; i < N; i++)
            count[a[i] + 1]++;
        // Transform counts to indices.
        for (int r = 0; r < 256; r++)
            count[r + 1] += count[r];
        // Distribute the records.
        for (int i = 0; i < N; i++)
            aux[count[a[i]]++] = a[i];
        // Copy back.
        for (int i = 0; i < N; i++)
            a[i] = aux[i];
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        // transform();
        //   inverseTransform();

        if (args[0].equals("+"))
            inverseTransform();
        else if (args[0].equals("-")) {
            transform();

        }

    }
}
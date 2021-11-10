/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class CircularSuffixArray {

    //private final CircularSuffix[] circularSuffix;
    //  private String[] cs;
    private final int[] index;
    private String str;
/*
    private static class CircularSuffix {
        private final String string;
        private final int index;

        private CircularSuffix(String s, int i) {
            this.string = s;
            this.index = i;
        }
    }

 */

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException();
        this.str = s;
        this.index = new int[str.length()];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        //  StringBuilder bob = new StringBuilder();
        /*
        this.circularSuffix = new CircularSuffix[s.length()];

        for (int i = 0; i < circularSuffix.length; i++) {
            circularSuffix[i] = new CircularSuffix(s, i);
        }

         */

/*
        for (CircularSuffix suffix : circularSuffix) {
            System.out.println(s.charAt(suffix.index) + " " + suffix.index);
        }
 */
        lsd();
/*
        for (CircularSuffix suffix : circularSuffix) {
            System.out.println(s.charAt(suffix.index) + " " + suffix.index);
        }

 */
    }

    private void lsd() {
        int n = str.length();
        int R = 256;   // extend ASCII alphabet size
        int[] aux = new int[str.length()];
        for (int d = n - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                if (index[i] + d > n - 1) {
                    count[str.charAt((index[i] + d) - n) + 1]++;
                }
                else
                    count[str.charAt(index[i] + d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                if (index[i] + d > n - 1) {
                    aux[count[str.charAt((index[i] + d) - n)]++] = index[i];
                }
                else {
                    aux[count[str.charAt(index[i] + d)]++] = index[i];
                }

            // copy back
            for (int i = 0; i < n; i++)
                index[i] = aux[i];
        }
    }


/*
    private void lsd() {
        int n = circularSuffix.length;
        int R = 256;   // extend ASCII alphabet size
        CircularSuffix[] aux = new CircularSuffix[n];

        for (int d = n - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                if (circularSuffix[i].index + d > circularSuffix.length - 1) {
                    count[circularSuffix[i].string.charAt((circularSuffix[i].index + d) - circularSuffix.length) + 1]++;
                }
                else
                    count[circularSuffix[i].string.charAt(circularSuffix[i].index + d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // move data
            for (int i = 0; i < n; i++)
                if (circularSuffix[i].index + d > circularSuffix.length - 1) {
                    //System.out.println(circularSuffix[i].string.charAt((circularSuffix[i].index + d) - circularSuffix.length));
                    aux[count[circularSuffix[i].string.charAt((circularSuffix[i].index + d) - circularSuffix.length)]++] = circularSuffix[i];
                }
                else {
                    // System.out.println(circularSuffix[i].string.charAt(circularSuffix[i].index + d) + " A");
                    aux[count[circularSuffix[i].string.charAt(circularSuffix[i].index + d)]++] = circularSuffix[i];
                }

            // copy back
            for (int i = 0; i < n; i++)
                circularSuffix[i] = aux[i];
        }
    }


 */

    // length of s
    public int length() {
        return index.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i > str.length() - 1)
            throw new IllegalArgumentException();
        return index[i];
    }

    public static void main(String[] args) {
        //String s = BinaryStdIn.readString();
        // CircularSuffixArray cs = new CircularSuffixArray(s);
    }
}

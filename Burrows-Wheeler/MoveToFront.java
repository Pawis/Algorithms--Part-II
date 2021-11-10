/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        ArrayList<Character> asci = new ArrayList<>();
        for (char i = 0; i < 256; i++) {
            asci.add(i);
        }
        while (!BinaryStdIn.isEmpty()) {
            char a = BinaryStdIn.readChar();
            char v = (char) asci.indexOf(a);
            BinaryStdOut.write(v);
            asci.remove(asci.indexOf(a));
            asci.add(0, a);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        ArrayList<Character> asci = new ArrayList<>();
        for (char i = 0; i < 256; i++) {
            asci.add(i);
        }
        while (!BinaryStdIn.isEmpty()) {
            char a = BinaryStdIn.readChar();
            BinaryStdOut.write(asci.get(a));
            char v = asci.get(a);
            asci.remove(a);
            asci.add(0, v);
        }
        BinaryStdOut.close();
        BinaryStdIn.close();

    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        //  encode();
        // decode();

        if (args[0].equals("+"))
            decode();
        else if (args[0].equals("-")) {
            encode();
        }


    }
}
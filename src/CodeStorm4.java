
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
public class CodeStorm4 {
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) {

        FasterScanner in = new FasterScanner();
        
        long[] preprocessesInput = new long[33];
        preprocessesInput[0] = 1;
        for (int i = 1; i < preprocessesInput.length; i++) {
            preprocessesInput[i] = 4 * preprocessesInput[i - 1] - 2;
        }

        int test = in.nextInt();
        loop:
        while (test-- > 0) {
            long n = in.nextLong();
            function(preprocessesInput,n);
        }
        out.close();
    }

    static void function(long[] Preprocess, long Input) {
        int i;
        int j = -1;
        for (i = 0; i < 33; i++) {
            if (Preprocess[i] > Input) {
                break;
            }
        }
        i = i + j;
        long myAnswer = 0;
        Input -= Preprocess[i] - 1;
        if (Input < Preprocess[i]) {
            myAnswer += Input;
            out.println(myAnswer);
            return;
        }
        myAnswer += Preprocess[i];
        Input -= Preprocess[i];
        if (Input % 2 == 0) {
            myAnswer += (Input / 2) * 3;
        } else {
            myAnswer += (Input / 2) * 3 + 2;
        }
        out.println(myAnswer);
    }

    static class FasterScanner {

        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }

}

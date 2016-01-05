
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
public class AdHack1 {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int totalOdd = 0;
        int totalEven=0;
        int[] oddSpecial = new int[n / 2];
        int[] evenSpecial = new int[n / 2];
        boolean[] isFilled = new boolean[n + 1];
        int[] indexSpecialarrays = new int[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            if (a[i] % 2 == 0) {
                evenSpecial[totalEven++] = a[i];
            } else {
                oddSpecial[totalOdd++] = a[i];
            }
        }
        Arrays.sort(evenSpecial);
        Arrays.sort(oddSpecial);
        int[] b = new int[n + 1];
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();

        totalOdd = 0;
        totalEven = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                h.put(evenSpecial[totalEven++], i);
            } else {
                h.put(oddSpecial[totalOdd++], i);
            }
        }
        for (int i = 1; i <= n; i++) {
            indexSpecialarrays[i] = h.get(a[i]);
        }
        
        Arrays.fill(isFilled, true);
        
        for (int i = 1; i <= n; i++) {
            if (isFilled[i]) {
                isFilled[i] = false;
                int cycleIndex = i;
                int cycleLength = 1;
                while (isFilled[indexSpecialarrays[cycleIndex]]) {
                    cycleLength++;
                    cycleIndex = indexSpecialarrays[cycleIndex];
                    isFilled[cycleIndex] = false;
                }
                ans += cycleLength-1;
            }
        }
        System.out.println(ans);
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

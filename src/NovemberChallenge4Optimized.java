
import java.io.IOException;
import java.util.Arrays;
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

class NovemberChallenge4Optimized {

    static final int couponTypes = 50000;

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            long[] f = new long[couponTypes + 1];
            while (n-- > 0) {
                f[in.nextInt()]++;
            }
            int[] a=new int[couponTypes+1];
            for(int i=0;i<=couponTypes;i++){
                a[i]=i;
            }
            Offer[] o=new Offer[m];
            for(int i=0;i<m;i++){
                int t=in.nextInt();
                int x=in.nextInt();
                int y=in.nextInt();
                o[i]=new Offer(t, x, y);
            }
            Arrays.sort(o);
            for(int i=0;i<m;i++){
                Offer cur=o[i];
                if(a[cur.a]<a[cur.b]){
                    a[cur.a]=a[cur.b];
                }
                else{
                    a[cur.b]=a[cur.a];
                }
            }
           
            long ans=0;
            for(int i=1;i<=couponTypes;i++){
                ans+=f[i]*a[i];
            }
            System.out.println(ans);
        }
    }

    static class Offer implements Comparable<Offer> {

        int t;
        int a;
        int b;

        public Offer(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Offer o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return o.t - this.t;
        }
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

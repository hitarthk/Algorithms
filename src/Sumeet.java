
import java.io.IOException;
import java.io.PrintWriter;
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
public class Sumeet {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int Q=in.nextInt();
        a=new int[n];
        a=in.nextIntArray(n);
        cnt=new int[200011];
        answer=new int[200011]; 
        PrintWriter out=new PrintWriter(System.out);
        Query[] q=new Query[Q];
        ans=new int[Q];
        for(int i=0;i<Q;i++){
            int l=in.nextInt()-1;
            int r=in.nextInt()-1;
            q[i]=new Query(i, l, r);
        }
        Arrays.sort(q);
        solve(q,Q);
        for(int i=0;i<Q;i++){
            out.println(ans[i]);
        }
        out.close();
    }
    
    
    static int[] a;
    static int[] cnt;
    static int[] answer;
    static int[] ans;

    public static void solve(Query[] q, int m) {
        int currentL = 0, currentR = 0;
        for (int i = 0; i < m; i++) {
            int L = q[i].L, R = q[i].R;
            while (currentL < L) {
                remove(currentL);
                currentL++;
            }
            while (currentL > L) {
                add(currentL - 1);
                currentL--;
            }
            while (currentR <= R) {
                add(currentR);
                currentR++;
            }
            while (currentR > R + 1) {
                remove(currentR - 1);
                currentR--;
            }
            int s=(int)Math.ceil((R-L+1.0)/3.0);
            ans[q[i].id] = answer[s];
        }

    }

    static class Query implements Comparable<Query> {

        int id;
        int L;
        int R;

        public Query(int id, int l, int r) {
            this.id = id;
            this.L = l;
            this.R = r;
        }

        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int tb = this.L / 320;
            int ob = o.L / 320;
            if (tb == ob) {
                return this.R - o.R;
            } else {
                return tb - ob;
            }
        }
    }

    static void add(int position) {
        cnt[a[position]]++;
        answer[cnt[a[position]]]++;
    }

    static void remove(int position) {
        answer[cnt[a[position]]]--;
        cnt[a[position]]--;
        
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

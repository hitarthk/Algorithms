
import java.io.IOException;
import java.io.PrintWriter;
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
public class Queries {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int m=in.nextInt();
        PrintWriter out=new PrintWriter(System.out);
        long[][] a=new long[m][2*n];
        for(int i=0;i<n;i++){
            long x=in.nextLong();
            //System.out.println("here "+x%m);
            a[(int)(x%m)][n+i]=x;
        }
        
        SegTree[] tree=new SegTree[m];
        for(int i=0;i<m;i++){
            tree[i]=new SegTree(n);
            tree[i].t=a[i];
            tree[i].build();
        }
        
        int q=in.nextInt();
        while(q>0){
            q--;
            String s=in.nextLine();
            if(s.charAt(0)=='+'){
                String[] spl=s.split(" ");
                int p=Integer.parseInt(spl[1])-1;
                long r=Long.parseLong(spl[2]);
                long value=0;
                int indm=0;
                for(int i=0;i<m;i++){
                    if(a[i][p+n]>0){
                        indm=i;
                        value=a[i][p+n];
                        break;
                    }
                }
                tree[(int)(value%m)].modify(p, 0);
                tree[(int)((value+r)%m)].modify(p, value+r);
                out.println(value+r);
            }
            if(s.charAt(0)=='-'){
                String[] spl=s.split(" ");
                int p=Integer.parseInt(spl[1])-1;
                long r=Long.parseLong(spl[2]);
                long value=0;
                int indm=0;
                for(int i=0;i<m;i++){
                    if(a[i][p+n]>0){
                        indm=i;
                        value=a[i][p+n];
                        break;
                    }
                }
                if(value>=r){
                    tree[(int)(value%m)].modify(p, 0);
                    tree[(int)((value-r)%m)].modify(p, value-r);
                    out.println(value-r);
                }
                else{
                    out.println(value);
                }
            }
            if(s.charAt(0)=='s'){
                String[] spl=s.split(" ");
                int l=Integer.parseInt(spl[1])-1;
                int r=Integer.parseInt(spl[2]);
                int mod=Integer.parseInt(spl[3]);
                out.println(tree[mod].query(l, r));
            }
        }
        out.close();
    }

    static class SegTree {

        int n;
        long[] t;
        
        public SegTree(int n){
            this.n=n;
            t=new long[2*n];
        }
                

        void build() {  // build the tree

            for (int i = n - 1; i > 0; --i) {
                t[i] = (t[i << 1]+ t[i << 1 | 1]);
            }
        }

        void modify(int p, long value) {  // set value at position p
            for (t[p += n] = value; p > 1; p >>= 1) {
                t[p >> 1] = (t[p]+ t[p ^ 1]);
            }
        }

        long query(int l, int r) {  // sum on interval [l, r)
            long res = 0;

            for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) != 0) {
                    res = (res+ t[l++]);
                }
                if ((r & 1) != 0) {
                    res = (res +t[--r]);
                }
            }
            return res;
        }
//	
//	
//	
    }

    public static class FasterScanner {

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

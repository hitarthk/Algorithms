
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class MSC6 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int limit=100000;
        
        ArrayList[] places=new ArrayList[limit+1];
        for(int i=0;i<=limit;i++){
            places[i]=new ArrayList<Integer>();
        }
        int n=in.nextInt();
        int Q=in.nextInt();
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            int x=in.nextInt();
            places[x].add(i);
            a[i]=x;
        }
        Query[] q=new Query[Q];
        int[] ans=new int[Q];
        for(int i=0;i<Q;i++){
            int l=in.nextInt();
            int k=in.nextInt();
            q[i]=new Query(i, l, k);
        }
        Arrays.sort(q);
        SegTree t=new SegTree(n+1);
        int relax=limit;
        for(int i=Q-1;i>=0;i--){
            while(relax>=q[i].l){
                for(int j=0;j<places[relax].size();j++){
                    t.modify((int)places[relax].get(j), 1);
                }
                relax--;
            }
            ans[q[i].id]=a[t.elementAt(q[i].k)];
        }
        for(int i=0;i<ans.length;i++){
            out.println(ans[i]);
        }
        out.close();
    }
    
    static class Query implements Comparable<Query>{
        int id;
        int l;
        int k;
        public Query(int id,int l,int k){
            this.id=id;
            this.l=l;
            this.k=k;
        }
        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.l!=o.l){
                return this.l-o.l;
            }
            if(this.k!=o.k)
                return this.k-o.k;
            return this.id-o.id;
        }
        
    }
    
    static class SegTree {

        int n;
        int[] t;
        
        public SegTree(int n){
            this.n=n;
            t=new int[2*n];
        }
            
        int elementAt(int rank){
            int l=0;
            int r=n-1;
            while(r-l>1){
                int m=(r+l)/2;
                if(query(0,m+1)>=rank){
                    r=m;
                }
                else{
                    l=m;
                }
            }
            return r;
        }

        void build() {  // build the tree

            for (int i = n - 1; i > 0; --i) {
                t[i] = (t[i << 1]+ t[i << 1 | 1]);
            }
        }

        void modify(int p, int value) {  // set value at position p
            for (t[p += n] = value; p > 1; p >>= 1) {
                t[p >> 1] = (t[p]+ t[p ^ 1]);
            }
        }

        long query(int l, int r) {  // sum on interval [l, r)
            int res = 0;

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


import java.io.IOException;
import java.io.PrintWriter;
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
class AMRITA3 {

    static HashMap<Long, Integer> h;
    static long[] present;
    static long[] ptree;
    static long[] atree;
    static long count[];
    static long[] actual;
    static long[] a;
    static int n;
    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();
        while (t-- > 0) {
             n = in.nextInt();
             a = new long[n];
             h=new HashMap<>();
            long[] temp = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
                temp[i] = a[i];
            }
            Arrays.sort(temp);
            int total = 0;
            for (int i = 0; i < n; i++) {
                h.put(temp[i], total);
                total++;
                int j;
                for (j = i; j < n; j++) {
                    if (temp[j] != temp[i]) {
                        break;
                    }
                }
                i = j - 1;
            }
            //System.out.println(h);
//            for(int i=0;i<n;i++){
//                System.out.println(h.get(a[i]));
//            }
            //System.out.println("||||");
            actual = new long[n];
            present = new long[n];
            count = new long[n];
            atree=new long[4*n];
            ptree=new long[4*n];
            answer=0;
            int m = in.nextInt();
            long[] ans = new long[m];
            Query[] q = new Query[m];
            for (int i = 0; i < m; i++) {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                q[i] = new Query(i, l, r);
            }
            Arrays.sort(q);

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
                ans[q[i].id] = answer;
            }
            
            
            for(int i=0;i<m;i++){
                out.println(ans[i]);
            }
            
        }
        out.close();
    }
    
    
    static void add(int position){
        int ppos=h.get(a[position]);
        
        if(count[ppos]==0){
            long endsum=query(1, 0, n-1, ppos, n-1, atree, actual);
            answer+=endsum;
            long rank=query(1, 0, n-1, 0, ppos, ptree, present);
            answer+=(rank+1)*a[position];
            present[ppos]=1;
            update(1, 0, n-1, ppos, ptree, present);
            actual[ppos]=a[position];
            update(1, 0, n-1, ppos, atree, actual);
            
        }
        count[ppos]++;
    }
    
    static void remove(int position){
        int ppos=h.get(a[position]);
        if(count[ppos]==1){
            present[ppos]=0;
            update(1, 0, n-1, ppos, ptree, present);
            actual[ppos]=0;
            update(1, 0, n-1, ppos, atree, actual);
            long endsum=query(1, 0, n-1, ppos, n-1, atree, actual);
            answer-=endsum;
            long rank=query(1, 0, n-1, 0, ppos, ptree, present);
            answer-=(rank+1)*a[position];
            
        }
        count[ppos]--;
    }

    static long answer = 0;

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
            int tb = this.L /100;
            int ob = o.L / 100;
            if (tb == ob) {
                return this.R - o.R;
            } else {
                return tb - ob;
            }
            //return -1;
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

    static long mod = (long) 1e9 + 7;

    public static void build(int node, int s, int e, long[] tree, long[] a) {
        if (s == e) {
            tree[node] = a[s];
        } else {
            int mid = (s + e) >> 1;
            build(2 * node, s, mid, tree, a);
            build(2 * node + 1, mid + 1, e, tree, a);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

    }

    public static void update(int node, int s, int e, int p, long[] tree, long[] a) {
        if (s == e) {
            tree[node]=a[p];
            return;
        }
        int mid = (s + e) >> 1;
        if (p <= mid) {
            update(2 * node, s, mid, p, tree, a);
        } else {
            update(2 * node + 1, mid + 1, e, p, tree, a);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public static long query(int node, int s, int e, int l, int r, long[] tree, long[] a) {
        if (s == l && e == r) {
            return tree[node];
        }
        int mid = (s + e) >> 1;
        if (r <= mid) {
            //System.out.println(l+" "+r);
            return query(2 * node, s, mid, l, r, tree, a);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, e, l, r, tree, a);
        }
        return merge(query(2 * node, s, mid, l, mid, tree, a), query(2 * node + 1, mid + 1, e, mid + 1, r, tree, a));
    }

    public static long merge(long l, long r) {
        return l + r;
    }

}

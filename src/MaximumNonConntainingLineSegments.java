
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
public class MaximumNonConntainingLineSegments {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] array = new int[2 * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            array[index++] = a[i];
            array[index++] = b[i];
        }
        Arrays.sort(array);
        HashMap<Integer, Integer> h = new HashMap<>();
        index = 0;
        for (int i = 0; i < 2 * n;) {
            h.put(array[i], index);
            index++;
            int j;
            for (j = i; j < 2 * n; j++) {
                if (array[i] != array[j]) {
                    break;
                };
            }
            i = j;
        }
        Pair[] p = new Pair[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Pair();
            p[i].ml = h.get(a[i]);
            p[i].mr = h.get(b[i]);
        }
        Arrays.sort(p);
        //System.out.println(Arrays.toString(p));
        boolean[] f = new boolean[n];
        long[] right = new long[2 * n];
        Arrays.fill(right, Integer.MAX_VALUE);
        long[] tree = new long[8 * n];
        int maxright = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                f[i] = true;
                right[p[i].ml] = Math.min(p[i].mr,right[p[i].ml]);
                maxright = p[i].mr;
                count++;
            } else {
                if (p[i].ml >= maxright) {
                    f[i] = true;
                    right[p[i].ml] = Math.min(p[i].mr,right[p[i].ml]);
                    maxright = p[i].mr;
                    count++;
                }
            }
        }
        //System.out.println(count);
        build(1, 0, 2 * n - 1, tree, right);

        for (int i = 0; i < n; i++) {
            //System.out.println("YES");
            if (!f[i]) {
                int q = (int) query(1, 0, 2 * n - 1, p[i].ml, p[i].mr, tree, right);
                //System.out.println(i+" "+q);
                if (p[i].mr < q) {
                    count++;
                    right[p[i].ml]=Math.min(right[p[i].ml], p[i].mr);
                    update(1, 0, 2*n-1, p[i].ml, tree, right);
                }
            }
        }
        System.out.println(count);
    }

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
            tree[node] = a[p];
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
        return Math.min(l, r);
    }

    static class Pair implements Comparable<Pair> {

        int id;
        int ml;
        int mr;

        @Override
        public int compareTo(Pair o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (this.mr == o.mr) {
                if (this.ml == o.ml) {
                    return this.id - o.id;
                } else {
                    return -this.ml + o.ml;
                }
            } else {
                return this.mr - o.mr;
            }
        }
        
        public String toString(){
            return this.ml+" "+this.mr;
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

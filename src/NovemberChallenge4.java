
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
class NovemberChallenge4 {

    static Vertex[] v;
    static long[] f;
    static final int coupnTypes = 50000;

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            f = new long[coupnTypes + 1];
            for (int i = 0; i < n; i++) {
                f[in.nextInt()]++;
            }
            v = new Vertex[coupnTypes + 1];
            for (int i = 0; i <= coupnTypes; i++) {
                v[i] = new Vertex(i);
            }
            Offer[] o = new Offer[m];
            for (int i = 0; i < m; i++) {
                int t = in.nextInt();
                int a = in.nextInt();
                int b = in.nextInt();
                o[i] = new Offer(t, a, b);
            }
            Arrays.sort(o);
            for (int i = 0; i < m; i++) {
                int a = o[i].a;
                int b = o[i].b;
                int t = o[i].t;
                v[a].addEdge(b, t);
                v[b].addEdge(a, t);
            }

//            for(int i=0;i<=coupnTypes;i++){
//                System.out.println(i+" "+v[i]);
//            }
            for (int i = 0; i <= coupnTypes; i++) {
                v[i].tree = new int[4 * v[i].distinctTime.size()];
                v[i].a = new int[v[i].distinctTime.size()];
                for (int j = 0; j < v[i].distinctTime.size(); j++) {
                    v[i].a[j] = i;
                }
                if (v[i].distinctTime.size() > 0) {
                    build(1, 0, v[i].distinctTime.size() - 1, v[i].tree, v[i].a);
                }
            }
            dfs();
            //System.out.println("|||||||");
//            for(int i=0;i<=coupnTypes;i++){
//                System.out.println(Arrays.toString(v[i].a)+" "+Arrays.toString(v[i].tree));
//            }
            long ans = 0;
            for (int i = 1; i <= coupnTypes; i++) {
                if (v[i].distinctTime.size() > 0) {
                    ans += v[i].tree[1] * f[i];
                    //System.out.println("Type "+i+" Max answer "+v[i].tree[1]);
                } else {
                    ans += f[i] * i;
                    //System.out.println("Type "+i+" "+i);
                }
            }
            System.out.println(ans);
        }
    }

    static void dfs() {
        for (int i = coupnTypes; i >= 1; i--) {
            if (!v[i].visited) {
                visit(i);
            } else {
                if (v[i].parent != 0) {
                    int parentT = v[i].parentEdget;
                    int indexP = v[v[i].parent].searchIndex(parentT);
                    int ans = query(1, 0, v[v[i].parent].distinctTime.size() - 1, indexP, v[v[i].parent].distinctTime.size() - 1, v[v[i].parent].tree, v[v[i].parent].a);
                    int indexC = v[i].searchIndex(parentT);
                    if (ans > v[i].a[indexC]) {
                        v[i].a[indexC] = ans;
                        update(1, 0, v[i].distinctTime.size() - 1, indexC, v[i].tree, v[i].a);
                    }
                }
            }
        }
    }

    static void visit(int u) {
        v[u].visited = true;
        for (int i = 0; i < v[u].list.size(); i++) {
            int other = v[u].list.get(i).other(u);
            if (!v[other].visited) {
                v[other].parent=u;
                v[other].parentEdget=v[u].list.get(i).t;
                visit(other);
            }
            int otherIndex = v[other].searchIndex(v[u].list.get(i).t);
            int curIndex = v[u].searchIndex(v[u].list.get(i).t);
            int ans=query(1, 0, v[other].distinctTime.size()-1, otherIndex, v[other].distinctTime.size()-1, v[other].tree, v[other].a);
            if (ans > v[u].a[curIndex]) {
                v[u].a[curIndex] = ans;
                update(1, 0, v[u].distinctTime.size() - 1, curIndex, v[u].tree, v[u].a);
            }
        }
    }

    static class Vertex {

        int id;
        ArrayList<Edge> list;
        ArrayList<Integer> distinctTime;
        int[] tree;
        int[] a;
        int parent;
        int parentEdget;
        boolean visited;
        int maxId;

        public Vertex(int id) {
            this.id = id;
            list = new ArrayList<>();
            parent = 0;
            visited = false;
            parentEdget = 0;
            maxId = id;
            distinctTime = new ArrayList<>();
        }

        public int searchIndex(int x) {
            if (x <= distinctTime.get(0)) {
                return 0;
            }
            if (x > distinctTime.get(distinctTime.size() - 1)) {
                return -1;
            }
            int l = 0;
            int r = distinctTime.size() - 1;
            while (r - l > 1) {
                int mid = (l + r) >> 1;
                if (x <= distinctTime.get(mid)) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            return r;
        }

        public void addEdge(int v, int t) {
            if (distinctTime.isEmpty() || distinctTime.get(distinctTime.size() - 1) != t) {
                distinctTime.add(t);
            }
            list.add(new Edge(t, id, v));
        }

        public String toString() {
            return list.toString() + " " + distinctTime.toString();
        }

    }

    public static void build(int node, int s, int e, int[] tree, int[] a) {
        if (s == e) {
            tree[node] = a[s];
        } else {
            int mid = (s + e) >> 1;
            build(2 * node, s, mid, tree, a);
            build(2 * node + 1, mid + 1, e, tree, a);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

    }

    public static void update(int node, int s, int e, int p, int[] tree, int[] a) {
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

    public static int query(int node, int s, int e, int l, int r, int[] tree, int[] a) {
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

    public static int merge(int l, int r) {
        return Math.max(l, r);
    }

    static class Edge {

        int u, v, t;

        public Edge(int t, int u, int v) {
            this.t = t;
            this.u = u;
            this.v = v;
        }

        public int other(int u) {
            return this.u == u ? v : this.u;
        }

        public String toString() {
            return u + " " + v + " " + t;
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
            return this.t - o.t;
        }
    }

    /*
    1
1 5
5
1 1 2
1 2 3
1 3 4
1 4 5
1 1 6*/
    
    
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

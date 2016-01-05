
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
public class c_275_d {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int[] a=new int[n];
        RangeSum tree=new RangeSum(n, a);
        int m = in.nextInt();
        while (m-- > 0) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int q = in.nextInt();
            StringBuilder up = new StringBuilder(Integer.toBinaryString(q));
            int len=up.length();
            for (int i = 0; i < 31 - len; i++) {
                up.insert(0,'0');
            }
            
            
            //System.out.println(up);
            for (int i = 0; i < 31; i++) {
                if (up.charAt(i) == '0') {
                    int ans = tree.queryRange(1, 0, n - 1, l, r);
                    if((ans & 1<<(30-i))>0) {
                            System.out.println("NO");
                        return;
                    }
                } else {
                    tree.updateRange(1, 0, n - 1, l, r, 1<<(30-i));
                }
            }

        }
        
//            for(int i=0;i<31;i++){
//                System.out.println(Arrays.toString(tree[i].A));
//            }
        System.out.println("YES");
        StringBuilder ans=new StringBuilder();
        for (int i = 0; i < n; i++) {
            ans.append(tree.queryRange(1, 0, n-1, i, i)+" ");
        }
        out.println(ans);
        out.close();
    }

    static class RangeSum {

        int[] A;
        int[] tree;
        int size;
        int sizea;
        int[] lazy;

        public RangeSum(int n, int[] a) {
            this.A = a;
            tree = new int[(1<<2) * n];
            lazy = new int[(1<<2) * n];
        }

        void build(int node, int start, int end) {
            if (start == end) {
                // Leaf node will have a single element
                tree[node] = A[start];
            } else {
                int mid = (start + end) >>1;
                // Recurse on the left child
                build((1<<1)* node, start, mid);
                // Recurse on the right child
                build((1<<1) * node + 1, mid + 1, end);
                // Internal node will have the sum of both of its children
                //tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }

        void updateRange(int node, int start, int end, int l, int r, int val) {
            if (lazy[node] != 0) {
                // This node needs to be updated
                tree[node] |= lazy[node];    // Update it
                if (start != end) {
                    lazy[node * 2] |= lazy[node];                  // Mark child as lazy
                    lazy[node * 2 + 1] |= lazy[node];                // Mark child as lazy
                } else {
                    A[start] = tree[node];
                }
                lazy[node] = 0;                                  // Reset it
            }
            if (start > end || start > r || end < l) // Current segment is not within range [l, r]
            {
                return;
            }
            if (start >= l && end <= r) {
                // Segment is fully within range
                tree[node] |= val;
                if (start != end) {
                    // Not leaf node
                    lazy[node * 2] |= val;
                    lazy[node * 2 + 1] |= val;
                } else {
                    A[start] = tree[node];
                }
                return;
            }
            int mid = (start + end) >>1;
            updateRange(node * 2, start, mid, l, r, val);        // Updating left child
            updateRange(node * 2 + 1, mid + 1, end, l, r, val);   // Updating right child
            tree[node] = tree[node * 2] & tree[node * 2 + 1];        // Updating root with max value 
        }
        int identity=0x7fffffff;
        int queryRange(int node, int start, int end, int l, int r) {
            if (start > end || start > r || end < l) {
                return identity;         // Out of range
            }
            if (lazy[node] != 0) {
                // This node needs to be updated
                tree[node] |= lazy[node];            // Update it
                if (start != end) {
                    lazy[node * 2] |= lazy[node];         // Mark child as lazy
                    lazy[node * 2 + 1] |= lazy[node];    // Mark child as lazy
                } else {
                    A[start] = tree[node];
                }
                lazy[node] = 0;                 // Reset it
            }
            if (start >= l && end <= r) // Current segment is totally within range [l, r]
            {
                return tree[node];
            }
            int mid = (start + end) >>1;
            int p1 = queryRange(node * 2, start, mid, l, r);         // Query left child
            int p2 = queryRange(node * 2 + 1, mid + 1, end, l, r); // Query right child
            return (p1 & p2);
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

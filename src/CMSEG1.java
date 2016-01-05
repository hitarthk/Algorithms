
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
public class CMSEG1 {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        String s=in.nextLine();
        int[] a=new int[n];
        for(int i=0;i<s.length();i++){
            a[i]=s.charAt(i)-48;
        }
        RangeSum t=new RangeSum(n, a);
        int q=in.nextInt();
        //in.nextLine();
        while(q>0){
            q--;
            String str=in.nextLine();
            //System.out.println(str);
            if(str.charAt(0)=='0'){
                String[] sa=str.split(" ");
                int l=Integer.parseInt(sa[1]);
                int r=Integer.parseInt(sa[2]);
                out.println(t.query(1, 0, n-1, l, r));
            }
            if(str.charAt(0)=='1'){
                String[] sa=str.split(" ");
                //System.out.println(Arrays.toString(sa));
                int l=Integer.parseInt(sa[1]);
                t.update(1, 0, n-1, l, 1);
            }
        }
        out.close();
    }

    public static class RangeSum {

        int[] A;
        int[] tree;
        int size;
        int sizea;
        int[] lazy;

        int pow(int b,int e){
            if(e==0){
                return 1;
            }
            else{
                int ans=pow(b, e/2);
                int fans=(ans*ans)%3;
                return e%2==0?fans:(fans*b)%3;
            }
        }
        
        public RangeSum(int n, int[] a) {
            this.A = a;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            build(1,0,n-1);
        }

        void build(int node, int start, int end) {
            if (start == end) {
                // Leaf node will have a single element
                tree[node] = A[start];
            } else {
                int mid = (start + end) / 2;
                // Recurse on the left child
                build(2 * node, start, mid);
                // Recurse on the right child
                build(2 * node + 1, mid + 1, end);
                // Internal node will ha    ve the sum of both of its children
                tree[node] = ((tree[2 * node]*pow(2,end-mid))%3 + tree[2 * node + 1])%3;
            }
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                // Leaf node
                if(A[idx]==0){
                A[idx] = val;
                tree[node] = (val);
                }
            } else {
                int mid = (start + end) / 2;
                if (start <= idx && idx <= mid) {
                    // If idx is in the left child, recurse on the left child
                    update(2 * node, start, mid, idx, val);
                } else {
                    // if idx is in the right child, recurse on the right child
                    update(2 * node + 1, mid + 1, end, idx, val);
                }
                // Internal node will have the sum of both of its children
                tree[node] = ((pow(2,end-mid)*tree[2 * node])%3 + tree[2 * node + 1])%3;
            }
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                // range represented by a node is completely outside the given range
                return 0;
            }
            if (l <= start && end <= r) {
                // range represented by a node is completely inside the given range
                return tree[node];
            }
            // range represented by a node is partially inside and partially outside the given range
            int mid = (start + end) / 2;
            int p1 = query(2 * node, start, mid, l, r);
            int p2 = query(2 * node + 1, mid + 1, end, l, r);
            if(r< mid+1 || end< l)
            return (p1)%3;
                return ((pow(2,Math.min(r, end)-mid)*p1)%3 + p2)%3;
        }
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

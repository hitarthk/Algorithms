
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class MSC3 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        int Q=in.nextInt();
        int[] w=in.nextIntArray(n);
        UF uf=new UF(n, w);
        
        while(Q>0){
            Q--;
            int p=in.nextInt()-1;
            int q=in.nextInt()-1;
            uf.union(p, q);
            out.println(uf.giveMin());
        }
        out.close();
    }
    
    static class Node implements Comparable<Node>{
        int id;
        int w;
        public Node(int id,int w){
            this.id=id;
            this.w=w;
        }

        @Override
        public int compareTo(Node o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.w!=o.w){
                return this.w-o.w;
            }
            else{
                return this.id-o.id;
            }
        }
    }
    
    public static class UF {

        public int max = 1;
        private int[] size;
        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;     // number of components
        int[] h;
        TreeSet<Node> t;
        public UF(int N,int[] w) {
            if (N < 0) {
                throw new IllegalArgumentException();
            }
            count = N;
            parent = new int[N];
            rank = new byte[N];
            size = new int[N];
            h=new int[N];
            t=new TreeSet<>();
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
                h[i]=w[i];
                t.add(new Node(i,w[i]));
            }
            
        }

        public void print(int id) {
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == parent[id]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

        public int find(int p) {
            if (p < 0 || p >= parent.length) {
                return -1;
            }
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            if (p >= rank.length || q >= rank.length) {
                return false;
            }

            return find(p) == find(q);
        }

        public boolean union(int p, int q) {

            if (p >= rank.length || q >= rank.length) {
                return false;
            }
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return false;
            }

            // make root of smaller rank point to root of larger rank
            t.remove(new Node(rootQ,h[rootQ]));
            t.remove(new Node(rootP, h[rootP]));
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
                h[rootQ]+=h[rootP];
                t.add(new Node(rootQ, h[rootQ]));
                if (size[rootQ] > max) {
                    max = size[rootQ];
                }
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
                h[rootP]+=h[rootQ];
                t.add(new Node(rootP, h[rootP]));
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
                size[rootP] += size[rootQ];
                h[rootP]+=h[rootQ];
                t.add(new Node(rootP, h[rootP]));
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            }
            count--;
            return true;
        }
        
        public int giveMin(){
            return t.first().w;
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

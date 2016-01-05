
import java.io.IOException;
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
public class CHNB {
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            int[] a=in.nextIntArray(n);
            UF uf=new UF(n);
            for(int i=1;i<n;i++){
                
                if(a[i]!=-1)
                uf.union(i, a[i]);
            }
            double exp=0.0;
            int total=0;
            int count=uf.count;
            System.out.println(count);
            for(int i=1;i<n;i++){
                if(a[i]==-1){
                    total++;
                }
            }
            for(int i=0;i<=total;i++){
                exp += (ncr(total,i)/(Math.pow(2.0, total-i)))*(count-i);
            }
            System.out.printf("%.6f\n",exp);
        }
    }
    
    static double ncr(int n,int k){
        double ans=1;
        for(int i=0;i<k;i++){
            ans = (ans*(n-i))/(2.0*(i+1));
        }
        return ans;
    }
    
    static class UF {

    public int max = 1;
    private int[] size;
    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    public UF(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        count = N;
        parent = new int[N];
        rank = new byte[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
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
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            if (size[rootQ] > max) {
                max = size[rootQ];
            }
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            if (size[rootP] > max) {
                max = size[rootP];
            }
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
            size[rootP] += size[rootQ];
            if (size[rootP] > max) {
                max = size[rootP];
            }
        }
        count--;
        return true;
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

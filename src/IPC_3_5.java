
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
class IPC_3_5 {

    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n + 2];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            long[] prefix = new long[n + 2];
            for (int i = 1; i <= n; i++) {
                prefix[i] = prefix[i - 1] + a[i];
            }

            long[][] specialPrefix = new long[101][n + 2];
            for (int i = 1; i <= 100; i++) {
                for (int j = 1; j <= n; j++) {
                    specialPrefix[i][j] = specialPrefix[i][j - 1] + a[j] % i;
                }
            }
            int m=in.nextInt();
            Query[] q=new Query[m];
            for(int i=0;i<m;i++){
                int l=in.nextInt();
                int r=in.nextInt();
                int mod=in.nextInt();
                q[i]=new Query(i, l, r, mod);
            }
            
            ArrayList[] pos=new ArrayList[10001];
            for(int i=0;i<=10000;i++){
                pos[i]=new ArrayList<Integer>();
            }
            for(int i=1;i<=n;i++){
                pos[a[i]].add(i);
            }
            
            ArrayList[] specialQ=new ArrayList[10001];
            for(int i=0;i<=10000;i++){
                specialQ[i]=new ArrayList<SpecialQuery>();
            }
            
            for(int i=0;i<m;i++){
                if(q[i].mod>100){
                    for(int j=q[i].mod;j<=10000;j+=q[i].mod){
                        specialQ[j].add(new SpecialQuery(i, q[i].l, q[i].r));
                    }
                }
            }
            
            long[] f=new long[n+2];
            long[] tree=new long[4*(n+2)];
            
            for(int i=10000;i>=1;i--){
                for(Integer x:(ArrayList<Integer>)pos[i]){
                    f[x]++;
                    update(1, 1, n, x, tree, f);
                }
                for(SpecialQuery sq:(ArrayList<SpecialQuery>)specialQ[i]){
                    long sub=query(1, 1, n, sq.l, sq.r, tree, f);
                    q[sq.id].sub+=sub*q[sq.id].mod;
                }
            }
            
            for(int i=0;i<m;i++){
                long ans;
                if(q[i].mod>100){
                    ans=prefix[q[i].r]-prefix[q[i].l-1];
                    ans-=q[i].sub;
                }
                else{
                    ans=specialPrefix[q[i].mod][q[i].r]-specialPrefix[q[i].mod][q[i].l-1];
                }
                out.println(ans);
            }
            
        }
        out.close();;
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

    
    static class Query{
        int id;
        int l;
        int r;
        long sub;
        int mod;
        public Query(int id,int l,int r,int mod){
            this.id=id;
            this.l=l;
            this.r=r;
            this.mod=mod;
        }
    }
    
    static class SpecialQuery{
        int id;
        int l;
        int r;
        public SpecialQuery(int id,int l,int r){
            this.id=id;
            this.l=l;
            this.r=r;
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


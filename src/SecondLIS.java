
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
class SecondLIS {
    
    public static void main(String[] args) {
        
        FasterScanner in=new FasterScanner();
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            long[] input=new long[n];
            long[] temp=new long[n];
            
            for(int i=0;i<n;i++){
                input[i]=in.nextLong();
                temp[i]=input[i];
            }
            Arrays.sort(temp);
            HashMap<Long,Integer> h=new HashMap<>();
            int total = 1;
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
            long[][] tree=new long[4*100005][3];
            long[] a=new long[100005];
            long[] f=new long[100005];
            for(int i=0;i<n;i++){
                long x=input[i];
                f[h.get(x)]++;
                long[] q=query(1, 0, 100001, 0, h.get(x)-1, tree, a);
                //System.out.println(Arrays.toString(q));
                
                update(1, 0, 100001, h.get(x), q,tree, a,f);
            }
            System.out.println((tree[1][2]));
        }
    }
    
//    public static void build(int node, int s, int e, long[] tree, long[] a) {
//        if (s == e) {
//            tree[node] = a[s];
//        } else {
//            int mid = (s + e) >> 1;
//            build(2 * node, s, mid, tree, a);
//            build(2 * node + 1, mid + 1, e, tree, a);
//            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
//        }
//
//    }

    public static void update(int node, int s, int e, int p, long[] v, long[][] tree, long[] a,long[] f) {
        if (s == e) {

            if (v[0] > 0) {
                if (v[0] == 1) {
                    tree[node][0] = 2;
                    tree[node][1] += v[1];
                    tree[node][2] = f[p];
                } else {
                    tree[node][0] = v[0] + 1;
                    tree[node][1] += v[1];
                    tree[node][2] += v[2];
                }
                //tree[node][2]%=mod;
                tree[node][1]%=mod;
                tree[node][2]%=mod;
                a[p] = v[0] + 1;
            } else {
                tree[node][0]=1;
                tree[node][1]=f[p];
                tree[node][1]%=mod;
                tree[node][2]=0;
                a[p]=1;
            }
            return;
        }
        int mid = (s + e) >> 1;
        if (p <= mid) {
            update(2 * node, s, mid, p,v, tree, a,f);
        } else {
            update(2 * node + 1, mid + 1, e, p,v, tree, a,f);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public static long[] query(int node, int s, int e, int l, int r, long[][] tree, long[] a) {
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

    public static long[] merge(long[] l, long[] r) {
        long[] ans=new long[3];
        if(l[0]==r[0]){
            ans[0]=l[0];
            ans[1]=(l[1]+r[1])%mod;
            ans[2]=(l[2]+r[2])%mod;
            
        }
        else if(l[0]<r[0]){
            ans[0]=r[0];
            ans[1]=r[1];
            ans[2]=r[2];
            if(l[0]==r[0]-1)
                ans[2]=(ans[2]+l[1])%mod;
        }
        else{
            ans[0]=l[0];
            ans[1]=l[1];
            ans[2]=l[2];
            if(r[0]==l[0]-1 )
                ans[2]=(ans[2]+r[1])%mod;
        }
        return ans;
    }
    
    static long mod=(long)1e9+7;
    
    static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
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

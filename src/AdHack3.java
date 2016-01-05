
import java.io.IOException;
import java.io.PrintWriter;
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
public class AdHack3 {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        a=new String[n];
        tree=new long[4*n][2];
        for(int i=0;i<n;i++){
            a[i]=in.nextString();
        }
        build(1,0,n-1);
        PrintWriter out=new PrintWriter(System.out);
        int q=in.nextInt();
        while(q-->0){
            int t=in.nextInt();
            if(t==0){
                int l=in.nextInt()-1;
                int r=in.nextInt()-1;
                System.out.println(query(1, 0, n-1, l, r)[1]);
            }
            else{
                int p=in.nextInt()-1;
                String v=in.nextString();
                a[p]=v;
                update(1,0 , n-1, p);
            }
        }
        out.close();
        
    }
    
    static long[][] tree;
    static String[] a;
    static long mod=(long)1e9+7;
    public static void build(int node, int s, int e) {
        if (s == e) {
            tree[node][0] = a[s].length();
            tree[node][1]= Long.parseLong(a[s])%mod;
        } else {
            int mid = (s + e) >> 1;
            build(2 * node, s, mid);
            build(2 * node + 1, mid + 1, e);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

    }

    public static void update(int node, int s, int e, int p) {
        if (s == e) {
            tree[node][0]=a[p].length();
            tree[node][1]=Long.parseLong(a[p])%mod;
            return;
        }
        int mid = (s + e) >> 1;
        if (p <= mid) {
            update(2 * node, s, mid, p);
        } else {
            update(2 * node + 1, mid + 1, e, p);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public static long[] query(int node, int s, int e, int l, int r) {
        if (s == l && e == r) {
            return tree[node];
        }
        int mid = (s + e) >> 1;
        if (r <= mid) {
            //System.out.println(l+" "+r);
            return query(2 * node, s, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, e, l, r);
        }
        return merge(query(2 * node, s, mid, l, mid), query(2 * node + 1, mid + 1, e, mid + 1, r));
    }

    public static long[] merge(long[] l, long[] r) {
        long[] ans=new long[2];
        ans[0]=l[0]+r[0];
        ans[1]=((modPow(10,r[0],mod)*l[1])%mod+r[1])%mod;
        return ans;
    }
    
    
    static long modPow( long a, long b, long mod )
    {
    	long res=1,pow=a;
    	while( b>0 )
    	{
    		if( (b&1)==1 )
    		{
    			res = (pow*res)%mod;
    		}
    		pow = (pow*pow)%mod;
    		b=b/2;
    	}
    	return res;
    }
    
//    static long numDigits(long n){
//        long ans=0;
//        while(n>0){
//            return 
//        }
//    }
    
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


import java.io.IOException;
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
public class BIT2 {
    static long total;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        
        //b=new long[100001];
//        t[0]=new long[4*100001];
//        t[1]=new long[4*100001];
        int max=100002;
        t=new long[2][4*(max+1)];
        a=new long[2][max+1];
        //long ans=0;
     
        for(int i=0;i<n;i++){
            int x=in.nextInt();
            //System.out.println(x);
            //query(1, 0, max, x+1, max, 1);
            update(1, 0, max, x, query(1, 0, max, x+1, max, 1), 0);
            update(1, 0, max, x, query(1, 0, max, 0, x-1, 0), 1);
//            ans+=a[0][x];
//            ans+=a[1][x];
        }
        //System.out.println(total);
        //System.out.println(query(1, 0, max, 1, max, 0));
        //System.out.println(query(1, 0, max, 1, max, 1));
        //System.out.println(Arrays.toString(a[0]));
        //System.out.println(Arrays.toString(a[1]));
        long ans=((query(1, 0, max, 1, max, 0)+query(1, 0, max, 1, max, 1)-2*n)%mod+mod)%mod;
        System.out.println(ans);
        
    }
    
    static long[][] t;
    static long[][] a;
    public static void build(int node,int s,int e,int type){
        if(s==e){
            t[type][node]=1;
        }
        else{
            int mid=(s+e)>>1;
            build(2*node,s,mid,type);
            build(2*node+1, mid+1, e,type);
            t[type][node]=merge(t[type][2*node], t[type][2*node+1]);
        }
        
    }
    
    public static void update(int node,int s,int e,int p,long v,int type){
        if(s==e){
            
//                if(a[type][p]==0)
//                    total++;
                a[type][p] += (v+1)%mod;
                a[type][p] = a[type][p]%mod;
                t[type][node] = a[type][p];
                return;
            
        }
        int mid=(s+e)>>1;
        //System.out.println(mid+" "+s+" "+e);
        if(p<=mid)
        update(2*node, s, mid,p,v,type);
        else
        update(2*node+1, mid+1, e,p,v,type);
        t[type][node]=merge(t[type][2*node], t[type][2*node+1]);
    }
    
    public static long query(int node,int s,int e,int l,int r,int type){
        //System.out.println(s+" "+e);
        if(s==l && e==r){
            return t[type][node];
        }
        int mid=(s+e)>>1;
        if(r<=mid){
            return query(2*node, s, mid, l, r,type);
        }
        if(l>mid)
            return query(2*node+1, mid+1, e, l, r,type);
        return merge(query(2*node, s, mid, l, mid,type),query(2*node+1, mid+1, e, mid+1, r,type));
    }
    
    
    public static long merge(long l,long r){
        return (l+r)%mod;
    }
    
    static long mod=(long)1e9+7;
    
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


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
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
public class BIT1 {
    
    static int[] t;
    static int[] a;
    static HashSet<Integer> h=new HashSet<>();
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int q=in.nextInt();
        
        h.add(0);
        for(int i=0;i<26;i++){
            h.add(1<<i);
        }
        String s=in.nextLine();
        t=new int[4*n];
        a=new int[n];
        for(int i=0;i<s.length();i++){
            a[i]=s.charAt(i)-97;
        }
        build(1, 0, n-1);
        PrintWriter out=new PrintWriter(System.out);
        while(q>0){
            q--;
            int ty=in.nextInt();
            int l=in.nextInt();
            
            if(ty==2){
            int r=in.nextInt();
                if(h.contains(query(1, 0, n-1, l-1, r-1)))
                out.println("yes");
            else
                out.println("no");
            }
            else{
                int r=in.nextLine().charAt(0);
                a[l-1]=r-97;
                update(1, 0, n-1,l-1,r-97);
            }
        }
        out.close();
        
    }
    
    
    public static void build(int node,int s,int e){
        if(s==e){
            t[node]=1<<a[s];
        }
        else{
            int mid=(s+e)>>1;
            build(2*node,s,mid);
            build(2*node+1, mid+1, e);
            t[node]=merge(t[2*node], t[2*node+1]);
        }
        
    }
    
    public static void update(int node,int s,int e,int p,int c){
        if(s==e){
            t[node]=1<<c;
            return;
        }
        int mid=(s+e)>>1;
        if(p<=mid){
            update(2*node, s, mid, p,c);
        }
        else{
            update(2*node+1, mid+1, e, p,c);
        }
        t[node]=merge(t[2*node], t[2*node+1]);
    }
    
    public static int query(int node,int s,int e,int l,int r){
        if(s==l && e==r){
            return t[node];
        }
        int mid=(s+e)>>1;
        if(r<=mid){
            return query(2*node, s, mid, l, r);
        }
        if(l>mid)
            return query(2*node+1, mid+1, e, l, r);
        return merge(query(2*node, s, mid, l, mid),query(2*node+1, mid+1, e, mid+1, r));
    }
    
    
    public static int merge(int l,int r){
        return l^r;
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

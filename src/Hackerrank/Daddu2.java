/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class Daddu2 {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        long k=in.nextLong();
        long[] a=new long[n+1];
        for(int i=1;i<=n;i++){
            a[i]=in.nextLong();
        }
        GCDSegmentTree tree=new GCDSegmentTree(a);
        int min=Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            if(a[i]==k){
                min=1;;
                break;
            }
            int l=i;
            
            int r=n;
            while(r-l>1){
                int mid=(l+r)/2;
                long g=tree.query(i, mid);
                if(g<=k){
                    r=mid;
                }
                else{
                    l=mid;
                }
            }
            if(tree.query(i, r)==k){
                if(r-i+1<min)
                    min=r-i+1;
            }
        }
        if(min==Integer.MAX_VALUE)
            System.out.println(-1);
        else
        System.out.println(min);
    }
    
    static class GCDSegmentTree {
        
        long[] a;
        long[] t;
        int size;

        public GCDSegmentTree(long[] a) {
            this.a = a;
            size = a.length - 1;
            t = new long[4 * size];
            build(1, 1, size);
        }

        public void build(int id, int l, int r) {
            if (l == r) {
                t[id] = a[l];
                return;
            }
            int mid = (l + r) / 2;
            build(2 * id, l, mid);
            build(2 * id + 1, mid + 1, r);
            t[id] = gcd(t[2 * id], t[2 * id + 1]);
        }
        
        public long query(int l,int r){
            return query(1, 1, size, l, r);
        }
        
        public long query(int id, int s, int e, int l, int r) {
            if (s >= l && e <= r) {
                return t[id];
            }
            if ((r < s) || l > e) {
                return -1;
            }
            int mid = (s + e) / 2;
            long lans = query(2 * id, s, mid, l, r);
            long rans = query(2 * id + 1, mid + 1, e, l, r);
            if (lans == -1) {
                lans = rans;
            }
            if (rans == -1) {
                rans = lans;
            }
            return gcd(lans, rans);
        }

        public long gcd(long a, long b) {
            if (a < b) {
                long temp = a;
                a = b;
                b = temp;
            }

            while (b > 0) {
                long temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

    }
    
    
    public static class FasterScanner {
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

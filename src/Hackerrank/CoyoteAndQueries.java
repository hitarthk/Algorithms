/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

/**
 *
 * @author hitarthk
 */
public class CoyoteAndQueries {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int q=in.nextInt();
        int[] a=new int[n];
        ArrayList<ArrayList<Integer>> places= new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<=200000;i++){
            places.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
            if(places.get(a[i])==null){
                
            }
        }
            
    }
    
    static class Query implements Comparable<Query>{
        int l;
        int r;

        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if((this.r-this.l)<=o.r-o.l)
                return -1;
            else
                return 1;
        }
        
        
    }

    static int N = (int) (2e5+1);  // limit for array size
    static int n;  // array size
    static int t[]=new int[2*N];

    void build() {  // build the tree
        for (int i = n - 1; i > 0; --i) {
            t[i] = t[i << 1] + t[i << 1 | 1];
        }
    }

    void modify(int p, int value) {  // set value at position p
        for (t[p += n] = value; p > 1; p >>= 1) {
            t[p >> 1] = t[p] + t[p ^ 1];
        }
    }

    static int query(int l, int r) {  // sum on interval [l, r)
        int res = 0;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if (l %2==1) {
                res += t[l++];
            }
            if (r %2== 1) {
                res += t[--r];
            }
        }
        return res;
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

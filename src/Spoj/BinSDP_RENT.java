/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoj;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class BinSDP_RENT {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int t=in.nextInt();
        while(t>0){
            t--;
            int n=in.nextInt();
            Order[] o=new Order[n];
            for(int i=0;i<n;i++){
                int s=in.nextInt();
                int d=in.nextInt();
                int p=in.nextInt();
                o[i]=new Order(p, s, d);
            }
            Arrays.sort(o);
            int[] dp=new int[n];
            
            dp[0]=o[0].p;
            
            for(int i=1;i<n;i++){
                if(o[i].s>=o[i-1].e){
                    dp[i]=dp[i-1]+o[i].p;
                }
                else{
                    int last=0;
                    if(o[0].e<=o[i].s){
                        int l=0;
                        int r=i;
                        while(r-l>1){
                            int m=l+(r-l)/2;
                            if(o[m].e<=o[i].s){
                                l=m;
                            }
                            else{
                                r=m;
                            }
                        }
                        last=dp[l];
                    }
                    else{
                        last=0;
                    }
                    dp[i]=Math.max(last+o[i].p,dp[i-1]);
                }
            }
            System.out.println(dp[n-1]);
        }
    }
    
    static class Order implements Comparable<Order>{
        int p;
        int s;
        int e;
        
        public Order(int p,int s,int d){
            this.p=p;
            this.s=s;
            this.e=s+d;
        }
                        
        @Override
        public int compareTo(Order o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return this.e-o.e;
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

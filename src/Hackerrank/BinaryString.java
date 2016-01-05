/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class BinaryString {
    public static void main(String[] args) {
//        BigInteger b=new BigInteger("327469897674637484675958574758687");
//        b=b.add(new BigInteger("1"));
//       
        //System.out.println(b);
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int k=in.nextInt();
        int t=in.nextInt();
        char[] s=in.nextLine().toCharArray();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=s[i]-'0';
        }
        int[] ans=new int[n];
        int[] c=new int[n];
        int i=0;
        
        while(t>=0 && i<n){
            
            while(i<n && (a[i]^c[i])==1){
                if(i<n-1)
                c[i+1]=(c[i+1]+c[i])%2;
                       
                ans[i]=1;
                i++;
            }
            t--;
            if(t<0 || i==n)
                break;
            c[i]=(c[i]+1)%2;
            if(i+k<=n-1){
                c[i+k]=(c[i+k]+1)%2;
            }
            
        }
        while(i<n){
            ans[i] = a[i]^c[i];
        }
        System.out.println(Arrays.toString(ans));
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

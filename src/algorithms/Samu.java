/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeSet;

/**
 *
 * @author hitarthk
 */
public class Samu {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int t=in.nextInt();
        while(t>0){
            int n=in.nextInt();
            long[][] a=new long[3][n+1];
            long[][] c=new long[3][n+1];
            for(int i=1;i<=n;i++){
                a[0][i]=in.nextLong();
                a[1][i]=in.nextLong();
                a[2][i]=in.nextLong();
                
            }
            c[0][1]=a[0][1];
            c[1][1]=a[1][1];
            c[2][1]=a[2][1];
            for(int i=2;i<=n;i++){
                for(int j=0;j<=2;j++){
                    //c[j][i]=Math.min(c[(j+1)%3][i-2], c[(j+2)%3][i-2])+a[j][i];
                    long min=Long.MAX_VALUE;
                    for(int k=(j+1)%3;k!=j;){
                        long temp=Math.min(c[(k+1)%3][i-2], c[(k+2)%3][i-2])+a[k][i-1];
                        if(temp<min){
                            min=temp;
                        }
                        k=(k+1)%3;
                    }
                    c[j][i]=min+a[j][i];
                }
            }
            long ans=Math.min(c[0][n], Math.min(c[1][n], c[2][n]));
            System.out.println(ans);
            t--;
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

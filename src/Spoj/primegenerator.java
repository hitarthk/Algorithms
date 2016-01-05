/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class primegenerator {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        boolean[] b=new boolean[32001];
        for(int i=2;i*i<=32000;i++){
            if(!b[i]){
                for(int j=0;i*(i+j)<=32000;j++){
                    b[i*(i+j)]=true;
                }
            }
        }
        
        int[] primes=new int[4000];
        int number=0;
        for(int i=2;i<=32000;i++){
            if(!b[i]){
                primes[number++]=i;
            }
        }
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        while(t>0){
            t--;
            int m=in.nextInt();
            int n=in.nextInt();
            boolean[] isPrime=new boolean[n-m+1];
            for(int i=0;i<number;i++){
                int p=primes[i];
                int start=0;
                if(p>=m)
                    start=2*p;
                else
                    start=m+(p-m%p);
                for(int j=start;j<=n;j+=p){
                    isPrime[j-m]=true;
                }
                
            }
            for(int i=m;i<=n;i++){
                if(!isPrime[i-m]&& i!=1)
                    out.println(i);
            }
            out.println();
        }
        out.close();
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

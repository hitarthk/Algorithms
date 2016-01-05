/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_C;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class C_234_ {
    static int n;
    static int m;
    static int i;
    static int j;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        n=in.nextInt()-1;
        m=in.nextInt()-1;
        int x=in.nextInt()%4;
        int y=in.nextInt()%2;
        int z=in.nextInt()%4;
        z = (-z+4)%4;
//        System.out.println("x"+x);
//        System.out.println("y"+y);
//        System.out.println("z"+z);
//        
        int p=in.nextInt();
        PrintWriter out=new PrintWriter(System.out);
        while(p>0){
            p--;
            i=in.nextInt()-1;
            j=in.nextInt()-1;
            int tempn=n;
            int tempm=m;
            for(int loop=0;loop<x;loop++){
                r90();
            }
            
            for(int loop=0;loop<y;loop++){
                mh();
            }
            
            for(int loop=0;loop<z;loop++){
                r90();
            }
            out.println((i+1)+" "+(j+1));
            n=tempn;
            m=tempm;
            
        }
        out.close();
    }
    
    public static void r90(){
        int tempi=i;
        int tempj=j;
        i=tempj;
        j=n-tempi;
        int temp=n;
        n=m;
        m=temp;     
    }
    
    public static void mv(){
        i=n-i;
    }
    
    public static void mh(){
        j=m-j;
    }
    
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

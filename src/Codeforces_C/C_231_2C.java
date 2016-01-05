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
public class C_231_2C {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int m=in.nextInt();
        int d11=0;
        int d10=0;
        int d00=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                String s=in.nextString();
                System.out.println(s);
                if(s.equals("11"))
                    d11++;
                if(s.equals("00"))
                    d00++;
                if(s.equals("10")||s.equals("01"))
                    d10++;
            }
        }
        System.out.println(d11+" "+d10+" "+d00);
        PrintWriter out=new PrintWriter(System.out);
        int i,j=1;
        A:for(i=1;i<=n && d11>0;i++){
            for(;j<=m && d11>0;j++){
                out.print("11 ");
                d11--;
                if(d11==0){
                    if(j<m)
                        j++;
                    else{
                        j=1;
                        i++;
                    }
                    break A;
                }
            }
            j=1;
            out.println();
        }
        
        boolean check=true;
        
        B:for(;i<=n && d10>0;i++){
            for(;j<=m && d10>0;j++){
                if(check){
                    out.print("10 ");
                }
                else{
                    out.print("01 ");
                }
                d10--;
                if(d10==0){
                    if(j<m)
                        j++;
                    else{
                        j=1;
                        i++;
                    }
                    break B;
                }
            }
            j=1;
            check=!check;
            out.println();
        }
        
        for(;i<=n && d00>0;i++){
            for(;j<=m && d00>0;j++){
                out.print("00 ");
                d00--;
            }
            out.println();
            j=1;
        }
        out.close();
                
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

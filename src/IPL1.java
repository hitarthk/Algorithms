
import java.io.IOException;
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
public class IPL1 {
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        String s=in.nextLine();
        String s1="head rush";
        String s2="cubing club";
        int[] f=new int[26];
        int[] f1=new int[26];
        int[] f2=new int[26];
        for(int i=0;i<s.length();i++){
            f[s.charAt(i)-97]++;
        }
        for(int i=0;i<s1.length();i++){
            //System.out.println(s1.charAt(i)-97);
            if(s1.charAt(i)!=' ')
            f1[s1.charAt(i)-97]++;
        }
        for(int i=0;i<s2.length();i++){
            if(s2.charAt(i)!=' ')
            f2[s2.charAt(i)-97]++;
        }
        int max=0;
        int c=-1;
        for(int i=0;i<26;i++){
            if(f[i]>max){
                max=f[i];
                c=i;
            }
        }
       
        if(f1[c]>0 && f2[c]>0){
            System.out.println("Thanks both");
        }
        else if(f1[c]>0){
            System.out.println("Thanks "+s1);
        }
        else if(f2[c]>0){
            System.out.println("Thanks "+s2);
        }
        else{
            System.out.println("none");
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

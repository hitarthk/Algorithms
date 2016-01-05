
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
public class C_218_C {
    public static void main(String[] args) {
        FasterScanner  in=new FasterScanner();
        String s=in.nextLine();
        long[] a=new long[3];
        for (int i = 0; i < 3; i++) {
            a[i]=in.nextLong();
        }
        long[] c=new long[3];
        for (int i = 0; i < 3; i++) {
            c[i]=in.nextLong();
        }
        long[] r=new long[3];
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='B'){
                r[0]++;
            }
            if(s.charAt(i)=='S'){
                r[1]++;
            }
            if(s.charAt(i)=='C'){
                r[2]++;
            }
        }
        
        long m=in.nextLong();
        
        if((r[0]-a[0])*c[0]+(r[1]-a[1])*c[1]+(r[2]-a[2])*c[2]>m){
            System.out.println(0);
        }
        
        else{
            long right=(long)1e13;
            long left=0;
            while(right-left>1){
                long mid=(right+left)/2;
                long requiredMoney=0;
                if(r[0]*mid>a[0]){
                    requiredMoney+=(r[0]*mid-a[0])*c[0];
                }
                if(r[1]*mid>a[1]){
                    requiredMoney+=(r[1]*mid-a[1])*c[1];
                }
                if(r[2]*mid>a[2]){
                    requiredMoney+=(r[2]*mid-a[2])*c[2];
                }
                        
                if(requiredMoney<=m){
                    left=mid;
                }
                else{
                    right=mid;
                }
            }
            System.out.println(left);
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

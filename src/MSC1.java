
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
public class MSC1 {
    static int max;
    static int[] a;
    static int n;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int t=in.nextInt();
        while (t > 0) {
            t--;
            n = in.nextInt();
            a = new int[n + 1];
            max=0;
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            int s=0;
            for(int i=1;i<=n/2;i++){
                s+=Math.abs(a[i]-a[n-i+1]);
            }
            f(1,s);
            System.out.println(max);
        }
    }
    
    static void f(int i,int s){
        if(i==n+1){
            if(s>max){
                max=s;
            }
        }
        else{
            if(i==1 || i==n){
                f(i+1,s);
            }
            else{
                if(a[i-1]%2==0 && a[i+1]%2==0){
                    int old=a[i];
                    
                    {
                        s-=Math.abs(a[i]-a[n-i+1]);
                        a[i]=(a[i-1]+a[i+1])/2;
                        s+=Math.abs(a[i]-a[n-i+1]);
                        f(i+1,s);
                        s-=Math.abs(a[i]-a[n-i+1]);
                        a[i]=old;
                        s+=Math.abs(a[i]-a[n-i+1]);
                    }
                    
                }
                f(i+1,s);
            }
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

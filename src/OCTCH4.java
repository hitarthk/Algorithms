
import java.io.IOException;
import java.io.PrintWriter;
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
public class OCTCH4 {
    public static void main(String[] args) {
            FasterScanner in=new FasterScanner();
            long mod=1000000000+7;
            int n = in.nextInt();
            int m = in.nextInt();
            int c = in.nextInt();
            long[] f=new long[c+2];
            long[] fu = new long[c + 2];
            long[] fl=new long[c+2];
            for(int i=0;i<n;i++){
                fu[in.nextInt()]++;
            }
            for(int i=0;i<m;i++){
                fl[in.nextInt()]++;
            }
            for(int i=1;i<=c;i++){
                f[i]=fl[i]*fu[i];
            }
            long[][] dp=new long[c+2][c+2];
            for(int i=0;i<=c;i++){
                dp[i][0]=1;
            }
            
            for(int i=1;i<=c;i++){
                for(int j=1;j<=c;j++){
                    if(i>=j){
                        dp[i][j]=(dp[i-1][j-1]*f[i] + dp[i-1][j])%mod;
                    }
                }
            }
            PrintWriter out=new PrintWriter(System.out);
            for(int i=2;i<=c+1;i++){
                out.println(dp[c][i]);
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

   

    

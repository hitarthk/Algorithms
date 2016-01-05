
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
public class C_237_D_2 {

    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        String s=in.nextLine();
        int n=s.length();
        s=s+"0";
        long[][] dp=new long[n+1][5];
        long mod=(1000000007);
        //0 means 0
        //1 means bomb
        //2 means 2
        //3 means 1 here and bomb on the left
        //4 means 1 here and bomb to be kept on the right
        if(s.charAt(0)=='?'){
            dp[0][0]=1;
            dp[0][1]=1;
            dp[0][4]=1;
        }
        else{
            if(s.charAt(0)=='0'){
                dp[0][0]=1;
            }
            if(s.charAt(0)=='*'){
                dp[0][1]=1;
            }
            if(s.charAt(0)=='1'){
                dp[0][4]=1;
            }
//            if(s.charAt(0)=='2'){
//                dp[0][2]=1;
//            }
        }
        for(int i=1;i<=n;i++){
            if(s.charAt(i)=='?'){
                dp[i][0]=(dp[i-1][0]+dp[i-1][3])%mod;
                dp[i][1]=(dp[i-1][1]+dp[i-1][2]+dp[i-1][4])%mod;
                dp[i][2]=(dp[i-1][1])%mod;
                dp[i][3]=(dp[i-1][1])%mod;
                dp[i][4]=(dp[i-1][0]+dp[i-1][3])%mod;
            }
            else{
                if(s.charAt(i)=='0'){
                    dp[i][0]=(dp[i-1][0]+dp[i-1][3])%mod;
                }
                if(s.charAt(i)=='*'){
                    dp[i][1]=(dp[i-1][1]+dp[i-1][2]+dp[i-1][4])%mod;
                }
                if(s.charAt(i)=='2'){
                    dp[i][2]=(dp[i-1][1])%mod;
                }
                if(s.charAt(i)=='1'){
                    dp[i][3]=(dp[i-1][1])%mod;
                    dp[i][4]=(dp[i-1][0]+dp[i-1][3])%mod;
                }
            }
        }
        
        long ans=0;
        for(int i=0;i<4;i++){
            if(i!=2)
            ans = (ans+dp[n-1][i])%mod;
        }
        System.out.println(ans);
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

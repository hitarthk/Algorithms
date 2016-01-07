
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
class JanLong1 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            long[] a=in.nextLongArray(n+1);
            long ans=0;
            long sum=0;
            long prod=2;
            long mod=(long)1e9+7;
            for(int i=n;i>=1;i--){
                sum+=(prod*a[i])%mod;
                sum%=mod;
                prod=(prod*2)%mod;
            }
            //System.out.println("sum "+sum);
            //System.out.println("prod "+prod);
            ans=(sum*a[0])%mod;
            long inv=modPow(2, mod-2, mod);
            prod=(prod*inv)%mod;
            //sum=(sum*inv)%mod;
            //prod=prod/2;
            sum=(sum-(prod*a[1])%mod)%mod;
            prod=(prod*inv)%mod;
            sum=(sum+mod)%mod;
            //sum=(sum*inv)%mod;
            //System.out.println(sum);
            for(int i=1;i<=n-1;i++){
                ans+=a[i]*sum;
                ans%=mod;
                sum=(sum-(prod*a[i+1])%mod)%mod;
                sum=(sum+mod)%mod;
                sum=(sum*2)%mod;
            }
            System.out.println(ans);
        }
    }
    
    static long modPow(long b,long e,long m){
        if(e==0){
            return 1;
        }
        else{
            long ret=modPow(b, e/2, m);
            ret=(ret*ret)%m;
            if(e%2==1){
                ret=(ret*b)%m;
            }
            return ret;
        }
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


import java.io.IOException;
import java.util.Arrays;
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
public class C_290_C {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int a=in.nextInt();
        int b=in.nextInt();
        int k=in.nextInt();
        long m=(long)1e9+7;
        long[] ans=new long[n+1];
        long[] tans=new long[n+1];
        
        long[] sans=new long[n+1];
        ans[a]=1;
        for(int i=1;i<=n;i++){
            sans[i]=sans[i-1]+ans[i];
        }
        //System.out.println(Arrays.toString(sans));
        for(int t=1;t<=k;t++){
            for(int p=1;p<=n;p++){
                if(p<b){
                    tans[p]+=(((sans[p+(b-p-1)/2]-sans[p])%m+m)%m+((sans[p-1]-sans[0])%m+m)%m)%m;
                }
                else if(p>b){
                    tans[p]+=(((sans[n]-sans[p])%m+m)%m+((sans[p-1]-sans[p-(p-b-1)/2-1])%m+m)%m)%m;
                }
            }
            ans=tans;
            //System.out.println(Arrays.toString(ans));
            tans=new long[n+1];
            sans=new long[n+1];
            for(int i=1;i<=n;i++){
                sans[i]=(sans[i-1]+ans[i])%m;
            }
        }
        //System.out.println("Final "+Arrays.toString(sans));
        System.out.println(sans[n]);
        
    }
    
    
    public static class FasterScanner {

        public FasterScanner() {
        }

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


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class IPL4 {
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int k=in.nextInt();
        int[] p=new int[n];
        for(int i=0;i<n;i++){
            p[i]=in.nextInt();
        }
        Arrays.sort(p);
        ArrayList<Integer> f=new ArrayList<>();
        int d=1;
        int freq=1;
        for(int i=1;i<n;i++){
            
            if(p[i]!=p[i-1]){
                f.add(freq);
                freq=1;
                d++;
            }
            else{
                freq++;
            }
        }
        f.add(freq);
//        for(int i=0;i<f.size();i++){
//            System.out.println(f.get(i));
//        }
        n=d;
        long m=(long)1000000007;
        long[][] a=new long[n+1][k+1];
        for(int i=0;i<=n;i++){
            a[i][0]=1;
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                a[i][j]=a[i-1][j];
                for(int t=1;t<=f.get(i-1)&& t<=j;t++){
                    a[i][j] = (a[i][j]+a[i-1][j-t])%m;
                }
            }
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                a[i][j] = (a[i-1][j]+a[i-1][j-1])%m;
            }
        }
        
        System.out.println(a[n][k]);
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

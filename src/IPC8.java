
import java.io.IOException;
import java.util.ArrayList;
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
public class IPC8 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int m=in.nextInt();
        
        
        long[][] dp=new long[2][m+2];
        
        for(int i=1;i<=n;i++){
            int size=in.nextInt();
            long[] a=new long[size+1];
            for(int j=1;j<=size;j++){
                a[j]=in.nextLong();
            }
            long[] maxsub=new long[size+1];
            for(int length=1;length<=m && length<=size;length++){
                long max=0;
                for(int k=1;k<=length;k++){
                    max+=a[k];
                }
                long temp=max;
                for(int k=length+1;k<=size;k++){
                    temp-=a[(k-length)];
                    temp+=a[(k)];
                    if(temp>max){
                        max=temp;
                    }
                }
                maxsub[length]=max;
                //System.out.println(i+" "+length+" "+max);
                
            }
            for (int j = 1; j <= m; j++) {
                dp[1][j] = dp[0][j];
                for (int length = 1; length <= j && length <= size; length++) {
                    long temp = maxsub[length] + dp[0][j - length];
                    if (temp > dp[1][j]) {
                        dp[1][j] = temp;
                    }
                }
            }
            dp[0]=Arrays.copyOf(dp[1],dp[1].length);
            //System.out.println(i+" "+dp[0][m]);
        }
//        
//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=m;j++){
//                dp[i][j]=dp[i-1][j];
//                for(int length=1;length<=j && length<=size[i];length++){
//                    long temp=maxsub[i][length]+dp[i-1][j-length];
//                    if(temp>dp[i][j])
//                        dp[i][j]=temp;
//                }
//            }
//        }
        long ans=0;
        for(int j=0;j<=m;j++){  
            if(dp[1][j]>ans)
                ans=dp[1][j];
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

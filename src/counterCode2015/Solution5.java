/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterCode2015;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Stack;

/**
 *
 * @author hitarthk
 */
public class Solution5 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }
        int[] dp=new int[n];
        int[] nearestMin=new int[n];
        nearestMin[0]=-1;
        Stack<Integer> s=new Stack<>();
        s.push(0);
        for(int i=1;i<n;i++){
            while(!s.isEmpty() && a[s.peek()]>a[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nearestMin[i]=-1;
            }
            else{
                nearestMin[i]=s.peek();
            }
            s.push(i);
        }
        int max=Integer.MIN_VALUE;
        int min=a[0];
        dp[0]=Integer.MIN_VALUE;
        for(int i=1;i<n;i++){
            if(a[i]>a[i-1]){
                dp[i]=1;
            }
            else{
                if(dp[i-1]==Integer.MIN_VALUE || a[i]<=min){
                    dp[i]=Integer.MIN_VALUE;
                    min=a[i];
                }
                else{
                    
                    if(a[nearestMin[i]]<a[i]){
                        dp[i]=dp[i-1]+1;
                    }
                    else{
                        if(dp[nearestMin[i]]!=Integer.MIN_VALUE){
                            
                        }
                        else{
                            dp[i]=Integer.MIN_VALUE;
                        }
                    }
                    
                }
            }
            System.out.println(i+" "+a[i]+" "+dp[i]);
            if(dp[i]>max){
                max=dp[i];
            }
        }
        if(max==Integer.MIN_VALUE)
            System.out.println(0);
        else
        System.out.println(max);
    }
    
    public static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
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

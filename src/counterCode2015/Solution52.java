/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterCode2015;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class Solution52 {
    
    public static void main(String[] args) {
            FasterScanner in=new FasterScanner();
            int n=in.nextInt();
            int[] a=new int[n+1];
            for(int i=0;i<n;i++){
                a[i]=in.nextInt();
            }
            int i=0;
            int j=1;
            int max=0;
            while(j<a.length){
                while(j<a.length && a[j]>a[i]){
                    j++;
                }
                int[] ta=new int[j-i];
                int k=0;
                while(k+i<j){
                    ta[k]=a[k+i];
                    k++;
                }
                max=Math.max(max, cnt(ta,ta.length,0));
                i=j;
                j++;
            }
            System.out.println(max);
    }
    
    public static int cnt(int[] a,int size,int step){
        if(size==1){
            return step;
        }
//        System.out.println(size);
//        System.out.println(Arrays.toString(a));
        int i=0;
        int[] ta=new int[size];
        int j=1;
        int tsize=0;
        while(j<size){
            while( j<size && a[j]>a[j-1] ){
                j++;
            }
            ta[tsize++]=a[i];
            i=j;
            j++;
        }
        if(i<size)
        ta[tsize++]=a[i];
        step++;
        return cnt(ta,tsize,step);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package algorithms;
package counterCode2015;
import java.io.IOException;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class Solution6{
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int q=in.nextInt();
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
        }
        int p=1;
        int[] rp=new int[n+1];
        rp[0]=1;
        int[] p3=new int[2];
        boolean ch=false;
        for(int i=1;i<=n;i++){
            if(ch){
                if(a[i]%3==0){
                    rp[i]=p;
                    p3[1]=i;
                    ch=false;
                }
                else{
                    rp[i]=(p*a[i])%3;
                    p=rp[i];
                }
            }
            else{
                rp[i]=(rp[i-1]*a[i]%3)%3;
                if(rp[i]==0){
                    p3[0]=i;
                    ch=true;
                    rp[i]=p;
                }
                else{
                    p=rp[i];
                }
            }
            //System.out.println(i+" "+rp[i]);
        }
        
        while(q>0){
            q--;
            int l=in.nextInt();
            int r=in.nextInt();
            if(p3[0]==0){
                System.out.println((rp[r]*rp[l-1])%3);
            }
            else{
                if((l<=p3[0] && r>=p3[0]&&r<p3[1])||(l<=p3[1] && l>p3[0] && r>=p3[1])){
                    System.out.println(0);
                }
                else{
                    System.out.println((rp[r]*rp[l-1])%3);
                }
            }
            
        }
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

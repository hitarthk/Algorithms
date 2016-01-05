/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class Solutio {
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        BigInteger k=new BigInteger(in.nextString());
        int n=in.nextInt();
        BigInteger[] a=new BigInteger[n+2];
        BigInteger check=new BigInteger("1");
        for(int i=1;i<=n;i++){
            a[i]=new BigInteger(in.nextString());
            a[i]=a[i].mod(k);
            a[i]=a[i].gcd(k);
            //check = (check*a[i])%k;
            check=check.multiply(a[i]).mod(k);
        }
        if(!check.equals(new BigInteger("0"))){
            System.out.println("NONE");
            return;
        }
        a[n+1]=new BigInteger("1");
        System.out.println(Arrays.toString(a));
        int i=1;
        int j=1;
        int min=Integer.MAX_VALUE;
        while(i<=n && j<=n){
            BigInteger temp=new BigInteger("1");
            while(j<=n &&!temp.mod(k).equals(new BigInteger("0"))){
                
                temp=temp.multiply(a[j]);
//                if(temp%k!=0){
//                    temp=temp%k;
//                }
                j++;
            }
            boolean ch=false;
            while(i<=n && temp.mod(k).equals(new BigInteger("0"))){
                temp=temp.divide(a[i]);
                ch=true;
                i++;
            }
            if(ch)
            min=Math.min(min, j-i+1);
            
        }
        System.out.println("Minimum interval length: "+min);
        System.out.println("Found the intervals:");
        i=1;
        BigInteger temp=new BigInteger("1");
        j=1;
        while(j<=min){
            temp=temp.multiply(a[j]);
            j++;
        }
        j--;
        while(j<=n){
            //System.out.println(i+" "+j+" "+temp);
            if(temp.mod(k).equals(new BigInteger("0"))){
                System.out.println("["+i+" "+j+"]");
            }
            temp=temp.divide(a[i]);
            j++;
            temp=temp.multiply(a[j]);
            i++;
            
        }
    }
    
    public static long gcd(long a,long b){
        if(a<b){
            long temp=a;
            a=b;
            b=temp;
        }
        while(b>0){
            long temp=a%b;
            a=b;
            b=temp;
        }
        return a;
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

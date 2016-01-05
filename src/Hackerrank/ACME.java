/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author hitarthk
 */
public class ACME {
    static int n;
    static long k;
    static long count;
    static long[] a;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        n=in.nextInt();
        k=in.nextLong();
        a=new long[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextLong();
            //a[i]=a[i]%k;
        }
        f(0,n-1);
        System.out.println(count);
    }
    
    public static void f(int p,int q){
        if(p>=q)
            return;
        long min=Long.MAX_VALUE;
        int minIndex=-1;
        int i=p;
        while(i<=q){
            if(a[i]<min){
                min=a[i];
                minIndex=i;
            }
            i++;
        }
        //System.out.println(p+" "+q+" "+"minIndex "+minIndex);
        Hashtable<Long, Long> h1=new Hashtable<>();
        Hashtable<Long,Long> h2=new Hashtable<>();
        long sum=0;
        for( i=minIndex+1;i<=q;i++){
            sum = (sum+a[i]%k)%k;
            long f=0;
            if(h2.get(sum)!=null){
                f=h2.get(sum);
            }
            f++;
            h2.put(sum, f);
        }
        sum=0;
        for(i=minIndex-1;i>=p;i--){
            sum = (sum+a[i]%k)%k;
            long f=0;
            if(h1.get(sum)!=null){
                f=h1.get(sum);
            }
            f++;
            h1.put(sum, f);
        }
        //System.out.println("|||||||||||||");
        long l0=0;
        long r0=0;
        //System.out.println(h1.get(new Long(0)));
        if(h1.get(new Long(0))!=null){
            l0=h1.get(new Long(0));
            //System.out.println("l0 "+l0);
        }
        if(h2.get(new Long(0))!=null)
            r0=h2.get(new Long(0));
        count += l0;
        count += r0;
        Set s1=h1.entrySet();
        for (Iterator it = s1.iterator(); it.hasNext();) {
            Entry e = (Entry) it.next();
            //System.out.println(e);
            long find;
            find = (k-(Long)e.getKey())%k;
            //System.out.println(find);
            long freq=(Long)e.getValue();
            if(h2.get(new Long(find))!=null){
                count+= freq*h2.get(new Long(find));
            }
        }
        
        f(p,minIndex-1);
        f(minIndex+1,q);
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

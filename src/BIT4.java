
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
public class BIT4 {
    static long ans=0;
    static int max=100000;
    static int[] f=new int[max+1];
    static int d;
    static int[] a;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int m=in.nextInt();
        d=in.nextInt();
        a=in.nextIntArray(n);
        Query[] q=new Query[m];
        for(int i=0;i<m;i++){
            int l=in.nextInt()-1;
            int r=in.nextInt()-1;
            q[i]=new Query(i, l, r);
        }
        Arrays.sort(q);
        int currentL=0;
        int currentR=0;
        long[] fans=new long[m];
        for(int i=0;i<m;i++){
            int L=q[i].l;
            int R=q[i].r;
            //int L = q[i].L, R = q[i].R;
		while(currentL < L) {
                    System.out.println("Called for "+currentL);
                    remove(currentL);
			currentL++;
		}
		while(currentL > L) {
			add(currentL-1);
			currentL--;
		}
		while(currentR <= R) {
			add(currentR);
			currentR++;
		}
		while(currentR > R+1) {
			remove(currentR-1);
			currentR--;
		}
            fans[q[i].id]=ans;
        }
        for(int i=0;i<m;i++){
            System.out.println(fans[i]);
        }
    }
    
    static void remove(int position){
        int i=Math.max(0, a[position]-d);
        int limit=Math.min(max, a[position]+d);
        while(i<=limit){
            ans-=f[i];
            i++;
        }
        System.out.println(f[a[position]]+" "+a[position]);
        f[a[position]]--;
        System.out.println(f[a[position]]+" "+a[position]);
    }
    
    static void add(int position){
        int i=Math.max(0, a[position]-d);
        int limit=Math.min(max, a[position]+d);
        
        f[a[position]]++;
        while(i<=limit){
            ans+=f[i];
            i++;
        }
    }
    
    static class Query implements Comparable<Query>{
        int id;
        int l;
        int r;
        public Query(int id,int l,int r){
            this.id=id;
            this.l=l;
            this.r=r;
        }

        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int tb=this.l/320;
            int ob=o.l/320;
            if(tb==ob)
                return this.r-o.r;
            else
                return tb-ob;
        }
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

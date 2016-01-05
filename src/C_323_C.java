
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
public class C_323_C {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        HashMap<Integer,Integer> h=new HashMap<>();
        int n=in.nextInt();
        int[] a=in.nextIntArray(n*n);
        int[] ans=new int[n];
        for(int i=0;i<n*n;i++){
            if(h.get(a[i])==null){
                h.put(a[i], 1);
            }
            else{
                h.put(a[i], h.get(a[i])+1);
            }
        }
        Arrays.sort(a);
        //System.out.println(Arrays.toString(a));
        int j=n*n-1;
        for(int i=n-1;i>=0;i--){
            while(h.get(a[j])==null || h.get(a[j])<=0){
                j--;
            }
            ans[i]=a[j];
            h.put(ans[i], h.get(ans[i])-1);
            for(int k=n-1;k>i;k--){
                int g=gcd(ans[k],ans[i]);
                h.put(g, h.get(g)-2);
            }
            j--;
        }
        for(int i=0;i<n;i++){
            System.out.print(ans[i]+" ");
        }
    }
    
    static int gcd(int a,int b){
        if(b==0)
            return a;
        else{
            return gcd(b,a%b);
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

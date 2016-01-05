
import java.io.IOException;
import java.io.PrintWriter;
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
public class C_218_D {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        int[] c=in.nextIntArray(n);
        int[] f=new int[n];
        int[] parent=new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
        int m=in.nextInt();
        while(m>0){
            m--;
            int q=in.nextInt();
            if(q==1){
                int p=in.nextInt()-1;
                int x=in.nextInt();
                while(x>0 && p<n){
                    //System.out.println("x "+x);
                    {
                        int X=x;
                        x-=Math.min(x, c[p]-f[p]);
                        f[p]=Math.min(c[p], f[p]+X);
                        if(f[p]==c[p]){
                            parent[p]=p+1;
                        }
                        while (p != parent[p]) {
                            //System.out.println("Hre");
                            parent[p] = parent[parent[p]];    // path compression by halving
                            p = parent[p];
                        } 
                    }
                }
            }
            else{
                int k=in.nextInt()-1;
                System.out.println(f[k]);
            }
        }
        out.close();
    }
    
    /*
    10
71 59 88 55 18 98 38 73 53 58
20
1 5 93
1 7 69
2 3
1 1 20
2 10
1 6 74
1 7 100
1 9 14
2 3
2 4
2 7
1 3 31
2 4
1 6 64
2 2
2 2
1 3 54
2 9
2 1
1 6 86

    */
    
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

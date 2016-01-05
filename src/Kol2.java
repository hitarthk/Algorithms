
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
class Kol2 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        while(t-->0)
        {
            int n=in.nextInt();
            int m=in.nextInt();
            int[][] a=new int[n+2][m+2];
            for(int i=1;i<=n;i++){
                for(int j=1;j<=m;j++){
                    a[i][j]=in.nextInt();
                }
            }
            int[][] ctb=new int[n+2][m+2];
            int[][] cbt=new int[n+2][m+2];
            int[][] clr=new int[n+2][m+2];
            int[][] crl=new int[n+2][m+2];
            for(int i=1;i<=n;i++){
                for(int j=1;j<=m;j++){
                    ctb[i][j]=Math.min(ctb[i-1][j]+a[i][j], a[i][j]);
                }
            }
            for(int i=n;i>0;i--){
                for(int j=1;j<=m;j++){
                    cbt[i][j]=Math.min(cbt[i+1][j]+a[i][j], a[i][j]);
                }
            }
            for(int j=1;j<=m;j++){
                for(int i=1;i<=n;i++){
                    clr[i][j]=Math.min(clr[i][j-1]+a[i][j], a[i][j]);
                }
            }
            for(int j=m;j>=1;j--){
                for(int i=1;i<=n;i++){
                    crl[i][j]=Math.min(crl[i][j+1]+a[i][j], a[i][j]);
                }
            }
            long ans=Integer.MAX_VALUE;
            int mini,minj;
            for(int i=1;i<=n;i++){
                for(int j=1;j<=m;j++){
                    ans=Math.min(ans, cbt[i][j]+ctb[i][j]+clr[i][j]+crl[i][j]-3*a[i][j]);
                }
            }
            out.println(ans);
        }
        out.close();
    }
    
    static class FasterScanner {

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

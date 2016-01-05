
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class GB3 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int h=in.nextInt();
        int w=in.nextInt();
        char[][] a=new char[h+1][];
        for(int i=1;i<=h;i++){
            a[i]=(" "+in.nextLine()).toCharArray();
        }
        a[0]=new char[w+1];
        int[][] colprefix=new int[w+1][h+1];
        int[][] rowprefix=new int[h+1][w+1];
        for(int i=1;i<=w;i++){
            for(int j=1;j<=h;j++){
                colprefix[i][j]=colprefix[i][j-1];
                if(a[j][i]=='.' &&  a[j-1][i]=='.'){
                    colprefix[i][j]++;
                }
            }
        }
        //System.out.println(Arrays.deepToString(a));
        for(int i=1;i<=h;i++){
            for(int j=1;j<=w;j++){
                rowprefix[i][j]=rowprefix[i][j-1];
                if(a[i][j]=='.' && a[i][j-1]=='.'){
                    rowprefix[i][j]++;
                }
            }
        }
        
        //System.out.println(Arrays.deepToString(rowprefix));
        
        
        int q=in.nextInt();
        PrintWriter out=new PrintWriter(System.out);
        while(q-->0){
            int r1=in.nextInt();
            int c1=in.nextInt();
            int r2=in.nextInt();
            int c2=in.nextInt();
            int ans=0;
            for(int i=r1;i<=r2;i++){
                ans+=rowprefix[i][c2]-rowprefix[i][c1-1]-(a[i][c1]=='.' && a[i][c1-1]=='.'?1:0);
                //System.out.println(rowprefix[i][c2]-rowprefix[i][c1-1]);
            }
            //System.out.println("||||||||||||");
            for(int i=c1;i<=c2;i++){
                ans+=colprefix[i][r2]-colprefix[i][r1-1]-(a[r1][i]=='.' && a[r1-1][i]=='.'?1:0);
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

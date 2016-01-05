
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
public class NT2 {
    public static void main(String[] args) {
        //FasterScanner in=new FasterScanner();
        Scanner in=new Scanner(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        int k=in.nextInt();
        long m=(long)1e9+7;
        char[][] c=new char[n+1][6];
        long[][] a=new long[n+1][k+1];
        //char[] s=new char[k+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<6;j++){
                c[i][j]=in.next().charAt(0);
            }
        //    System.out.println(Arrays.toString(c[i]));
        }
        in.nextLine();
        char[] s=new char[k+1];
        String str=(" "+in.nextLine());
        //System.out.println("Here" +str);
        s = str.toCharArray();
        a[0][0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=k;j++){
                if(j==0){
                    a[i][j]=1;
                }
                else{
                    long count=0;
                    for(int l=0;l<6;l++){
                        if(c[i][l]==s[j]){
                            count++;
                        }
                    }
                    a[i][j]=(a[i-1][j]+count*(a[i-1][j-1]))%m;
                }
            }
            
        }
        System.out.println(a[n][k]);
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

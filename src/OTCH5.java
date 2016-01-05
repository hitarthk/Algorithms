
import java.io.IOException;
import java.io.PrintWriter;
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
public class OTCH5 {
    static boolean[] a;
    static int[][][] sp=new int[3][3][3];
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        sp[0][0][0]=1;
        sp[0][0][1]=-2;
        sp[0][0][2]=2;
        sp[0][1][0]=2;
        sp[0][1][1]=-1;
        sp[0][1][2]=2;
        sp[0][2][0]=2;
        sp[0][2][1]=-2;
        sp[0][2][2]=3;
        
        sp[1][0][0]=1;
        sp[1][0][1]=2;
        sp[1][0][2]=2;
        sp[1][1][0]=2;
        sp[1][1][1]=1;
        sp[1][1][2]=2;
        sp[1][2][0]=2;
        sp[1][2][1]=2;
        sp[1][2][2]=3;
        
        sp[2][0][0]=-1;
        sp[2][0][1]=2;
        sp[2][0][2]=2;
        sp[2][1][0]=-2;
        sp[2][1][1]=1;
        sp[2][1][2]=2;
        sp[2][2][0]=-2;
        sp[2][2][1]=2;
        sp[2][2][2]=3;
        
        
        a=new boolean[5000001];
        int[] b=new int[3];
        b[0]=3;
        b[1]=4;
        b[2]=5;
        
        int[][] ans=new int[3][3];
        dfs(b);
        
        for(int i=1;i<=5000000;i++){
            if(a[i]){
                for(int j=1;i*j<=5000000;j++){
                    a[i*j]=true;
                }
            }
        }
        
//        for(int i=1;i<=100;i++){
//            A:for(int j=1;j<=i;j++){
//                for(int k=1;k<=i;k++){
//                    if(j*j+k*k==i*i && !a[i]){
//                        System.out.println("WA "+i+" "+j+" "+k+" "+a[i]);
//                    }
//                    else{
//                        
//                    }
//                }
//            }
//        }
        //System.out.println("Finish");
        int t=in.nextInt();
        while(t>0){
            t--;
            int n=in.nextInt();
            if(a[n]){
                out.println("YES");
            }
            else{
                out.println("NO");
            }
        }
        out.close();
    }
    
    static void dfs(int[] b){
        if(b[2]<=5000000){
            a[b[2]]=true;
//            if(!a[b[2]]){
//            for(int j=1;b[2]*j<=5000000;j++){
//                a[b[2]*j]=true;
//            }
            //System.out.println(Arrays.toString(b));
            for(int i=0;i<3;i++){
                int[] t=mul(sp[i],b);
                dfs(t);
            }
        }
    }
    
    static int[] mul(int[][] a,int[] b){
        int[] ans=new int[3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ans[i]+=a[i][j]*b[j];
            }
        }
        return ans;
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

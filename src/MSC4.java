
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class MSC4 {
    static Node[] v;
    static long[][] dp;
    static int n;
    static long m;
    static int k;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T>0){
            T--;
            n=in.nextInt();
            m=in.nextLong();
            k=in.nextInt();
            v=new Node[n];
            dp=new long[n][k+1];
            for(int i=0;i<n;i++){
                for(int j=0;j<=k;j++){
                    Arrays.fill(dp[i], Long.MIN_VALUE);
                }
            }
            
            for(int i=0;i<n;i++){
                long w=in.nextLong();
                v[i]=new Node(i,w);
            }
            
            
            for(int i=0;i<n-1;i++){
                int a=in.nextInt();
                int b=in.nextInt();
                v[a].c.add(b);
                v[b].parent.add(a);
            }
            long max=Long.MIN_VALUE;
            for(int i=0;i<n;i++){
                for(int j=0;j<=k;j++){
                    long temp=f(i,j);
                    if(temp>max){
                        max=temp;
                    }
                }
            }
            out.println(max);
        }
        out.close();
    }
    
    static long f(int i,int j){
        if(dp[i][j]==Long.MIN_VALUE){
            if(v[i].c.size()==0){
                if(j==0){
                    dp[i][j]=v[i].w;
                }
                if(j==1){
                    dp[i][j]=v[i].w*m;
                }
                return dp[i][j];
            }
            long mul=1;
            long ans=dp[i][j];
            for(int l=0;l<=1 && l<=j;l++){
                long temp=mul*v[i].w;
                long ctemp=Long.MIN_VALUE;
                for(Integer child:v[i].c){
                    long cans=f(child,j-l);
                    if(cans>ctemp){
                        ctemp=cans;
                    }
                }
                if(ctemp>=0){
                    temp+=ctemp;
                }
                if(temp>ans){
                    ans=temp;
                }
                mul*=m;
            }
            dp[i][j]=ans;
        }
        return dp[i][j];
    }
    
    static long pow(long e){
        if(e==0){
            return 1;
        }
        long ans=pow(e/2);
        return e%2==0?ans*ans:ans*ans*m;
    }
    
    static class Node{
        ArrayList<Integer> parent;
        ArrayList<Integer> c;
        long w;
        int id;
        public Node(int id,long w){
            this.id=id;
            parent=new ArrayList<>();
            c=new ArrayList<>();
            this.w=w;
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

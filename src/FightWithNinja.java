
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class FightWithNinja {
    static boolean[] v=new boolean[20002];
    static ArrayList[] l=new ArrayList[20002];
    static int ans;
    static int[] c=new int[2];
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        int T=t;
        for(int i=0;i<=20001;i++){
            l[i]=new ArrayList<Integer>();
        }
        while(t>0){
            t--;
            int n=in.nextInt();
            ans=0;
            for(int i=1;i<=20000;i++){
                l[i]=new ArrayList<Integer>();
                v[i]=true;
            }
            
            for(int i=1;i<=n;i++){
                int x=in.nextInt();
                int y=in.nextInt();
                l[x].add(y);
                l[y].add(x);
                v[x]=false;
                v[y]=false;
            }
            for(int i=1;i<=20000;i++){
                dfs(i);
            }
            out.println("Case "+(T-t)+": "+ans);
        }
        out.close();
    }
    
    static void dfs(int i){
        if(!v[i]){
            c[0]=0;
            c[1]=0;
            visit(i,0);
            ans += Math.max(c[0], c[1]);
        }
    }
    
    static void visit(int i,int w){
        v[i]=true;
        c[w]++;
        for(int j=0;j<l[i].size();j++){
            int x=(int) l[i].get(j);
            if(!v[x]){
                
                visit(x,(w+1)%2);
            }
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

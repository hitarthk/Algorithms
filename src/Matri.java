
import java.io.IOException;
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
public class Matri {
    
    static int[][] a;
    static HashMap<Integer,Boolean> h=new HashMap<>();
    static int n;
     
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        n=in.nextInt();
        a=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                a[i][j]=in.nextInt();
            }
        }
        h.put(a[0][0], Boolean.TRUE);
        if(a[0][0]==a[n-1][n-1]){
            System.out.println("NO");
        }
        else{
        if(dfs(0,0))
        System.out.println("YES");
        else
            System.out.println("NO");
        }
    }
    
    static boolean dfs(int x,int y){
        if(x==n-1 && y==n-1){
            if(!h.containsKey(a[x][y]))
                return true;
            else
                return false;
        }
        boolean check=false;
        if(x<n-1){
            if(!h.containsKey(a[x+1][y])){
                h.put(a[x+1][y], true);
                check=check||dfs(x+1,y);
                h.remove(a[x+1][y]);
            }
            if(check)
                return true;
        }
        
        if(y<n-1){
            if(!h.containsKey(a[x][y+1])){
                h.put(a[x][y+1], true);
                check=check||dfs(x,y+1);
                h.remove(a[x][y+1]);
                
            }
            if(check)
                    return true;
        }
        return false;
        
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

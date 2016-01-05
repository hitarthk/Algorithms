
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
public class C_279_D2_C {
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        String s=in.nextLine();
        long a=in.nextLong();
        long b=in.nextLong();
        boolean[] ac=new boolean[s.length()];
        boolean[] bc=new boolean[s.length()];
        long mod=((long)s.charAt(0)-48)%a;
        if(mod==0){
            ac[0]=true;
        }
        for(int i=1;i<s.length();i++){
            long x=(long)s.charAt(i)-48;
            //out.println(x);
            mod=((10*mod)%a + x)%a;
            //out.println(mod);
            if(mod==0){
                ac[i]=true;
            }
        }
        //out.println(Arrays.toString(ac));
        mod=0;
        
        for(int i=s.length()-1;i>=0;i--){
            long x=(long)(s.charAt(i)-48);
            //out.println(x);
            mod=(mod+((x)*pow(10,s.length()-1-i,b))%b)%b;
            if(mod==0 && s.charAt(i)!='0'){
                bc[i]=true;
            }
        }
        //out.println(Arrays.toString(bc));
        for(int i=0;i<s.length()-1;i++){
            if(ac[i] && bc[i+1]){
                out.println("YES");
                out.println(s.substring(0, i+1));
                out.println(s.substring(i+1, s.length()));
                out.close();
                return;
            }
        }
        out.println("NO");
        out.close();
    }
    
    static long pow(long b,long e,long m){
        if(e==0){
            return 1;
        }
        else{
            long ans=pow(b,e/2,m);
            return e%2==0?(ans*ans)%m:(((ans*ans)%m)*b)%m;
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

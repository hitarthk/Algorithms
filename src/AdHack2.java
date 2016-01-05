
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
public class AdHack2 {
    static long mod=(long)1e9+7;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        int max=1000000;
        long[] f=new long[max+1];
        int[] a=new int[n];
        //int[] a=in.nextIntArray(n);
        for(int i=0;i<n;i++){
            a[i]=1;
        }
        for(int i=0;i<n;i++){
            f[a[i]]++;
        }
        long[] mul=new long[max+1];
        for(int i=1;i<=max;i++){
            for(int j=i;j<=max;j+=i){
                mul[i]+=f[j];
                
            }
        }
        //System.out.println(Arrays.toString(mul));
        long[] gcd=new long[max+1];
        for(int i=max;i>0;i--){
            gcd[i]=(modPow(2,mul[i], mod)-1);
            gcd[i]=gcd[i]<0?gcd[i]+mod:gcd[i];
            for(int j=2*i;j<=max;j+=i){
                gcd[i]-=gcd[j];
                gcd[i]=(gcd[i]+mod)%mod;
                
            }
        }
        //System.out.println(Arrays.toString(gcd));
        for(int i=0;i<n;i++){
            out.print(gcd[a[i]]+" ");
        }
        out.close();
    }
    
    static long modPow( long a, long b, long mod )
    {
    	long res=1,pow=a;
    	while( b>0 )
    	{
    		if( (b&1)==1 )
    		{
    			res = (pow*res)%mod;
    		}
    		pow = (pow*pow)%mod;
    		b=b/2;
    	}
    	return res;
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

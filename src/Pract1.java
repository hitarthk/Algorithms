
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
public class Pract1 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int max=1000010;
        boolean[] b=new boolean[max+1];
        for(int i=2;i*i<=max;i++){
            if(!b[i]){
                for(int j=0;i*(i+j)<=max;j++){
                    b[i*(i+j)]=true;
                }
            }
        }
        
        int[] primes=new int[max+1];
        int numprimes = 0;
        int total=0;
        for(int i=2;i<=max;i++){
            if(!b[i]){
                primes[numprimes++]=i;
            }
        }
        //System.out.println(Arrays.toString(primes));
        HashSet<Long> h=new HashSet<>();
        for(int i=0;i<numprimes;i++){
            long p=primes[i];
            int pow=1;
            while(p<=1000000000){
                
                if(!b[pow+1])
                    h.add(p);
                p*=primes[i];
                //System.out.println(p);
                pow++;
            }
        }
        
        
        
        
        
        
        
        int t=in.nextInt();
        while(t>0){
            t--;
            int M=in.nextInt();
            int N=in.nextInt();
            boolean[] isprime = new boolean[200001];
            for (int j = 0; j < 200001; j++)
            {
                        isprime[j] = true;
            }

            for (int i = 0; i < numprimes; i++) 
            {
                int p = primes[i];
                int start;

                if (p >= M) start = p*2;
                else start = M + ((p - M % p)%p);

                for (int j = start; j <= N; j += p) 
                {
                       isprime[j - M] = false;
                }
            }
            int ans=0;
            //System.out.println("Here");
            for(int i=M;i<=N;i++){
                //System.out.println(i+" "+h.contains(i));
                if(isprime[i-M]|| h.contains(new Long(i))){
                    ans++;
                    //System.out.println(i);
                }
            }
            out.println(ans);

        }
        out.close();
        
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


import java.io.IOException;
import java.math.BigInteger;
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
public class c_326_d {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n;
        n=in.nextInt();
        long l=in.nextLong();
        long k=in.nextLong();
        long blocks=(long)Math.ceil(l/(n+0.0));
        
        
        long[][] dp;
        long mod=(long)1e9+7;
        int maxlen=0;
        if(blocks<=k){
            
            maxlen=(int)blocks;
            
        }
        else{
            
            maxlen=(int)k;
        }
        //System.out.println(maxlen);
        dp=new long[maxlen+1][n];
        //System.out.println((l%n));
        //System.out.println((l/n));
        int[] a=new int[n];
        Pair[] p=new Pair[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
            p[i]=new Pair(i,a[i]);
        }
        Arrays.sort(p);
        int[] indices=new int[n];
        for(int i=n-1;i>=0;){
            int j=i;
            while(j>=0 && a[p[j].index]==a[p[i].index]){
                indices[p[j].index]=i;
                j--;
            }
            i=j;
        }
        
        //System.out.println(Arrays.toString(indices));
        
        //System.out.println(Arrays.toString(nearest));
        //System.out.println(Arrays.toString(lessIndices));
        
        //System.out.println("maxlen "+maxlen);
        long[] sum=new long[n];
        for(int len=1;len<=maxlen;len++){
            for(int i=0;i<n;i++){
                if(len==1){
                    dp[len][i]=1;
                }
                else{
                    dp[len][i]=sum[indices[i]];
                }
                //System.out.println(len+" "+i+" "+dp[len][i]);
            }
            sum[0]=dp[len][p[0].index];
            for(int i=1;i<n;i++){
                sum[i]=(sum[i-1]+dp[len][p[i].index])%mod;
            }
            //System.out.println(Arrays.toString(sum));
        }
        
        long ans=0;
        long c=(long)Math.ceil(l/(n+0.0));
        //System.out.println("|||||||");
        for(int len=1;len<=maxlen;len++){
            for(int i=0;i<n;i++){
                long howmany=(l/n);
                if(i<l%n)
                    howmany++;
                //System.out.println(len+" "+i+" "+howmany);
                ans  = (ans + ((howmany-len+1)%mod*(dp[len][i]))%mod);
                ans-=ans>=mod? mod:0;
                //ans = (ans + new BigInteger(howmany-len+1+"").multiply(new BigInteger(dp[i][len]+"")).mod(new BigInteger(mod+"")).longValue())%mod;
            }
        }
//        
//        for(int i=(int)((l-1)%n) +1;i<n;i++){
//            for(int len=1;len<=k && len<=c-1;len++){
//                long howmany=(long)Math.ceil(l/(n+0.0));
//                
//                ans  = (ans + ((howmany-len)%mod*(dp[i][len]%mod))%mod)%mod;
//                //ans = (ans + new BigInteger(howmany-len+1+"").multiply(new BigInteger(dp[i][len]+"")).mod(new BigInteger(mod+"")).longValue())%mod;
//            }
//        }
        
        
        
        System.out.println(ans);
        
    }
    
    static class Pair implements Comparable<Pair>{
        int index;
        int v;
        
        public Pair(int index,int v){
            this.index=index;
            this.v=v;
        }
        
        @Override
        public int compareTo(Pair o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return this.v-o.v;
        }
        
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

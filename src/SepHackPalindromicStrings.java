
import java.io.IOException;
import java.io.PrintWriter;
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
public class SepHackPalindromicStrings {
    
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        while(t>0){
            t--;
            String s=in.nextLine();
            int n=s.length();
            long[][][][] dp=new long[n][26][26][4];
            
            int c=s.charAt(0)-97;
            long ans=0;
            for(int k=0;k<26;k++){
                dp[0][c][k][2]++;
                dp[0][k][c][1]++;
            }
            ans++;
            for(int i=1;i<n;i++){
                c=s.charAt(i)-97;
                for(int j=0;j<26;j++){
                    for(int k=0;k<26;k++){
                        for(int l=0;l<3;l++){
                            if(l==0){
                                if(j!=c && k!=c){
                                    dp[i][j][k][0]=dp[i-1][j][k][0];
                                }
                                if(j==c && k!=c){
                                    dp[i][j][k][0]=dp[i-1][j][k][2];
                                }
                                if(j!=c && k==c){
                                    dp[i][j][k][0]=dp[i-1][j][k][1];
                                }
                                if(j==c&& k==c){
                                    dp[i][j][k][0]=dp[i-1][j][k][0];
                                }
                                ans+=dp[i][j][k][0];
                            }
                            //0 both even
                            // 1 even odd
                            //2 odd even
                            // 3 odd odd
                            if(l==1){
                                if(j!=c && k!=c){
                                    dp[i][j][k][1]=dp[i-1][c][k][3];
                                }
                                if(j==c && k!=c){
                                    dp[i][j][k][1]=dp[i-1][c][k][3];
                                }
                                if(j!=c && k==c){
                                    dp[i][j][k][1]=dp[i-1][j][c][1];
                                }
                                if((j==c && k==c)||(j==k)){
                                    dp[i][j][k][1]=0;
                                }
                                ans+=dp[i][j][k][1];
                            }
                            if(l==2){
                                
                                if(j!=c && k!=c){
                                    dp[i][j][k][2]=dp[i-1][j][c][3];
                                }
                                if(j==c && k!=c){
                                    dp[i][j][k][2]=dp[i-1][c][k][0];
                                }
                                if(j!=c && k==c){
                                    dp[i][j][k][2]=dp[i-1][j][c][3];
                                }
                                if((j==c&& k==c)|| (j==k)){
                                    dp[i][j][k][2]=0;
                                }
                                
                                ans+=dp[i][j][k][2];
                            }
                            //0 both even
                            // 1 even odd
                            //2 odd even
                            // 3 odd odd
                            if(l==3){
                                if(j==k){
                                    if(j==c){
                                        dp[i][j][k][3]=dp[i-1][c][c][0];
                                    }
                                    else{
                                        dp[i][j][k][3]=dp[i-1][j][c][2];
                                    }
                                }
                                if(j!=c && k!=c){
                                    dp[i][j][k][3]=dp[i-1][j][c][2]+dp[i-1][c][k][1];
                                }
                                if(j==c && k!=c){
                                    dp[i][j][k][3]=dp[i-1][c][k][1];
                                }
                                if(j!=c && k==c){
                                    dp[i][j][k][3]=dp[i-1][j][c][2];
                                }
                            }
                        }
                    }
                }
            }
            long total=((n)*(n+1))/2;
            out.println(ans+"/"+total);
            long g=gcd(ans,total);
            ans/=g;
            total/=g;
            
        }
        out.close();
    }
    
    static long gcd(long a,long b){
        if(a<b){
            long temp=a;
            a=b;
            b=temp;
        }
        while(b>0){
            long temp=a%b;
            a=b;
            b=temp;
        }
        return a;
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


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
class NovemberChallengeSimpleSum {
    public static void main(String[] args) {
        
        FasterScanner in=new FasterScanner();
        int max=(int)1e7;
        int[] p=new int[max+1];
        for(int i=2;i*i<=max;i++){
            if(p[i]==0)
            for(int j=0;i*(i+j)<=max;j++){
                p[i*(i+j)]=i;
            }
        }
        
        int[] div=new int[max+1];
        div[1]=1;
        for(int i=2;i<=max;i++){
            if(p[i]==0){
                div[i]=2;
            }
            else{
                int count=0;
                int temp=i;
                int prime=p[i];
                while(temp%prime==0){
                    count++;
                    temp/=prime;
                }
                div[i]=div[temp]*(count+1);
            }
        }
        
        long[] ans=new long[max+1];
        
        ans[1]=1;
        
        for(int i=2;i<=max;i++){
            if(p[i]==0){
                ans[i]=1+(long)i*(i-1);
            }
            else{
                int count=0;
                int temp=i;
                while(temp%p[i]==0){
                    count++;
                    temp/=p[i];
                }
                long seriessum=(long)(p[i]*(pow(2*count,p[i])))/(p[i]*p[i]-1);
                //System.out.println(i+" "+seriessum+" "+count+" "+pow(2*count,p[i]));
                ans[i]=ans[temp]*(1+(long)((p[i]-1)*seriessum));
            }
            //System.out.println(i+" "+ans[i]);
        }
        
        int t=in.nextInt();
        PrintWriter out=new PrintWriter(System.out);
        while(t-->0){
            int n=in.nextInt();
            out.println(ans[n]);
        }
        out.close();
        
    }
    
    static long pow(int pow,long b){
        if(pow==0){
            return 1;
        }
        else{
            long ans=pow(pow/2,b);
            ans=ans*ans;
            if((pow&1)!=0){
                ans*=b;
            }
            return ans;
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

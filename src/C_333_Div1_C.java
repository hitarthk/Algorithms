
import java.io.IOException;
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
public class C_333_Div1_C {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int m=in.nextInt();
        int[] g=new int[n+1];
        int sum=0;
        for(int i=1;i<=n;i++){
            g[i]=in.nextInt();
            sum+=g[i];
        }
        if(m==1){
            System.out.println(1);
            return;
        }
        double[] p=new double[n*m+1];
        double[] s=new double[n*m+1];
        double div=1/(m-1.0);
        p[0]=1;
        s[0]=1;
        for(int j=1;j<=n*m;j++){
            s[j]=s[j-1]+p[j];
        }
        for(int i=1;i<=n;i++){
            double[] temp=new double[p.length];
            for(int j=1;j<=n*m;j++){
                if(j<=m){
                    temp[j]+=s[j-1];
                    if(j-g[i]>=0){
                        temp[j]-=p[j-g[i]];
                    }
                }
                else{
                    temp[j]+=s[j-1]-s[j-m-1];
                    temp[j]-=p[j-g[i]];
                }
                temp[j]*=div;
            }
            double[] stemp=new double[temp.length];
            stemp[0]=temp[0];
            for(int j=1;j<=n*m;j++){
                stemp[j]=stemp[j-1]+temp[j];
            }
            //System.out.println("temp "+Arrays.toString(temp));
            //System.out.println("stemp "+Arrays.toString(stemp));
            s=stemp;
            p=temp;
        }
        //System.out.println(Arrays.toString(s));
        double ans=s[sum-1];
        ans*=m-1;
        ans+=1;
        System.out.println(ans);
        
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

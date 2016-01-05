
import java.io.IOException;
import java.util.ArrayList;
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
public class HourCode3 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int[] a=in.nextIntArray(n);
        int max=1000000;
        int[] b=new int[max+1];
        for(int i=2;i*i<=max;i++){
            if(b[i]==0){
                for(int j=0;i*(i+j)<=max;j++){
                    b[i*(i+j)]|=1;
                }
            }
        }
        for(int i=2;i<=max;i++){
            b[i]=b[i-1]+(b[i]^1);
        }
        //System.out.println(Arrays.toString(b));
        HashMap<HashSet<Integer>,Long> h=new HashMap<>();
        long hash=0;
        long ans=0;
        int[] arr=new int[max+1];
        long mod=(long)1e18+3;
        
        HashSet<Integer> cur=new HashSet<>();
        h.put(cur, 1l);
        for(int i=0;i<n;i++){
            HashSet<Integer> get=fact(a[i]);
            //System.out.println(cur);
            for(Integer x:get){
                if(cur.contains(x)){
                    cur.remove(x);
                }
                else{
                    cur.add(x);
                }
            }
            //System.out.println(Arrays.toString(arr)+" "+hash);
            if(h.get(cur)==null){
                h.put(cur, 1l);
            }
            else{
                long t=h.get(cur);
                ans+=t;
                h.put(cur, t+1);
            }
        }
        //System.out.println(h);
        System.out.println(ans);
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
    
    static HashSet<Integer> fact(int n){
        HashSet<Integer> h=new HashSet<>();
        for(int i=2;i*i<=n;i++){
            int p=0;
            while(n%i==0){
                n=n/i;
                p=(p+1)&1;
            }
            if(p>0){
                h.add(i);
            }
        }
        if(n>1){
            h.add(n);
        }
        return h;
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

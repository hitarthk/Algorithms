
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
class ICPC2 {
    static long[][] dp;
    static int[] ceiling;
    static int[] floor;
    static int[] higher;
    static int[] lower;
    static ArrayList<Integer> l=new ArrayList<>();
    static TreeSet<Integer> t=new TreeSet<>();
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int max=1100000;
        boolean[] b=new boolean[max+1];
        int n=in.nextInt();
        if(n==0){
            System.out.println(0);
            System.exit(0);
        }
        for(int i=2;i*i<=max;i++){
            if(!b[i]){
                for(int j=0;i*(i+j)<=max;j++){
                    b[i*(i+j)]=true;
                }
            }
        }
        ceiling=new int[max+1];
        floor=new int[max+1];
        t=new TreeSet<>();
        
        for(int i=2;i<=1000103;i++){
            if(!b[i]){
                t.add(i);
                l.add(i);
            }
        }
        ceiling[1]=2;
        int p=0;
        for(int i=2;i<=1000003;i++){
            if((i>l.get(p)))
                p++;
            ceiling[i]=l.get(p);
            //System.out.println(i+" "+l.get(ceiling[i]));
        }
//        floor[1]=-1;
//        p=0;
//        for(int i=2;i<=1000003;i++){
//            floor[i]=l.get(p);
//            if(i==l.get(p)){
//                p++;
//            }
//        }
        lower=new int[max+1];
        higher=new int[max+1];
        p=0;
        lower[1]=-1;
        lower[2]=-1;
        for(int i=3;i<=1000003;i++){
            lower[i]=l.get(p);
            if(i==l.get(p+1))
                p++;
        }
        
        higher[1]=2;
        p=1;
        
        for(int i=2;i<=1000003;i++){
            if(l.get(p)==i)
                p++;
            higher[i]=l.get(p);
        }
        
//        for( int i=1 ; i<1000 ; i++ )
//        {
//            System.out.println(i+" "+lower[i]+" "+floor[i]+" "+ceiling[i]+" "+higher[i]);
//                    
//        }
        
        //System.out.println(t.last());
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
        }
        dp=new long[n+2][n+2];
        int[][] rem=new int[n+2][n+2];
        for(int length=1;length<=n;length+=2){
            for(int i=1;i+length<=n;i++){
                if(length==1){
                    int m=Math.max(a[i], a[i+length]);
                    dp[i][i+length]=cost(a[i], a[i+length]);
                    
                }
                else{
                long min=Long.MAX_VALUE;
                for(int j=i+length-1;j>=i;j-=2){
                    //System.out.println(i+" "+j+" "+length);
                    
                    long temp=ans(j+1,i+length-1)+ans(i,j-1)+cost(a[j],a[i+length]);
                    //System.out.println(temp);
                    if(temp<min){
                        min=temp;
                    }
                }
                dp[i][i+length]=min;
                }
                //System.out.println(i+" "+(i+length)+" "+dp[i][i+length]);
            }
        }
        System.out.println(dp[1][n]);
    }
    
    static long cost(int x,int y){
        int c=0;
        if(x<y){
            
            int yc=(ceiling[y]);
            if(yc>2)
            {
                if( lower[yc]<x )
                {
                    c+=yc-x;
                    c+=higher[yc]-y;
                }
                else
                {
                    c+=yc-y;
                    c+=lower[yc]-x;
                }
                
            }
            else{
                c+=2;
            }
        }
        else if(x==y){
            
            int yc=ceiling[y];
            //c+=t.higher(yc)-y;
            c+=higher[yc]-y;
            c+=yc-x;
        }
        else{
            //int xc=t.ceiling(x);
            int xc=ceiling[x];
            c+=xc-x;
            //c+=t.higher(xc)-y;
            c+=higher[xc]-y;
        }
        return c+1;
    }
    
    static int f(int x,int y){
        int max=Math.max(x, y);
        int min=Math.min(x, y);
        boolean maxc=t.contains(max);
        boolean minc=t.contains(min);
        if(maxc){
            return 1+t.higher(max)-max+t.floor(max)-min;
        }
        else if(!maxc && !minc){
            int fl=t.ceiling(min);
            int fh=t.ceiling(max);
            if(fl==fh){
                return 1+fl-min+t.higher(fh);
            }
            else{
                return 1+t.floor(max)-min+t.higher(max)-max;
            }
        }
        else{
            int fh=t.floor(max);
            if(fh==min){
                return 1+t.higher(max)-max;
            }
            else{
                return 1+t.higher(max)-max+t.floor(max)-min;
            }
        }
    }
    
    static long ans(int i,int j){
        if(i>=j)
            return 0;
        else
            return dp[i][j];
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

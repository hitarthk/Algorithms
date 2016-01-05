/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class UncompressString {
    static ArrayList<Seg> arr=new ArrayList<Seg>();
    static long ans=0;
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        StringBuilder s=new StringBuilder(in.nextLine());
        s.append("1");
        s.insert(0, ' ');
        StringBuilder temp=new StringBuilder();
        
        Seg seg=new Seg();
        seg.length=1;
        arr.add(seg);
        long oldMul=1;
        long oldLength=0;
        int j=0;
        int oldI=1;
        long[] cf=new long[26];
        
        ArrayList<Long[]> f=new ArrayList<>();
        
        f.add(new Long[26]);
        Arrays.fill(f.get(0), new Long(0));
        //System.out.println(Arrays.toString(f.get(0)));
        for(int i=1;i<s.length();i++){
            
            if(s.charAt(i)>=48 && s.charAt(i)<=58){
                long mul=1;
                int newI=i;
                while(i<s.length() && s.charAt(i)>=48 && s.charAt(i)<=58){
                    mul *= (s.charAt(i)-48);
                    i++;
                }
                
                i--;
                Seg segment=new Seg();
                segment.s=temp;
                for(int k=0;k<26;k++){
                    cf[k]*=mul;
                }
                for(int k=0;k<26;k++){
                    cf[k]+=mul*f.get(f.size()-1)[k];
                }
                //System.out.println(Arrays.toString(f.get(f.size()-1)));
                segment.f=f;
                segment.cf=cf.clone();
                segment.end=(newI-oldI)*mul +oldLength;
                segment.x=mul;
                segment.length=(newI-oldI);
                //System.out.println("Segment "+(arr.size())+" "+Arrays.toString(segment.cf)+" "+segment.length+" "+segment.x);
                arr.add(segment);
                f=new ArrayList<Long[]>();
                f.add(new Long[26]);
                Arrays.fill(f.get(0),new Long(0));
                oldMul=mul;
                oldI=i+1;
                oldLength=segment.end;
                temp=new StringBuilder();
            }
            else{
                int c=s.charAt(i)-'a';
                temp.append(s.charAt(i));
                f.add( f.get(f.size()-1));
                f.get(f.size()-1)[c]++;
                
            }
        }
        //System.out.println("|||||||||");
        int q=in.nextInt();
//        System.out.println(arr.size());
//        for(int i=0;i<arr.size();i++){
//            //System.out.println(i+" "+Arrays.toString(arr.get(i).cf)+" "+arr.get(i).length+" "+arr.get(i).x);
//        }
        while(q>0){
            q--;
            char ch=in.nextString().charAt(0);
            int c=ch-'a';
            long a=in.nextLong()-1;
            long b=in.nextLong();
            //System.out.println(a+" "+b+" "+ch);
//            fb+= (b/oldMul)*arr.get(arr.size()-1).cf[(int)c-'a'];
//            b=b%oldMul;
            ans=0;
            func(b,c);
            long fb=ans;
            //System.out.println("fb "+fb);
            ans=0;
            long fa;
            if(a==0){
                fa=0;
            }
            else{
                func(a,c);
                fa=ans;
            }
            
            //System.out.println("fa "+fa);
            System.out.println((fb-fa));
        }
        
    }
            
    static void func(long v,int c){
        //System.out.println("v"+v);
        if(v==0){
            return;
        }
        int i=1;
        long dividend=0;
        boolean check=true;
        
        while(check){
            Seg seg=arr.get(i);
            dividend+=seg.length;
            //System.out.println("Dividend"+dividend);
            long div=v/dividend;
            if(div<=seg.x){
                
//                System.out.println("arr.get(i-1).cf[c]"+ (arr.get(i-1).cf[c])+" "+i);
//                System.out.println("seg.f.get(seg.f.size()-1)[c] "+(seg.f.get(seg.f.size()-1)[c]));
                ans += div*(arr.get(i-1).cf[c]+seg.f.get(seg.f.size()-1)[c]);
                //System.out.println("Ans "+ans );
                long rem=v%dividend;
                if(rem>(dividend-seg.length)){
                    rem=dividend-seg.length;
                    ans += seg.f.get((int)rem)[c];
                    //System.out.println("Again ans "+ans);
                    return;
                }
                else{
                    func(rem,c);
                    return;
                }
            }
            dividend*=seg.x;
            i++;
        }
        
        
    }
    
    static class Seg{
        StringBuilder s=new StringBuilder();
        long[] cf=new long[26];
        ArrayList<Long[]> f=new ArrayList<>();
        long end;
        long x;
        long length;
    }
    
    
     public static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
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
//            System.out.println("Dividend "+dividend+" "+div);
//            if(div<seg.x){
//                //System.out.println("Gere");
//                tans = (tans + seg.f.get(seg.f.size()-1)[c])*div;
//                //System.out.println("tans"+ tans);
//                long rem=v%dividend;
//                if(rem>=dividend-seg.length){
//                tans += arr.get(i-1).cf[c];
//                    System.out.println("rem-dividend+seg.length "+(rem-dividend+seg.length));
//                tans += seg.f.get((int)(rem-dividend+seg.length))[c];
//                    System.out.println("In rem> if tans "+tans);
//                ans += tans;
//                return;
//                }
//                
//                else if(rem==0){
//                    ans += tans;
//                    return;
//                }
//                
//                else{
//                    System.out.println(rem);
//                    System.out.println();
//                    func(rem,c);
//                    check=false;
//                }
//                    
//                
//            }
//            else{
//                tans += seg.f.get(seg.f.size()-1)[c]*seg.x;
//                System.out.println("In elese tans"+tans);
//                //ans+=tans;
//            }

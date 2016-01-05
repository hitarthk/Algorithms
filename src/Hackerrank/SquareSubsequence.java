///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Hackerrank;
//
//import java.io.IOException;
//import java.util.InputMismatchException;
//
///**
// *
// * @author hitarthk
// */
//public class SquareSubsequence {
//    static long m=(long)10e9 + 7;
//    static int n;
//    static long ans;
//    static String s;
//    public static void main(String[] args) {
//        FasterScanner in=new FasterScanner();
//        int t=in.nextInt();
//        while(t>0){
//            t--;
//            s=in.nextLine();
//            n=s.length();
//            ans=0;
//            s=" "+s;
//            int l=1;
//            int r=n;
//        }
//    }
//    
//    public static void gen(int l,int r){
//        int m=(l+r)/2;
//        ans = (ans+acs(l,m,m+1,r))%m;
//        gen(l,m-1);
//        gen(m+1,r);
//    }
//    
//    public static long acs(int l1,int r1,int l2,int r2){
//        long[][] arr=new long[r1-l1+2][r2-l2+2];
////        for(int i=l1;i<=r1;i++){
////            if(s.charAt(i)==s.charAt(l2)){
////                arr[i][1]=1;
////            }
////        }
////        for(int i=l2;i<=r2;i++){
////            if(s.charAt(i)==s.charAt(l1)){
////                arr[1][i]=
////            }
////        }
//        arr[0][0]=1;
//        for(int i=0;i<arr.length;i++){
//            arr[i][0]=1;
//        }
//        for(int i=0;i<=arr[0].length;i++){
//            arr[0][i]=1;
//        }
//        
//        for(int i=l1;i<=r1;i++){
//            for(int j=l2;j<=r2;j++){
//                int di=i-l1+1;
//                int dj=j-l2+1;
//                if(s.charAt(i)==s.charAt(j)){
//                    arr[di][dj]=(2*arr[di-1][dj-1])%m;
//                }
//                else{
//                    arr[di][dj]= (arr[di-1][dj]+(arr[di][dj-1]-arr[di-1][dj-1]))
//                }
//            }
//        }
//        
//    }
//    
//    
//    public static class FasterScanner {
//        private byte[] buf = new byte[1024];
//        private int curChar;
//        private int numChars;
//
//        public int read() {
//            if (numChars == -1)
//                throw new InputMismatchException();
//            if (curChar >= numChars) {
//                curChar = 0;
//                try {
//                    numChars = System.in.read(buf);
//                } catch (IOException e) {
//                    throw new InputMismatchException();
//                }
//                if (numChars <= 0)
//                    return -1;
//            }
//            return buf[curChar++];
//        }
//
//        public String nextLine() {
//            int c = read();
//            while (isSpaceChar(c))
//                c = read();
//            StringBuilder res = new StringBuilder();
//            do {
//                res.appendCodePoint(c);
//                c = read();
//            } while (!isEndOfLine(c));
//            return res.toString();
//        }
//
//        public String nextString() {
//            int c = read();
//            while (isSpaceChar(c))
//                c = read();
//            StringBuilder res = new StringBuilder();
//            do {
//                res.appendCodePoint(c);
//                c = read();
//            } while (!isSpaceChar(c));
//            return res.toString();
//        }
//
//        public long nextLong() {
//            int c = read();
//            while (isSpaceChar(c))
//                c = read();
//            int sgn = 1;
//            if (c == '-') {
//                sgn = -1;
//                c = read();
//            }
//            long res = 0;
//            do {
//                if (c < '0' || c > '9')
//                    throw new InputMismatchException();
//                res *= 10;
//                res += c - '0';
//                c = read();
//            } while (!isSpaceChar(c));
//            return res * sgn;
//        }
//
//        public int nextInt() {
//            int c = read();
//            while (isSpaceChar(c))
//                c = read();
//            int sgn = 1;
//            if (c == '-') {
//                sgn = -1;
//                c = read();
//            }
//            int res = 0;
//            do {
//                if (c < '0' || c > '9')
//                    throw new InputMismatchException();
//                res *= 10;
//                res += c - '0';
//                c = read();
//            } while (!isSpaceChar(c));
//            return res * sgn;
//        }
//            
//        public int[] nextIntArray(int n) {
//            int[] arr = new int[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = nextInt();
//            }
//            return arr;
//        }
//        
//        public long[] nextLongArray(int n) {
//            long[] arr = new long[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = nextLong();
//            }
//            return arr;
//        }
//
//        private boolean isSpaceChar(int c) {
//            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
//        }
//
//        private boolean isEndOfLine(int c) {
//            return c == '\n' || c == '\r' || c == -1;
//        }
//    }
//}

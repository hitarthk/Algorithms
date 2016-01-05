/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C_316;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.*;
/**
 *
 * @author hitarthk
 */
public class C{
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int m=in.nextInt();
        String s=in.nextLine();
        char[] arr=new char[n+2];
        arr[0]='-';
        arr[n+1]='-';
        for(int i=0;i<n;i++)
		{
			arr[i+1]=s.charAt(i);	
		}
//        int[] l=new int[s.length()];
//        int r[] =new int[s.length()];
//        Arrays.fill(l, -1);
//        Arrays.fill(r, -1);
//        if(s.charAt(0)=='.'){
//            l[0]=0;
//        }
//        for(int i=1;i<s.length();i++){
//            if(s.charAt(i)=='.'){
//                if(s.charAt(i-1)=='.'){
//                    l[i]=l[i-1];
//                }
//                else{
//                    l[i]=i;
//                }
//            }
//            
//        }
//        if(s.charAt(s.length()-1)=='.'){
//            r[s.length()-1]=s.length()-1;
//        }
//        for(int i=s.length()-2;i>=0;i--){
//            if(s.charAt(i)=='.'){
//                if(s.charAt(i+1)=='.'){
//                    r[i]=r[i+1];
//                }
//                else{
//                    r[i]=i;
//                }
//            }
//        }
//        int ans=0;
//        for(int i=0;i<n;i++){
//            if(s.charAt(i)=='.'){
//                int j=r[i];
//                ans += (j-i+1);
//                i=j;
//            }
//        }
        int ans=0;
        for(int i=1;i<n;i++)
		{
			if(arr[i]=='.' && arr[i+1]=='.')
			{
				ans++;
			}
		}
        //System.out.println("Pelo "+ans);
        while(m>0){
            m--;
            String[] q=in.nextLine().split(" ");
            
            int x=Integer.parseInt(q[0]);
//            if(s.charAt(p)!=q[1].charAt(0) && q[1].charAt(0)=='.'){
//                int left=p;
//                int right=p;
//                if(p>0 && s.charAt(p)=='.'){
//                    if(s.charAt(p-1)=='.'){
//                        left=l[p-1];
//                    }
//                }
//                if(p<n-1 && s.charAt(p)=='.'){
//                    if(s.charAt(p+1)=='.'){
//                        right=r[p+1];
//                    }
//                }
//                ans += (right-left);
//            }
            char c=q[1].charAt(0);
            if(arr[x]=='.' && arr[x-1]=='.')
			{
				ans--;
			}
			if(arr[x]=='.' && arr[x+1]=='.')
			{
				ans--;
			}
			arr[x]=c;
			if(arr[x]=='.' && arr[x-1]=='.')
			{
				ans++;
			}
			if(arr[x]=='.' && arr[x+1]=='.')
			{
				ans++;
			}
                        //arr[x]=c;
            System.out.println(ans);
        }
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

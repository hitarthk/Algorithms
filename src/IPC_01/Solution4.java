/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IPC_01;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class Solution4 {
    static int[] seg=new int[4*1000001];
    static int[] dp=new int[1000001];
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        int n=in.nextInt();
        int[] q=new int[n];
        for(int i=0;i<n;i++){
            q[i]=in.nextInt();
        }
        build(1,0,n);
        update(1,n-1,1,0,n);
        for(int i=n-2;i>=0;i--){
            //int ans=query(0,i+1,i+q[i]+1,0,n);
            //System.out.println(ans+1);
            update(1,i,1+query(1,i+1,Math.min(i+q[i]+1,n),0,n),0,n);
        }
        System.out.println(dp[0]);
    }
    
    
    
    public static void build(int id,int l,int r){
		if(r-l<2){
			seg[id]=dp[l];
			return;
		}
		int mid=(l+r)/2;
		build(2*id,l,mid);
		build(2*id+1,mid,r);
		seg[id]=Math.min(seg[2*id],seg[2*id+1]);
    }
    public static int query(int id,int x,int y,int l,int r){
		if(x<=l && y>=r){
			return seg[id];
		}
		if(y<=l || r<=x){
			return 1000000;
		}
		int mid=(l+r)/2;
		return Math.min(query(2*id, x, y, l, mid),query(2*id+1, x, y, mid, r));
    }
    public static int update(int id,int p,int value,int l,int r){
		if(r-l<2){
			dp[p]=value;
			seg[id]=value;
			return value;
		}
		int mid=(l+r)/2;
		if(p<mid){
		update(2*id, p, value, l, mid);
		seg[id]=Math.min(seg[2*id], seg[2*id+1]);
		return seg[id];
		}
		else{
			update(2*id+1, p, value, mid,r);
			seg[id]=Math.min(seg[2*id+1], seg[2*id]);
			return seg[id];
				
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

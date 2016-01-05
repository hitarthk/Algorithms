package Hackerrank;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Solution3 {

	static int n;  
	static int t[];
	static int count[];
	public static void main(String[] args) {
    	FasterScanner s=new FasterScanner();
    	n=s.nextInt();
    	int nQueries=s.nextInt();
    	count=new int[2*n];
	    t=new int[2*n];
	    for(int i=n;i<2*n;i++)
	    	t[i]=s.nextInt();

	    construct();

	    for(int i=0;i<nQueries;i++){
                int l=s.nextInt();
                int r=s.nextInt();
            
	    	System.out.println(q(l-1,r));
            }
    }
    
    
	static void construct() { 
		for(int i=n;i<2*n;i++)
			count[i]=1;
		
		for (int i = n - 1; i > 0; --i)
		{
			if(t[i<<1]>t[i<<1|1])
			{
				t[i]=t[i<<1];
				count[i]=count[i<<1];
			}
			else if(t[i<<1]<t[i<<1|1])
			{
				t[i]=t[i<<1|1];
				count[i]=count[i<<1|1];
			}
			else
			{
				t[i]=t[i<<1|1];
				count[i]=count[i<<1|1]+count[i<<1];
			}
		}
	}

	void modify(int p, int value) {  
	  for (t[p += n] = value; p > 1; p >>= 1) t[p>>1] = t[p] + t[p^1];
	}

	static int q(int l, int r) { 
	  int res = 0;
	  int max=-1111111;
	  int l2=l,r2=r;
			  
	  for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
		    if ((l&1)!=0) max =Math.max(max, t[l++]);
		    if ((r&1)!=0) max =Math.max(max, t[--r]);
		  }
//	  System.out.println(max);
	  l=l2;
	  r=r2;
	  for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
	    if ((l&1)!=0 ){
	    	if(t[l]==max)
	    		res += count[l++];
	    	else
	    		l++;
	    }
	    if ((r&1)!=0){
	    	if(t[r-1]==max)
	    	res += count[--r];
	    	else
	    		r--;
	    }
	  }
	  return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	























//
//	static class Node implements Comparable<Node>
//    {
//    	long ind;
//    	long count=0;
//    	long val=0;
//    	long countLeft=0;
//    	long countRight=0;
//    	Node(long v,long c)
//    	{
//    		ind=index++;
//    		count=c;
//    		val=v;
//    	}
//		public int compareTo(Node o) {
//			return (int) (val-o.val);
//			
//		}
//    	
//    }
//    
    
    
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
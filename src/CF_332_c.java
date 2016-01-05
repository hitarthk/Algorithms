import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

public class CF_332_c {
	public static int mod=(int)1e9+7;
	
	
	
	public static void main(String[] args)
	{
    	
		FasterScanner s=new FasterScanner();
		PrintWriter out=new PrintWriter(System.out);
		
		int n=s.nextInt();
		long h[] = s.nextLongArray(n);
		long[] min=new long[n];
		boolean[] ismin=new boolean[n];
		min[n-1]=h[n-1];
		ismin[n-1]=true;
		int count=0;
		for(int i=n-2;i>=0;i--)
		{
			min[i]=Math.min(min[i+1], h[i]);
		}
		for(int i=0;i<n;i++)
		{
			long max=h[i];
			i++;
			while(i<n && min[i]<max)
			{
				max=Math.max(max, h[i]);
				i++;
			}
			count++;

			if(i==n)
				break;
			i--;
		}
		System.out.println(count);
	
		out.close();
	}
	

	public static int pow(int x, int n, int mod) {
		int res = 1;
		for (long p = x; n > 0; n >>= 1, p = (p * p) % mod) {
			if ((n & 1) != 0) {
				res = (int) (res * p % mod);
			}
		}
		return res;
	}
    public static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int tmp_curChar;
        private int tmp_numChars;

        public int read() {
            if (tmp_numChars == -1)
                throw new InputMismatchException();
            if (tmp_curChar >= tmp_numChars) {
                tmp_curChar = 0;
                try {
                    tmp_numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (tmp_numChars <= 0)
                    return -1;
            }
            return buf[tmp_curChar++];
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
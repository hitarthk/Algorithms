/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C_316;

/**
 *
 * @author hitarthk
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.TreeSet;

public class Solution 
{
	public static void main(String[] args) {
		new Solution().solve();
	}

	PrintWriter out;

	//    long mod=1000000007L ;
	public void solve()
	{
		out=new PrintWriter(System.out);
		int n=in.nextInt();
		int m=in.nextInt();;
		String s=in.nextLine();
		char [] arr=new char[s.length()+2];
		for(int i=0;i<n;i++)
		{
			arr[i+1]=s.charAt(i);	
		}
		arr[0]='a';
		arr[arr.length-1]='a';
		int ans=0;
		for(int i=1;i<n;i++)
		{
			if(arr[i]=='.' && arr[i+1]=='.')
			{
				ans++;
			}
		}
		for(int i=0;i<m;i++)
		{
			int x=in.nextInt();
			char c=in.nextLine().charAt(0);
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
			out.println(ans);
		}
		out.close();
	}

	class s implements Comparable<s>
	{
		int val;
		long freq;
		public s(int v,long f)
		{
			this.val=v;
			this.freq=f;
		}
		@Override
		public int compareTo(s arg0) {
			// TODO Auto-generated method stub
			return this.val-arg0.val;

		}

		public String toString()
		{
			return "( "+this.val+" "+this.freq+" )";
		}
		public boolean equals(Object o)
		{
			if(o==null)
			{
				return false;
			}
			else
			{
				try
				{
					s oo=(s) o;
					if(oo.val==this.val)
					{
						return true;
					}
					else 
					{
						return false;
					}
				}
				catch(Exception e)
				{
					return false;
				}
			}
		}

	}

	public static int gcd(int a, int b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
	}



	FasterScanner in=new FasterScanner();

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



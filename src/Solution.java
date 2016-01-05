import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.text.Segment;



public class Solution 
{
	public static void main(String[] args) {
		//      FasterScanner in=new FasterScanner();
		//      PrintWriter out=new PrintWriter(System.out);
		//      int t=in.nextInt();
		//      for(int ii=0;ii<t;ii++)
		//      {
		new Solution().solve();
		//      }//System.out.println(Long.MIN_VALUE);
		//      out.close();

	}
	public Solution()
	{
		in=new FasterScanner();
		out=new PrintWriter(System.out);
	}

	//    public Solution(FasterScanner in,PrintWriter out)
	//    {
	//        this.in=in;
	//        this.out=out;
	//    }

	FasterScanner in;
	//Scanner in;
	PrintWriter out;
	//  segmentTree t;
	//  segmentTree c;
	long mod=1000000007L ;
	//  long[] arr;
	//  Hashtable<Long, Integer> table;
	//  int d;
	//  int[] count;
	//  int n;
	public void solve()
	{
		amod[0]=1;
		for(int i=1;i<amod.length;i++)
		{
			amod[i]=amod[i-1]*10;
			amod[i]%=mod;
		}
		
		int n=in.nextInt();
		
		long[] arr=new long[n];
		int[] len=new int[n];
		for(int i=0;i<n;i++)
		{	
			String xx=in.nextString();
			len[i]=xx.length();
			arr[i]=Long.parseLong(xx);
			arr[i]%=mod;
		}
		Node tree=new Node(0,n-1,arr,len);
		int q=in.nextInt();
		for(int i=0;i<q;i++)
		{
			int qq=in.nextInt();
			if(qq==1)
			{
				int ii=in.nextInt()-1;
				String p=in.nextLine();
				tree.update(ii,Long.parseLong(p)%mod,p.length());
			}
			else
			{
				int l=in.nextInt()-1;
				int r=in.nextInt()-1;
				sss a=tree.query(l, r);
				out.println(a.val);
			}
					
		}
					
		//long[] arr=in.nextLongArray(n);

		out.close();
	}

	long[] amod=new long[1800001];


	public class Node
	{
		int start;
		int stop;
		Node left;
		Node right;
		long val;
		int len;

		public Node(int start,int stop,long[] arr,int[] len)
		{
			this.start=start;
			this.stop=stop;
			if(this.start==this.stop)
			{
				this.val=arr[start];
				this.len=len[start];
			}
			else
			{
				left=new Node(start,(start+stop)/2,arr,len);
				right=new Node((start+stop)/2+1,stop,arr,len);
				merge(left,right);
			}
			
//			System.out.print(" this.start "+this.start);
//			System.out.print(" this.stop "+this.stop);
//			System.out.print(" this.val "+this.val);
//			System.out.print(" this.len "+this.len);
//			System.out.println();
			
		}

		public void merge(Node l,Node r)
		{
			this.len=l.len+r.len;
			this.val=l.val*amod[r.len];
			this.val%=mod;
			this.val+=r.val;
			this.val%=mod;
		}

		public void update(int idx,long v,int length)
		{
			if(this.start==this.stop)
			{
				if(this.start==idx)
				{
					this.val=v;
					this.len=length;
				}
			}
			else
			{
				if(this.start<=idx && this.stop>=idx)
				{
					left.update(idx, v,length);
					right.update(idx, v,length);
					merge(left,right);
				}
			}

		}

		public sss query(int l,int r)
		{
			if(l>r)
				return null;
			if(this.start>r || this.stop<l)
			{
				return null;
			}
			if(this.start==this.stop)
			{
				if(l<=this.start && this.start<=r)
				{
					sss a=new sss();
					a.val=this.val;
					a.len=this.len;
					return a;
				}
				else
				{
					return null;
				}
			}
			else if(this.start>=l && this.stop<=r)
			{
				sss a=new sss();
				a.val=this.val;
				a.len=this.len;
				return a;
			}
			else
			{
				sss a1=left.query(l, r);
				sss a2=right.query(l, r);
				sss a=m(a1,a2);
				return a;
			}
		}

		public sss m(sss a1,sss a2)
		{
			if(a1==null && a2==null)
				return null;
			if(a1==null && a2!=null)
				return a2;
			if(a1!=null && a2==null)
				return a1;

			sss a=new sss();
			a.len=a1.len+a2.len;
			a.val=amod[a2.len]*a1.val;
			a.val%=mod;
			a.val+=a2.val;
			a.val%=mod;
			return a;
		}

		//		public int lazyUpdate(int l,int r)
		//		{
			//			if(l>r)
				//			{
				//				return 0;
		//			}
		//			if(this.start==this.stop)
		//			{
		//				if(l<=this.start && this.start<=r)
		//				{
		//					this.val^=1;
		//					return this.val;
		//				}
		//				else
		//				{
		//					return this.val;
		//				}
		//			}
		//			else if(this.start>r || this.stop<l)
		//			{
		//				return this.val;
		//			}
		//			else if(this.start>=l && this.stop<=r)
		//			{
		//				this.lazy=1-this.lazy;
		//				this.val=this.stop-this.start+1-this.val;
		//				return this.val;
		//			}
		//			else
		//			{
		//				if(this.lazy==0)
		//				{
		//					//left.query(l, r)+right.query(l, r);
		//					this.val=left.lazyUpdate(l, r);
		//					this.val+=right.lazyUpdate(l, r);
		//				}
		//				else
		//				{
		//					this.lazy=0;
		//					left.lazyUpdate(start,stop);
		//					right.lazyUpdate(start, stop);
		//					this.val=left.lazyUpdate(l, r);
		//					this.val+=right.lazyUpdate(l, r);
		//				}
		//				return this.val;
		//			}
		//		}

		//		public int query(int l,int r)
		//		{
		//			if(l>r)
		//			{
		//				return 0;
		//			}
		//			if(this.start==this.stop)
		//			{
		//				if(l<=this.start && this.start<=r)
		//				{
		//					return this.val;
		//				}
		//				else
		//				{
		//					return 0;
		//				}
		//			}
		//			else if(this.start>r || this.stop<l)
		//			{
		//				return 0;
		//			}
		//			else if(this.start>=l && this.stop<=r)
		//			{
		//				return this.val;
		//			}
		//			else
		//			{
		//				if(this.lazy==0)
		//				{
		//					return left.query(l, r)+right.query(l, r);
		//				}
		//				else
		//				{
		//					this.lazy=0;
		//					this.val=left.lazyUpdate(start,stop);
		//					this.val+=right.lazyUpdate(start, stop);
		//					return left.query(l, r)+right.query(l, r);
		//				}
		//			}
		//		}

	}

	class sss
	{
		long val;
		int len;
		//			sss(int v,int l)
		//			{
		//				this.val=v;
		//				this.len=l;
		//			}

	}

	public class UF {

		private int[] parent;  // parent[i] = parent of i
		private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
		private int count;   
		int[] seg;
		int max;
		// number of components
		public UF(int N) {
			if (N < 0) throw new IllegalArgumentException();
			count = N;
			parent = new int[N];
			rank = new byte[N];
			seg=new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
				rank[i] = 0;
				seg[i]=1;
			}
			max=1;
		}

		public int find(int p) {
			if (p < 0 || p >= parent.length) throw new IndexOutOfBoundsException();
			while (p != parent[p]) {
				parent[p] = parent[parent[p]];    // path compression by halving
				p = parent[p];
			}
			return p;
		}

		public int count() {
			return count;
		}

		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		public boolean union(int p, int q) {
			//  System.out.println("uf "+p+" "+q);
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) return false;

			// make root of smaller rank point to root of larger rank
			if      (rank[rootP] < rank[rootQ])
			{
				parent[rootP] = rootQ;
				seg[rootQ]+=seg[rootP];
				max=Math.max(seg[rootQ],max);
			}
			else if (rank[rootP] > rank[rootQ])
			{
				parent[rootQ] = rootP;
				seg[rootP]+=seg[rootQ];
				max=Math.max(seg[rootP],max);
			}
			else {
				parent[rootQ] = rootP;
				rank[rootP]++;
				seg[rootP]+=seg[rootQ];
				max=Math.max(seg[rootP],max);
			}
			count--;
			return true;
		}

	}



	long pow(long a,int n,long m)
	{
		if(n==1)
			return a;
		if(n==0)
			return 1;
		long ans=pow(a,n/2,m);
		ans*=ans;
		ans%=m;
		if(n%2!=0)
			ans*=a;
		ans%=m;
		return ans;
	}

	public static int gcd(long a, long b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
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
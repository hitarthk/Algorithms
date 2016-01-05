/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_326;

/**
 *
 * @author hitarthk
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Hashtable;
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
 public class A {
    public static void main(String[] args) {
        FasterScanner s=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        
        
        int n=s.nextInt();
        int l=s.nextInt();
        int k=s.nextInt();
        int a[] =s.nextIntArray(n);
        Hashtable<Integer,Integer> map=new Hashtable<>();
        int[] b=Arrays.copyOf(a, n);
        Arrays.sort(b);
        long freq[] = new long[n+1];
        int index=0;
        
       
        for(int i=0;i<n;i++)
        { 
            if(!map.containsKey(b[i]))
                map.put(b[i], ++index);
                
            freq[map.get(b[i])]++;
        }  
        freq=Arrays.copyOf(freq, index+1);
        int len=index+1;
        int x=l/n;
        if(k*n<=l-n)
        {
            System.out.println(0);
            return;
        }
        long[] prev=Arrays.copyOf(freq, len);
        for(int i=1;i<len;i++)
        {
            prev[i]+=prev[i-1];
        }
        long[] next=new long[len];
        for(int j=1;j<k;j++)
        {
            for(int i=0;i<len;i++)
            {
                next[i]=prev[i];
                if(i!=0)
                {
                    next[i]+=next[i-1];
                    next[i]%=mod;
                }
            }
            prev=next;
            next=new long[len];
        }
        
        long ans=x-k+1;
        ans*=prev[len-1];
        ans%=mod;
        if(ans<0)
            ans=0;
        System.out.println(ans);
        if(l%n!=0)
        {
            prev=Arrays.copyOf(freq, len);
            for(int i=1;i<len;i++)
            {
                prev[i]+=prev[i-1];
            }
            next=new long[len];
            
                System.out.println(Arrays.toString(prev));
            for(int j=1;j<Math.min(Math.ceil(l/(double)n),k-1)-1;j++)
            {
                ans+=prev[len-1];
                ans%=mod;
                System.out.println(ans);
                for(int i=0;i<len;i++)
                {
                    next[i]=prev[i];
                    if(i!=0)
                    {
                        next[i]+=next[i-1];
                        next[i]%=mod;
                    }
                }
                prev=next;
                System.out.println(Arrays.toString(prev));
                next=new long[len];
            }
                System.out.println(Arrays.toString(prev));
            for(int i=0;i<l%n;i++)
            {
                ans+=prev[map.get(a[i])];
                ans%=mod;
                  System.out.println(map.get(a[i]));
            }
        }
        out.println(ans);
        
        out.close();
    } 


public static class Sieve {

    

    public static int[] sieveEratosthenes(int n) {
        if(n <= 32){
            int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
            for(int i = 0;i < primes.length;i++){
                if(n < primes[i]){
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }

        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;

        int[] isp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;

        int[] tprimes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
        for(int tp : tprimes){
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for(int i = (tp - 3) / 2;i < tp << 5;i += tp)
                ptn[i >> 5] |= 1 << (i & 31);
            for(int i = 0;i < tp;i++){
                for(int j = i;j < sup;j += tp)
                    isp[j] |= ptn[i];
            }
        }

        // 3,5,7
        // 2x+3=n
        int[] magic = { 0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14 };
        int h = n / 2;
        for(int i = 0;i < sup;i++){
            for(int j = ~isp[i];j != 0;j &= j - 1){
                int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
                int p = 2 * pp + 3;
                if(p > n)
                    break;
                ret[pos++] = p;
                for(int q = pp;q <= h;q += p)
                    isp[q >> 5] |= 1 << (q & 31);
            }
        }

        return Arrays.copyOf(ret, pos);
    }
}
    static long mod=(long)1e9+7;
    
    static int[] tree;
    static int[] a;
    public static void build(int node,int s,int e){
        if(s==e){
            tree[node]=a[s];
        }
        else{
            int mid=(s+e)>>1;
            build(2*node,s,mid);
            build(2*node+1, mid+1, e);
            tree[node]=merge(tree[2*node], tree[2*node+1]);
        }
        
    }
    
    public static void update(int node,int s,int e,int p){
        if(s==e)
            return;
        int mid=(s+e)>>1;
        if(p<=mid){
            update(2*node, s, mid, p);
        }
        else{
            update(2*node+1, mid+1, e, p);
        }
        tree[node]=merge(tree[2*node], tree[2*node+1]);
    }
    
    public static int query(int node,int s,int e,int l,int r){
        if(s==l && e==r){
            return tree[node];
        }
        int mid=(s+e)>>1;
        if(r<=mid){
            return query(2*node, s, mid, l, r);
        }
        if(l>mid)
            return query(2*node+1, mid+1, e, l, r);
        return merge(query(2*node, s, mid, l, mid),query(2*node+1, mid+1, e, mid+1, r));
    }
    
    
    public static int merge(int l,int r){
        return l+r;
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
    
    public static long modInverse(long a, long p) 
    {
		// calculates the modular multiplicative inverse of a mod m.
		// (assuming p is prime).
		return modPow(a, p - 2, p);
	}
    
    public static long ncr[][];
    private static void generateNCR( long z, long p )
    {
    	ncr = new long[(int)z+1][(int)z+1];
    	ncr[0][0] = 1;
    	for(int i=1; i<=z; i++)
    	{
    		ncr[i][0] = 1;
    		ncr[0][i] = 0;
    	}
        for(int i=1; i<=z; i++)
        {
            for(int j=1; j<=z; j++)
            {
                ncr[i][j] = (ncr[i-1][j] + ncr[i-1][j-1]) % p;
            }
        }
    }
    
    static class UF {

        private int[] parent;  // parent[i] = parent of i
        private int[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;     // number of components
        public UF(int N) {
            if (N < 0) throw new IllegalArgumentException();
            count = N;
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find( int p )
        {
        	if( parent[p]!=p )
        	{
        		parent[p] = find( parent[p] );
        	}
        	return parent[p];
        }
        
        public int findKAN(int p) {
            if (p < 0 || p >= parent.length) throw new IndexOutOfBoundsException();
            while (p != parent[p]) 
            {
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
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return false;

            // make root of smaller rank point to root of larger rank
            if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
            return true;
        }

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

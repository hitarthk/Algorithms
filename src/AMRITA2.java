
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class AMRITA2 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            Integer[] a=new Integer[n];
            for(int i=0;i<n;i++){
                a[i]=in.nextInt();
            }
            long ans=1;
            HashMap<Integer,Long> h=new HashMap<>();
            //h.put(1, 1L);
            Queue<Integer> q=new LinkedList<>();
            q.add(a[0]);
            h.put(a[0], 1l);
                    
            for(int i=1;i<n;i++){
                Queue<Integer> tempq=new LinkedList<>();
                
                while(!q.isEmpty()){
                    Integer x=q.poll();
                    //System.out.println("Polled "+x);
                    Integer g=gcd(a[i],x);
                    //System.out.println("gcd "+g);
                    if(!h.containsKey(g)){
                        h.put(g,1l);
                        tempq.add(g);
                    }
                    else{
                        //System.out.println("In lese");
                        Long prev=h.get(g);
                        Long now=(2*prev)%mod;
                        h.put(g, now);
                    }
                    tempq.add(x);
                }
                if(h.get(a[i])==null || h.get(a[i])==0){
                    h.put(a[i], 1l);
                    tempq.add(a[i]);
                }
                q=tempq;
            }
            
            //System.out.println(h);
            
            for(Long v: h.values()){
                
                ans = (ans*v)%mod;
            }
            System.out.println(ans);
        }        
    }
    
    
    static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
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
        
}

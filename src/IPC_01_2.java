
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class IPC_01_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in) );
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        //br.readLine();
        s = br.readLine().split(" ");
        long[] a = new long[n];
        int l = 2*(int)Math.pow(2, Math.ceil(Math.log(n)/Math.log(2)))-1;
        long[] tree = new long[l];
        for( int i=0 ; i<n ; i++ )
        {
            a[i] = Integer.parseInt(s[i]);
        }
        build(1,0,n-1,tree,a);
        int max=Integer.MIN_VALUE;
        for( int i=0 ; i<n ; i++ )
        {
            int left=i,mid; int right=n;
            if(a[i]<k){
                continue;
            }
            while( right-left>1 )
            {
                mid = (left+right)/2;
                long q=query(1, 0, n-1, i, mid, tree, a);
                if(q>=k){
                    left=mid;
                }
                else{
                    right=mid;
                }
            }
            if(left-i+1>max){
                max=left-i+1;
            }
        }
        System.out.println(max);
    }
    
    static int[] tree;
    static int[] a;
    
    public static void build(int node, int s, int e, long[] tree, long[] a) {
        if (s == e) {
            tree[node] = a[s];
        } else {
            int mid = (s + e) >> 1;
            build(2 * node, s, mid, tree, a);
            build(2 * node + 1, mid + 1, e, tree, a);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

    }
    
    public static void update(int node, int s, int e, int p, long[] tree, long[] a) {
        if (s == e) {
            tree[node]=a[p];
            return;
        }
        int mid = (s + e) >> 1;
        if (p <= mid) {
            update(2 * node, s, mid, p, tree, a);
        } else {
            update(2 * node + 1, mid + 1, e, p, tree, a);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public static long query(int node, int s, int e, int l, int r, long[] tree, long[] a) {
        if (s == l && e == r) {
            return tree[node];
        }
        int mid = (s + e) >> 1;
        if (r <= mid) {
            //System.out.println(l+" "+r);
            return query(2 * node, s, mid, l, r, tree, a);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, e, l, r, tree, a);
        }
        return merge(query(2 * node, s, mid, l, mid, tree, a), query(2 * node + 1, mid + 1, e, mid + 1, r, tree, a));
    }

    public static long merge(long l, long r) {
        return gcd(l,r);
    }
    
    static long gcd( long a, long b )
    {
        if( a<b )
        {
            long temp = a; a=b ; b=temp;
        }
        while( b!=0 )
        {
            long aa = a; long bb=b; b=aa%bb; a=bb;
        }
        return a;
    }
}


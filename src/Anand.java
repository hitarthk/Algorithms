/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class Anand {
    public static void main(String[] args) {
        
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
        return l + r;
    }
    
}

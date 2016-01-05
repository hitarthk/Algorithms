/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author hitarthk
 */
public class SegTree {

    int n;
    int[] t;

    public SegTree(int n) {
        this.n = n;
        t = new int[2 * n];
    }

    int elementAt(int rank) {
        int l = 0;
        int r = n - 1;
        while (r - l > 1) {
            int m = (r + l) / 2;
            if (query(0, m + 1) >= rank) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    void build() {  // build the tree

        for (int i = n - 1; i > 0; --i) {
            t[i] = (t[i << 1] + t[i << 1 | 1]);
        }
    }

    void modify(int p, int value) {  // set value at position p
        for (t[p += n] = value; p > 1; p >>= 1) {
            t[p >> 1] = (t[p] + t[p ^ 1]);
        }
    }

    long query(int l, int r) {  // sum on interval [l, r)
        int res = 0;

        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) {
                res = (res + t[l++]);
            }
            if ((r & 1) != 0) {
                res = (res + t[--r]);
            }
        }
        return res;
    }
//	
//	
//	
}

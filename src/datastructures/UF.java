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
public class UF {

    public int max = 1;
    private int[] size;
    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    public UF(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        count = N;
        parent = new int[N];
        rank = new byte[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public void print(int id) {
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == parent[id]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public int find(int p) {
        if (p < 0 || p >= parent.length) {
            return -1;
        }
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
        if (p >= rank.length || q >= rank.length) {
            return false;
        }

        return find(p) == find(q);
    }

    public boolean union(int p, int q) {

        if (p >= rank.length || q >= rank.length) {
            return false;
        }
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return false;
        }

        // make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            if (size[rootQ] > max) {
                max = size[rootQ];
            }
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            if (size[rootP] > max) {
                max = size[rootP];
            }
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
            size[rootP] += size[rootQ];
            if (size[rootP] > max) {
                max = size[rootP];
            }
        }
        count--;
        return true;
    }

}

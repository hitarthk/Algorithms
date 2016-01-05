
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
public class CityAndFiremanVincent {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] e=new int[n];
        for(int i=0;i<n;i++){
            e[i]=in.nextInt();
        }
        UF uf=new UF(n, e);
        int k=in.nextInt();
        for(int i=0;i<k;i++){
            int a=in.nextInt()-1;
            int b=in.nextInt()-1;
            uf.union(a, b);
        }
        System.out.println(uf.giveAns());
    }
    
    
    
    static class UF {

        public int max = 1;
        private int[] size;
        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;     // number of components
        private int[] e;
        private int[] min;
        private long[] f;
        public UF(int N,int[] e) {
            if (N < 0) {
                throw new IllegalArgumentException();
            }
            count = N;
            parent = new int[N];
            rank = new byte[N];
            size = new int[N];
            this.e=e;
            min=new int[N];
            f=new long[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
                min[i]=e[i];
                f[i]=1;
            }
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
                if(min[rootP]>min[rootQ]){
                    min[rootQ]=min[rootQ];
                    f[rootQ]=f[rootQ];
                }
                else if(min[rootP]<min[rootQ]){
                    min[rootQ]=min[rootP];
                    f[rootQ]=f[rootP];
                }
                else{
                    f[rootQ]+=f[rootP];
                }
                f[rootP]=1;
                size[rootQ] += size[rootP];
                if (size[rootQ] > max) {
                    max = size[rootQ];
                }
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
                if(min[rootP]>min[rootQ]){
                    min[rootP]=min[rootQ];
                    f[rootP]=f[rootQ];
                }
                else if(min[rootP]<min[rootQ]){
                    min[rootP]=min[rootP];
                    f[rootP]=f[rootP];
                }
                else{
                    f[rootP]+=f[rootQ];
                }
                f[rootQ]=1;
                size[rootP] += size[rootQ];
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            } else {
                parent[rootQ] = rootP;
                if(min[rootP]>min[rootQ]){
                    min[rootP]=min[rootQ];
                    f[rootP]=f[rootQ];
                }
                else if(min[rootP]<min[rootQ]){
                    min[rootP]=min[rootP];
                    f[rootP]=f[rootP];
                }
                else{
                    f[rootP]+=f[rootQ];
                }
                f[rootQ]=1;
                rank[rootP]++;
                size[rootP] += size[rootQ];
                if (size[rootP] > max) {
                    max = size[rootP];
                }
            }
            count--;
            return true;
        }
        
        public long giveAns(){
            long m=(long)1e9+7;
            long ans=1;
            for(int i=0;i<e.length;i++){
                ans = (ans*f[i])%m;
            }
            return ans;
        }

    }

    

}

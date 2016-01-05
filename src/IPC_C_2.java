
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
public class IPC_C_2 {

    static ArrayList[] child;
    static int[] size;
    static int[] f;
    static int ans;
    static int[] color;
    static int[] a;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        child = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            child[i] = new ArrayList<Integer>();
        }
        f=new int[100001];
        for (int i = 2; i <= n; i++) {
            int x = in.nextInt();
            child[x].add(i);
        }
        color=new int[n+1];
        for(int i=1;i<=n;i++){
            color[i]=in.nextInt();
        }
        size = new int[n + 1];
        a=new int[n];
        dfs(1,0);
        int q=n;
        //System.out.println(Arrays.toString(a));
        //System.out.println(Arrays.toString(size));
        Query[] Q=new Query[n];
        for(int i=0;i<n;i++){
            Q[i]=new Query(i, i, i+size[a[i]]-1);
        }
        Arrays.sort(Q);
        ans=0;
        long real=0;
        int currentL=0;
        int currentR=0;
        for(int i=0;i<q;i++){
            int L=Q[i].l;
            int R=Q[i].r;
            //System.out.println( L+" "+R);
            while (currentL < L) {
                remove(currentL);
                currentL++;
            }
            while (currentL > L) {
                add(currentL - 1);
                currentL--;
            }
            while (currentR <= R) {
                add(currentR);
                currentR++;
            }
            while (currentR > R + 1) {
                remove(currentR - 1);
                currentR--;
            }
            real+=ans;
        }
        System.out.println(real);
    }
    
    static void add(int position){
        if(f[color[a[position]]]==0){
            ans++;
        }
        f[color[a[position]]]++;
    }
    
    static void remove(int position){
        f[color[a[position]]]--;
        if(f[color[a[position]]]==0){
            ans--;
        }
    }

    static class Query implements Comparable<Query> {

        int id;
        int l;
        int r;

        public Query(int id, int l, int r) {
            this.id = id;
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int tb=l/320;
            int ob=o.l/320;
            if(tb==ob){
                return this.r-o.r;
            }
            else{
                return tb-ob;
            }
        }

    }

    static void dfs(int v,int offset) {
        a[size[v]+offset]=v;
        size[v]++;
        offset++;
        for (Integer x : (ArrayList<Integer>) child[v]) {
            dfs(x,offset);
            offset+=size[x];
            size[v] += size[x];
        }
    }

}

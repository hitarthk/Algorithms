
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class C_331_C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        HashMap<Integer,TreeSet<Pair>> h=new HashMap<>();
        int n=in.nextInt();
        int offset=100000;
        a=new long[200005];
        tree=new long[4*200005];
        b=new long[200005];
        Arrays.fill(b,Long.MIN_VALUE);
        
        ytree=new long[4*200005];
        build(1, 0, 200004, ytree, b);
        Pair[] p=new Pair[n];
        for(int i=0;i<n;i++){
            int x=in.nextInt();
            int y=in.nextInt();
            
            if(h.get(y-x)==null){
                h.put(y-x, new TreeSet<Pair>());
            }
            h.get(y-x).add(new Pair(x, y));
        }
        int[] w=new int[n];
        for(int i=0;i<n;i++){
            w[i]=in.nextInt();
        }
        
        boolean check=true;
        for(int i=0;i<n;i++){
            if(h.get(w[i])==null || h.get(w[i]).isEmpty()){
                check=false;
                break;
            }
            Pair cur=h.get(w[i]).first();
            long q1=query(1, 0, 200004, w[i]+offset+1, 200004, tree, a);
            long q2=query(1, 0, 200004, 0, w[i]+offset-1, ytree, b);
            //System.out.println(q1+" "+q2+" "+cur.x+" "+cur.y);
            if(cur.x<q1 || cur.y<=q2){
                check=false;
                break;
            }
            a[w[i]+offset]=cur.x;
            b[w[i]+offset]=cur.y;
            update(1, 0, 200004, w[i]+offset, tree, a);
            update(1, 0, 200004, w[i]+offset, ytree, b);
            p[i]=cur;
            h.get(w[i]).remove(cur);
        }
        if(check) {
            System.out.println("YES");
            for(int i=0;i<n;i++){
                System.out.println(p[i].x+" "+p[i].y);
            }
        }
        else{
            System.out.println("NO");
        }
    }
    
    static class Pair implements Comparable<Pair>{
        int id;
        int x;
        int y;
        public Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
        @Override
        public int compareTo(Pair o) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return this.x-o.x;
        }
        public String toString(){
            return this.x+" "+this.y;
        }
    }
    
    static long[] tree;
    static long[] a;
    static long[] b;
    static long[] ytree;
    public static void build(int node, int s, int e, long[] tree, long[] a) {
        if(s>e){
            return;
        }
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
        return Math.max(l, r);
    }

    
}

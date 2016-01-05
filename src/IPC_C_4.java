
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class IPC_C_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(in.readLine());
        String[] s;
        
        while(t-->0){
            s=in.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            String[] a=new String[n+1];
            for(int i=1;i<=n;i++){
                a[i]=in.readLine();
            }
            
            boolean[][] b=new boolean[26][n+1];
            boolean[][] tree=new boolean[26][4*(n+1)];
            b[a[n].charAt(0)-'a'][n]=true;
            update(1, 1, n, n, tree[a[n].charAt(0)-'a'], b[a[n].charAt(0)-'a']);
            for(int i=n-1;i>=1;i--){
                int clast=a[i].charAt(a[i].length()-1)-'a';
                int cfirst=a[i].charAt(0)-'a';
                boolean q=query(1, 1, n, i+1, Math.min(i+1+k-1,n), tree[clast], b[clast]);
                if(!q){
                    b[cfirst][i]=true;
                    update(1, 1, n, i, tree[cfirst], b[cfirst]);
                }
            }
            boolean ans=false;
            for(int i=1;i<=k;i++){
                for(int j=0;j<26;j++){
                    ans|=b[j][i];
                }
            }
            
//            for(int i=0;i<26;i++){
//                System.out.println(Arrays.toString(b[i]));
//            }
            
            if(ans){
                System.out.println("Alex");
            }
            else{
                System.out.println("Bob");
            }
        }
    }
    
    public static void build(int node, int s, int e, boolean[] tree, boolean[] a) {
        if (s == e) {
            tree[node] = a[s];
        } else {
            int mid = (s + e) >> 1;
            build(2 * node, s, mid, tree, a);
            build(2 * node + 1, mid + 1, e, tree, a);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

    }
    
    public static void update(int node, int s, int e, int p, boolean[] tree, boolean[] a) {
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

    public static boolean query(int node, int s, int e, int l, int r, boolean[] tree, boolean[] a) {
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

    public static boolean merge(boolean l, boolean r) {
        return l | r;
    }
    
    
}

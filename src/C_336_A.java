
import java.util.ArrayList;
import java.util.Collections;
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
public class C_336_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int s=in.nextInt();
        ArrayList[] h=new ArrayList[s+1];
        for(int i=0;i<h.length;i++){
            h[i]=new ArrayList<Pair>();
        }
        for(int i=0;i<n;i++){
            int f=in.nextInt();
            int t=in.nextInt();
            h[f].add(new Pair(f, t));
        }
        
        for(int i=0;i<h.length;i++){
            Collections.sort(h[i]);
        }
        
        int cur=0;
        for(int i=s;i>=1;i--){
            for(Pair x:(ArrayList<Pair>)h[i]){
                cur=Math.max(cur, x.t);
            }
            cur++;
        }
        System.out.println(cur );
        
    }
    
    static class Pair implements Comparable<Pair>{
        int f;
        int t;
        
        public Pair(int f,int t){
            this.f=f;
            this.t=t;
        }

        @Override
        public int compareTo(Pair o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return this.t-o.t;
        }
        
    }
    
}

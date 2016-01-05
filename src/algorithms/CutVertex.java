/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class CutVertex {
    static ArrayList[] adj;
    static int[] h;
    static boolean[] v;
    static int n;
    static int[] d;
    static int[] p;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        adj=new ArrayList[n];
        h=new int[n];
        v=new boolean[n];
        d=new int[n];
        p=new int[n];
        Arrays.fill(h, -1);
        Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(p, -1);
        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<Integer>();
        }
        int m=in.nextInt();
        for(int i=0;i<m;i++){
            int x=in.nextInt();
            int y=in.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        dfsh();
        System.out.println(Arrays.toString(p));
        dfs2();
        System.out.println(Arrays.toString(d));
    }
    
    static void dfsh(){
        for(int i=0;i<n;i++){
            if(h[i]==-1){
                visith(i,0);
            }
        }
    }
    
    static void visith(int x,int height){
        h[x]=height;
        for(Integer y:(ArrayList<Integer>)adj[x]){
            if(h[y]==-1){
                p[y]=x;
                visith(y,height+1);
            }
        }
    }
    
    static void dfs2(){
        for(int i=0;i<n;i++){
            if(!v[i]){
                visit(i);
            }
        }
    }
    
    static void visit(int x){
        v[x]=true;
        System.out.println(x);
        d[x]=h[x];
        for(Integer y:(ArrayList<Integer>)adj[x]){
            if(!v[y]){
                visit(y);
                d[x]=Math.min(d[x], d[y]);
                if(d[y]>=h[y]){
                    System.out.println(x+" "+y+" is a cut edge");
                }
            }
            else if(p[x]!=y){
                d[x]=Math.min(d[x], h[y]);
                //System.out.println("Here");
            }
        }
        
    }
}

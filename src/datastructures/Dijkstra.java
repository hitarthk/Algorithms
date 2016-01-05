/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author hitarthk
 */
public class Dijkstra {
    static class Node implements Comparable<Node>{
        int id;
        ArrayList<Integer> list;
        ArrayList<Integer> weight;
        int d;
        public Node(int id){
            this.id=id;
            this.d=Integer.MAX_VALUE/2;
            list=new ArrayList<>();
            weight=new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.d==o.d){
                return this.id-o.id;
            }
            else{
                return this.d-o.d;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            Node[] v=new Node[n];
            for(int i=0;i<n;i++){
                v[i]=new Node(i);
            }
            for(int i=0;i<m;i++){
                int x=in.nextInt()-1;
                int y=in.nextInt()-1;
                int r=in.nextInt();
                v[x].list.add(y);
                v[x].weight.add(r);
                v[y].list.add(x);
                v[y].weight.add(r); 
            }
            int s=in.nextInt()-1;
            v[s].d=0;
            TreeSet<Node> tree=new TreeSet<>();
            for(int i=0;i<n;i++){
                tree.add(v[i]);
            }
            
            while(!tree.isEmpty()){
                Node cur=tree.first();
                tree.remove(cur);
                for(int i=0;i<cur.list.size();i++){
                    if(tree.contains(v[cur.list.get(i)])){
                        if(v[cur.list.get(i)].d>cur.d+cur.weight.get(i)){
                            tree.remove(v[cur.list.get(i)]);
                            v[cur.list.get(i)].d=cur.d+cur.weight.get(i);
                            tree.add(v[cur.list.get(i)]);
                        }
                    }
                }
            }
            
            for(int i=0;i<n;i++){
                if(v[i].d!=0){
                    if(v[i].d==Integer.MAX_VALUE/2){
                        System.out.print("-1 ");
                    }
                    else{
                        System.out.print(v[i].d+" ");
                    }
                }
            }
            System.out.println();
        }
    }
    
}


import java.io.PrintWriter;
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
public class C_335_D {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        int m=in.nextInt();
        Edge[] e=new Edge[m];
        for(int i=0;i<m;i++){
            int w=in.nextInt();
            int t=in.nextInt();
            e[i]=new Edge();
            e[i].id=i+1;
            e[i].weight=w;
            if(t==1){
                e[i].taken=true;
            }
            else{
                e[i].taken=false;
            }
        }
        
        Arrays.sort(e);
        boolean check=true;
        
        int vertices=1;
        int backu=3;
        int backv=1;
        
        for(int i=0;i<m;i++){
            if(e[i].taken){
                e[i].u=vertices;
                e[i].v=++vertices;
            }
            else{
                if((vertices*(vertices-1))/2-(i)>0){
                    if(backv==0){
                        backu++;
                        backv=backu-2;
                    }
                    e[i].u=backu;
                    e[i].v=backv;
                    backv--;
                }
                else{
                    check=false;
                    break;
                }
            }
        }
        
        if(!check){
            System.out.println("-1");
            return;
        }
        //System.out.println(Arrays.toString(e));
        int[] u=new int[m+1];
        int[] v=new int[m+1];
        
        for(int i=0;i<m;i++){
            u[e[i].id]=e[i].u;
            v[e[i].id]=e[i].v;
        }
        
        for(int i=1;i<=m;i++){
            out.println(u[i]+" "+v[i]);
        }
        
        out.close();
    }
    
    static class Edge implements Comparable<Edge>{
        int id;
        boolean taken;
        int weight;
        int u;
        int v;
        @Override
        public int compareTo(Edge o) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.weight!=o.weight)
            return this.weight-o.weight;
            else{
                if(this.taken && !o.taken){
                    return -1;
                }
                else if(!this.taken && o.taken){
                    return 1;
                }
                else{
                    return this.id-o.id;
                }
            }
        }
        public String toString(){
            return id+" "+taken+" "+weight;
        }
    }
    
    static class Vertex{
        int id;
        int parent;
    }
    
}

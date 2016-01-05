
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
public class c_327_c {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Queue<Node> q=new LinkedList<Node>();
        int n=in.nextInt();
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
        }
        
        for(int i=2;i<=n-1;i++){
            int count=0;
            
            count=(a[i-1]^a[i])==1?1:0;
            count += (a[i+1]^a[i])==1?1:0;
            //System.out.println(count);
            if(count==2)
            q.add(new Node(a[i],i,0));
        }
        //System.out.println(q);
        int time=0;
        while(!q.isEmpty()){
            Node cur=q.poll();
            time=Math.max(time, cur.time+1);
            a[cur.index]=a[cur.index]^1;
            if(cur.index>2){
                int count=(a[cur.index-2]^a[cur.index-1])==1?1:0;
                count+= (a[cur.index]^a[cur.index-1])==1?1:0;
                if(count==2){
                    q.add(new Node( a[cur.index-1],cur.index-1,cur.time+1));
                }
            }
        }
        System.out.println(time);
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
    }
    
    static class Node{
        int original;
        int index;
        int time;
        public Node(int original,int index,int time){
            this.original=original;
            this.index=index;
            this.time=time;
        }
        
        public String toString(){
            return String.format("%d ", index);
        }
        
    }
    
    
}

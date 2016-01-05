/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APAC;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author hitarthk
 */
public class R3A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        int T=t;
        while(t>0){
            t--;
            int p=in.nextInt();
            int[] s=new int[p+1];
            for(int i=1;i<=p;i++){
                s[i]=in.nextInt();
            }
            int atheletes=0;
            int n=in.nextInt();
            HashMap<String,PriorityQueue<Integer>> h=new HashMap();
            for(int i=1;i<=n;i++){
                int w=in.nextInt();
                for(int j=1;j<=p;j++){
                    String a=in.next();
                    if(h.get(a)==null){
                        h.put(a, new PriorityQueue<>(Comparator.reverseOrder()));
                        atheletes++;
                    }
                    h.get(a).add(w*s[j]);
                    
                }
            }
            int m=in.nextInt();
            TreeSet<Athelete> tree=new TreeSet();
            for(Map.Entry e: h.entrySet()){
                int totalScore=0;
                for(int i=1;i<=m;i++){
                    if(h.get(e.getKey()).size()==0)
                        break;
                    totalScore+=h.get(e.getKey()).poll();
                }
                tree.add(new Athelete(totalScore, (String) e.getKey()));
            }
            Athelete[] arr=new Athelete[atheletes];
            arr=tree.toArray(arr);
            int rank=1;
            int total=0;
            System.out.println("Case #"+(T-t)+":");
            for(int i=0;i<atheletes;i++){
                total++;
                if(i>0 && arr[i].totalScore<arr[i-1].totalScore){
                    rank=total;
                }
                System.out.println(rank+": "+arr[i].name);
            }
        }
    }
    
    static class Athelete implements Comparable<Athelete>{
        int totalScore;
        String name;
        public Athelete(int totalScore,String name){
            this.totalScore=totalScore;
            this.name=name;
        }

        @Override
        public int compareTo(Athelete o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.totalScore==o.totalScore){
                return this.name.compareTo(o.name);
            }
            return -(this.totalScore-o.totalScore);
        }
        
    }
}

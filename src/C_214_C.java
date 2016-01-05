
import java.util.Arrays;
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
public class C_214_C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int[][] ans=new int[n][2];
        Mitten[] a=new Mitten[m];
        for(int i=0;i<m;i++){
            a[i]=new Mitten();
            a[i].c=i;
        }
        for(int i=0;i<n;i++){
            int c=in.nextInt()-1;
            a[c].f++;
            a[c].f++;
        }
        TreeSet<Mitten> t=new TreeSet<>();
        for(int i=0;i<m;i++){
            if(a[i].f>0)
            t.add(a[i]);
        }
        int total=0;
        for(int i=0;i<n;i++){
            Mitten first=(t.first());
            Mitten last=t.last();
            t.remove(first);
            if(first.c!=last.c)
            t.remove(last);
            ans[i][0]=first.c;
            ans[i][1]=last.c;
            if(first.c!=last.c){
                total++;
            }
            first.f--;
            last.f--;
            if(first.f>0)
            t.add(first);
           
            t.add(last);
        }
        System.out.println(total);
        for(int i=0;i<n;i++){
            System.out.println((ans[i][0]+1)+" "+(ans[i][1]+1));
        }
    }
    
    static class Mitten implements Comparable<Mitten>{
        int f;
        int c;
        @Override
        public int compareTo(Mitten o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.f!=o.f){
                return this.f-o.f;
            }
            return this.c-o.c;
        }
        
    }
    
}

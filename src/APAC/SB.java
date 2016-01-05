/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APAC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class SB {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in=new Scanner(new File("/home/hitarthk/Desktop/B-small-practice.in"));
        //System.out.println(gcd(72,28));
        //Scanner in=new Scanner(System.in);
        PrintWriter out=new PrintWriter("output");
        int T=in.nextInt();
        for(int test=1;test<=T;test++){
            out.println("Case #"+test+":");
            int[] n=new int[3];
            long[][] g=new long[3][];
            for(int i=0;i<3;i++){
                n[i]=in.nextInt();
                g[i]=new long[n[i]];
            }
            
            for(int i=0;i<3;i++){
                for(int j=0;j<n[i];j++){
                    g[i][j]=in.nextLong();
                }
            }
            
            HashSet<Pair> h=new HashSet<>();
            //Long[] p=new Long[2];
            for(int i=0;i<n[1];i++){
                for(int j=i+1;j<n[1];j++){
                    Long[] p=new Long[2];
                    p[0]=g[1][i];
                    p[1]=g[1][j];
                    Long gcd=gcd(g[1][i],g[1][j]);
                    p[0]/=gcd;
                    p[1]/=gcd;
                    //System.out.println(Arrays.toString(p));
                    //System.out.println(p.hashCode());
                    h.add(new Pair(p[0],p[1]));
                    p=new Long[2];
                    p[0]=g[1][j];
                    p[1]=g[1][i];
                    p[0]/=gcd;
                    p[1]/=gcd;
                    //System.out.println(Arrays.toString(p));
                    //System.out.println(p.hashCode());
                    h.add(new Pair(p[0],p[1]));
                }
            }
            
            //System.out.println(h.toString());
            
            //System.out.println(h.size());
            
            int M=in.nextInt();
            
            for(int m=0;m<M;m++){
                long P=in.nextInt();
                long Q=in.nextInt();
                boolean check=true;
                A:for(int i=0;i<n[0];i++){
                    for(int j=0;j<n[2];j++){
                        Long[] p=new Long[2];
                        p[0]=P*g[2][j];
                        p[1]=Q*g[0][i];
                        long gcd=gcd(p[0],p[1]);
                        p[0]/=gcd;
                        p[1]/=gcd;
                        //System.out.println(p.hashCode());
                        if(h.contains(new Pair(p[0],p[1]))){
                            //System.out.println("Req "+Arrays.toString(p));
                            out.println("Yes");
                            check=false;
                            break A;
                        }
                    }
                }
                if(check)
                out.println("No");
            }
            
        }
        out.close();
    }
    
    static class Pair{
        Long a;
        Long b;
        @Override
        public int hashCode(){
            return a.hashCode()+b.hashCode();
        }
        
        public Pair(Long a,Long b){
            this.a=a;
            this.b=b;
        }
        
        @Override
        public boolean equals(Object obj){
            if(obj!=null && Pair.class.isInstance(obj)){
                Pair p=(Pair)obj;
                return a==p.a && b==p.b;
                         
            }
            return false;
        }
        
    }
    
    static long gcd(long a,long b){
        if(a<b){
            long temp=a;
            a=b;
            b=temp;
        }
        while(b>0){
            long temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
    
}

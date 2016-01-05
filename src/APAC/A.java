/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APAC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class A {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in=new Scanner(new File("/home/hitarthk/Desktop/A-large.in"));
        //Scanner in=new Scanner(System.in);
        PrintWriter out=new PrintWriter("output");
        int T=in.nextInt();
        for(int t=1;t<=T;t++){
            long k=in.nextLong();
            long n=(long)(Math.log(k)/Math.log(2))+1;
            out.println("Case #"+t+": "+cal(k,n));
        }
        out.close();
    }
    
    public static int cal(long k,long n){
        if(n==1){
            return 0;
        }
        
        double lnp=Math.pow(2, n-1);
        double lnc=Math.pow(2,n)-1;
        if(k==lnp){
            return 0;
        }
        if(k<lnp){
            return cal(k,n-1);
        }
        else{
            int ans=cal((long)lnc-k+1,n-1);
            return ans==0?1:0;
        }
    }
    
    
    
            
}

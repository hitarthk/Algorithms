
import java.util.ArrayList;
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
class JanLong1BF {
    static long mod=(long)1e9+7;
    static int n;
    static long[] a;
    static long ans;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        
        while(t-->0){
            n=in.nextInt();
            a=new long[n+1];
            ans=0;
            for(int i=0;i<=n;i++){
                a[i]=in.nextLong();
            }
            ArrayList<Long> l=new ArrayList<>();
            l.add(a[0]);
            fun(l,1);
            System.out.println(ans);
        }
    }
    
    static void fun(ArrayList<Long> l,int i){
        //System.out.println(l.toString()+" "+i);
        if(i==n+1){
            for(int j=0;j<l.size()-1;j++){
                ans=(ans+l.get(j)*l.get(j+1))%mod;
            }
            return;
        }
        else{
            //ans=(ans+(l.get(0)*a[i]%mod))%mod;
            l.add(0, a[i]);
            fun(l, i+1);
            l.remove(0);
            //ans=(ans+(l.get(l.size()-1)*a[i])%mod)%mod;
            l.add(a[i]);
            fun(l,i+1);
            
            l.remove(l.size()-1);
        }
    }
    
    
    
    
}

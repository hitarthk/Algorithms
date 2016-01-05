
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
public class c_330_a {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        long[] a=new long[n/k];
        long[] b=new long[n/k];
        for(int i=0;i<n/k;i++){
            a[i]=in.nextLong();
        }
        long mod=(long)1e9+7;
        long ans=1;
        for(int i=0;i<n/k;i++){
            b[i]=in.nextLong();
        }
        for(int i=0;i<n/k;i++){
            long temp=0;
            for(int j=0;j<=9;j++){
                if(j!=b[i]){
                    long firstM=(long)Math.pow(10, k-1)*j;
                    firstM%=a[i];
                    firstM=(a[i]-firstM)%a[i];
                    //System.out.println(i+" "+firstM);
                    long temp1=f(k-1,a[i],firstM);
//                    if(i==2){
//                        System.out.println(j+" "+temp1);
//                    }
                    temp+=temp1;
                    temp%=mod;
                }
            }
            //System.out.println(temp);
            ans=(ans*temp)%mod;
        }
        System.out.println(ans);
                
    }
    
    static long f(int l,long d,long rem){
        long first=rem;
        long range=(long)(Math.pow(10, l)-1)-(first-1);
        if(range<0){
            return 0;
        }
        //System.out.println("Range "+range);
        long ans=(long)Math.ceil((range+0.0)/d);
        //System.out.println("ans "+ans);
        return ans<0?0:ans;
    }
    
}

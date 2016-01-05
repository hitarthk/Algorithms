
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
public class c_321_d {
    static long[][] dp;
    static int n;
    static int m;
    static int k;
    static long[] a;
    static long[][] c;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        m=in.nextInt();
        k=in.nextInt();
        a=new long[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextLong();
        }
        c=new long[n][n];
        for(int i=0;i<k;i++){
            int x=in.nextInt()-1;
            int y=in.nextInt()-1;
            long cost=in.nextLong();
            c[x][y]=cost;
        }
        dp=new long[n][1<<n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Long.MIN_VALUE);
        }
        
        //sent=(1<<n)-1;
        System.out.println(sent);
        for(int i=0;1<<i<=sent;i++){
            dp[i][1<<i]=a[i];
        }
        for(int i=0;i<n;i++){
            f(sent,i);
        }
        long ans=Long.MIN_VALUE;
        for(int i=0;i<=sent;i++){
            if(count(i)==m){
                for(int j=0;j<n;j++){
                    if(dp[j][i]>ans){
                        ans=dp[j][i];
                    }
                }
            }
        }
        System.out.println(ans);
    }
    
    static int count(int n){
        int ans=0;
        while(n>0){
            ans+=n&1;
            n=n>>1;
        }
        return ans;
    }
    
    static int sent;
    static long f(int mask,int last){
        if(dp[last][mask]!=Long.MIN_VALUE){
            return dp[last][mask];
        }
         
        {
            int temp=1<<last;
            int check=mask & temp;
            
            if(check>0){
                int newmask=mask & (sent^check);
                long max=Long.MIN_VALUE;
                for(int j=0;j<n;j++){
                    if(j!=last && ((1<<j & mask)>0)){
                        long tmax=f(newmask,j)+c[j][last]+a[last];
                        if(tmax>max){
                            max=tmax;
                        }
                    }
                }
                dp[last][mask]=max;
            }
            return dp[last][mask];
        }
    }
}

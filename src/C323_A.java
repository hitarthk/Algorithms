
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
public class C323_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int t=in.nextInt();
        int[] a=new int[2*(n+1)];
        int[] f=new int[301];
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
            a[n+i]=a[i];
            f[a[i]]++;
        }
        
        int[][] dp=new int[2*(n+1)][2*(n+1)];
        
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                if(i==j){
                    dp[i][j]=1;
                }
                else{
                    
                    if (a[i] <= a[j]) {
                        int max = 1;
                        for (int k = j - 1; k > i; k--) {
                            if (a[k] <= a[j] && a[k]>=a[i]) {
                                max = dp[i][k];
                                break;
                            }
                        }
                        dp[i][j]=max+1;
                    }
                }
            }
        }
        
        int[][] dp2=new int[(n+1)][(n+1)];
        int ans=0;
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                int smax=0;
                if(i==j){
                    int max=0;
                        for(int k=n;k>j;k--){
                            if(a[k]>=a[j]){
                                max=dp[j][k];
                                break;
                            }
                        }
                    dp2[i][i]=dp[i][i]+(t-2)*f[a[i]]+max;
                    //smax=Math.max(dp[i][i]+(t-2)*f[a[i]]+max,);
                }
                else{
                    if(a[i]<=a[j]){
                        int max=0;
                        for(int k=n;k>j;k--){
                            if(a[k]>=a[j]){
                                max=dp[j][k];
                                break;
                            }
                        }
                        
                        for(int k=j-1;k>i;k--){
                            if(a[k]>=a[i] && a[k]<=a[j]){
                                int temp=dp[i][j]+(t-2)*f[a[j]]+max;
                                if(temp>smax){
                                    smax=temp;
                                }
                            }
                        }
                        dp2[i][j]=smax;
                        
                    }
                }
                if(smax>ans){
                            ans=smax;
                        }
            }
        }
        System.out.println(ans);
    }
}

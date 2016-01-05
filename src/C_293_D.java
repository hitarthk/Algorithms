
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
public class C_293_D {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        double p=in.nextDouble();
        double q=1-p;
        int t=in.nextInt();
        double[][] dp=new double[t+2][n+2];
        dp[0][0]=1;
        for(int i=1;i<=t;i++){
            for(int j=0;j<=n;j++){
                if(j==0){
                    dp[i][j]=q*dp[i-1][j];
                }
                else{
                    dp[i][j]=p*dp[i-1][j-1];
                    if(j<n){
                        dp[i][j]+=q*dp[i-1][j];
                    }
                    else{
                        dp[i][j]+=dp[i-1][j];
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        double ans=0;
        for(int i=0;i<=n;i++){
            ans+=i*dp[t][i];
        }
        System.out.println(ans);
    }
}

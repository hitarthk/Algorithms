
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
public class Zenefit_codesprint_1 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int[] c={0,1,2,5,10,20,50,100};
        int m=7;
        int n=in.nextInt();
        long[][] dp=new long[m+1][n+1];
        dp[0][0]=1;
        for(int i=1;i<=7;i++){
            for(int j=0;j<=n;j++){
                dp[i][j]=dp[i-1][j];
                if(j-c[i]>=0){
                    dp[i][j]+=dp[i][j-c[i]];
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}


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
public class SS {
    public static void main(String[] args) {
        long t1=System.currentTimeMillis();
        Scanner in=new Scanner(System.in);
        long mod=(long)(1e9+7);
        long[][] dp=new long[2][5002];
        long[][] sum=new long[2][5002];
        long[] ans=new long[5002];
        dp[0][1]=26;
        sum[0][1]=26;
        ans[1]=26;
        
        for(int i=2;i<=5000;i++){
            dp[0][i]=26;
            sum[0][i]=sum[0][i-1]+dp[0][i];
        }
        for(int i=2;i<=5000;i++){
            for(int j=1;j<=5000;j++){
                if(j==1){
                    dp[1][j]=26;
                    sum[1][j]=26*i;
                }
                else{
                    dp[1][j]=(25*sum[0][j-1]+26);
                    dp[1][j]-=dp[1][j]>=mod?mod:0;
                    sum[1][j]=((sum[1][j-1]+sum[0][j]));
                    sum[1][j]-=sum[1][j]>=mod?mod:0;
                    sum[1][j]+=dp[1][j];
                    sum[1][j]-=sum[1][j]>=mod?mod:0;
                    sum[1][j]-=sum[0][j-1];
                    sum[1][j]+=sum[1][j]<0?mod:0;
                }
                if(i==j){
                    ans[i]=sum[1][i];
                }
            }
            dp[0]=Arrays.copyOf(dp[1], dp[0].length);
            sum[0]=Arrays.copyOf(sum[1], sum[0].length);
        }
        long t2=System.currentTimeMillis();
        System.out.println(t2-t1);
        int t=in.nextInt();
        while(t-->0){
            int n=in.nextInt();
            System.out.println(ans[n]);
        }
    }
}

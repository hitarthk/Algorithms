/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class ShortestPathWithMagic {
    public double getTime(String[] dist, int k){
        int n=dist.length;
        double[][] adj=new double[dist.length][dist.length];
        for(int i=0;i<dist.length;i++){
            for(int j=0;j<dist.length;j++){
                adj[i][j]=dist[i].charAt(j)-'0';
            }
        }
        
        double[][] dp=new double[n][k+1];
        for(int i=1;i<n;i++){
            for(int j=0;j<=k;j++){
                dp[i][j]=Double.MAX_VALUE;
            }
        }
        
        for(int loop=1;loop<=n-1;loop++){
            for(int j=0;j<=k;j++){
                for(int l=0;l<n;l++){
                    if(dp[l][k]==Double.MAX_VALUE){
                        continue;
                    }
                    for(int m=0;m<n;m++){
                        if(l!=m){
                            if(j==0){
                                dp[m][0]=Math.min(dp[m][0], dp[l][0]+adj[l][m]);
                            }
                            else{
                                dp[m][j]=Math.min(dp[m][j], Math.min(dp[l][j-1]+adj[l][m]/2.0,dp[l][j]+adj[l][m]));
                            }
                        }
                    }
                }
            }
        }
        return dp[1][k];
    }
}

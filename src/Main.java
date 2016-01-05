

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



public class Main {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int mod = 1000000007;
        int n = in.nextInt();
        int m = in.nextInt();
        long mat[][] = new long[n + 1][m + 1];
        long dp[][] = new long[n + 1][m + 1];
        long rp[][] = new long[n + 1][m + 1];
        long cp[][] = new long[n + 1][m + 1];
        long dip[][] = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                mat[i][j] = in.nextLong();
            }
        }
        dp[0][0] = 1;

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1;

        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = mat[i][j];
                    rp[i][j] = dp[i][j];
                    cp[i][j] = dp[i][j];
                    dip[i][j] = dp[i][j];
                    continue;
                } else if (mat[i][j] != 0) {
                    dp[i][j] = ((rp[i - 1][j] + cp[i][j - 1] + dip[i - 1][j - 1]) % mod * mat[i][j]) % mod;
                    rp[i][j] = (rp[i - 1][j] + dp[i][j]) % mod;
                    cp[i][j] = (cp[i][j - 1] + dp[i][j]) % mod;
                    dip[i][j] = (dip[i - 1][j - 1] + dp[i][j]) % mod;
                }
//System.out.println(dp[i][j]+" "+rp[i][j]+" "+cp[i][j]+" "+dip[i][j]);

            }
        }
        System.out.println(dp[n][m]);

    }
}

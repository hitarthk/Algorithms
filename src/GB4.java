
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hitarthk
 */
public class GB4 {

    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        StringBuilder s = new StringBuilder(" " + in.nextLine() + " ");
        for (int i = 1; i <= n; i++) {
            s.append(" ");
        }
//        StringBuilder s=new StringBuilder();
//        for(int i=0;i<=2*n;i++){
//            s.append(9);
//        }
        //System.out.println(s);
        int[][] dp = new int[n + 2][n + 2];
        int[][] prefix = new int[n + 2][n + 2];
        int[][] match = new int[2*n + 2][n + 2];
        for (int i = 0; i < match.length; i++) {
            for (int j = 0; j < match[i].length; j++) {
                match[i][j] = -1;
            }
        }
        for (int length = 1; length <= n; length++) {
            for (int i = n; i>=1; i--) {
                if (s.charAt(i) != s.charAt(i + length)) {
                    match[i + length][length] = 0;
                } else {
                    if (match[i+1+length][length] == length || match[i +1+ length][length] == -1) {
                        match[i + length][length] = -1;
                    } else {
                        match[i + length][length] = match[i +1+ length][length] + 1;
                    }
                }
            }
        }
//        for(int i=1;i<=n;i++){
//            System.out.println(i+" "+Arrays.toString(match[i]));
//        }
        for (int j = 1; j <= n; j++) {
            for (int i = j; i >= 2; i--) {
                if (s.charAt(i) != '0') {
                    dp[i][j] = prefix[i - 1][i - 1] - prefix[i - 1][Math.max(i - 1 - (j - i), 0)];
                    dp[i][j] += dp[i][j] < 0 ? mod : 0;
                    if (match[i][(j - i) + 1] != -1) {
                        if (i - 1 - (j - i) > 0) {
                            if (s.charAt(i - 1 - (j - i) + match[i][j - i + 1]) < s.charAt(i + match[i][j - i + 1])) {
                                dp[i][j] = (dp[i][j] + dp[i - 1 - (j - i)][i - 1]) % mod;
                            }
                        }
                    }
                }
            }
            dp[1][j] = 1;
            for (int i = 1; i <= j; i++) {
                prefix[j][i] = prefix[j][i - 1] + dp[i][j];
                prefix[j][i] -= prefix[j][i] >= mod ? mod : 0;
            }
        }
//        for(int i=1;i<=n;i++){
//            System.out.println(i+" "+Arrays.toString(dp[i]));
//        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dp[i][n];
            ans -= ans >= mod ? mod : 0;
        }
        System.out.println(ans);
    }

}

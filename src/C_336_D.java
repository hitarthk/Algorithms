
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
public class C_336_D {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        int[][] dp = new int[n + 2][n + 2];

        for (int length = 1; length <= n; length++) {
            for (int i = 1; i + length - 1 <= n; i++) {
                if (length == 1) {
                    dp[i][i] = 1;
                } else if (length == 2) {
                    if (a[i] == a[i + 1]) {
                        dp[i][i + 1] = 1;
                    } else {
                        dp[i][i + 1] = 2;
                    }
                } else {
                    if (a[i] == a[i + length - 1]) {
                        dp[i][i + length - 1] = dp[i + 1][i + length - 2];
                    } else {
                        dp[i][i + length - 1] = Integer.MAX_VALUE;
                    }
                    for (int j = i; j <= i + length - 2; j++) {
                        dp[i][i + length - 1] = Math.min(dp[i][i + length - 1], dp[i][j] + dp[j + 1][i + length - 1]);
                    }

                }
            }

        }
        System.out.println(dp[1][n]);

    }
}

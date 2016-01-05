
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
public class C_336_C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int max = (int) (1e6);
        int[] a = new int[max + 5];
        int n = in.nextInt();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() + 1;
            int y = in.nextInt();
            a[x] = y;
            if (x < min) {
                min = x;
            }
        }

        int[] prefix = new int[max + 5];

        for (int i = 1; i <= max+2; i++) {
            prefix[i] = prefix[i - 1] + (a[i] > 0 ? 1 : 0);
        }
        //System.out.println(Arrays.toString(prefix));
        int[] dp = new int[max + 5];

        for (int i = 1; i <= max + 2; i++) {
            if (a[i] > 0) {
                dp[i] = dp[Math.max(i - a[i]-1, 0)] + prefix[i-1] - prefix[Math.max(i - a[i] - 1, 0)];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        //System.out.println(Arrays.toString(dp));
        
        int ans = Integer.MAX_VALUE;
        for (int i = max + 2; i >= 1; i--) {
            if (prefix[max+2]-prefix[i]+dp[i-1]<ans) {
                ans=prefix[max+2]-prefix[i]+dp[i];
                //System.out.println(i);
            }
        }
        System.out.println(ans);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APAC;

import java.util.Scanner;

/**
 *
 * @author hitarthk
 */
public class APACD_B {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int q = in.nextInt();
            int[] v = new int[m];
            for (int i = 0; i < m; i++) {
                v[i] = in.nextInt();
            }
            int[][] p = new int[n][2];
            boolean check = true;
            for (int i = 0; i < n; i++) {
                p[i][0] = in.nextInt();
                p[i][1] = in.nextInt();
                if (p[i][0] != 0) {
                    check = false;
                }
            }
            if (check) {
                System.out.println("Case #" + t + ": " + 0);
                continue;
            }
            check = true;
            for (int i = 0; i < n; i++) {
                if (p[i][0] != 0) {
                    boolean isThere = false;
                    for (int j = 0; j < m; j++) {
                        if (p[i][0] * v[j] < 0) {
                            isThere = true;
                            break;
                        }
                    }
                    if (!isThere) {
                        check = false;
                        break;
                    }
                }
            }
            if (!check) {
                System.out.println("Case #" + t + ": IMPOSSIBLE");
                continue;
            }
            int l = 0;
            int r = 1000000;

            A:
            //System.out.println("|||||");
            while (r - l > 1) {
                int mid = (l + r) / 2;
                int totalCost = 0;
                for (int i = 0; i < n; i++) {
                    if (p[i][0] != 0) {
                        int min = Integer.MAX_VALUE;
                        for (int j = 0; j < m; j++) {
                            if (v[j] * p[i][0] < 0) {
                                if (Math.abs((int) Math.ceil(Math.abs(p[i][0] + 0.0) / Math.abs(v[j]))) <= mid) {

                                    //System.out.println("Math.abs((int)Math.ceil((p[i][0]+0.0)/(v[j]))) "+Math.abs(Math.ceil(Math.abs(p[i][0]+0.0)/Math.abs(v[j])))+" "+p[i][0]+" "+v[j]);
                                    if (Math.abs(j - p[i][1]) < min) {
                                        min = Math.abs(j - p[i][1]);
                                    }
                                }
                            }
                        }

                        if (min == Integer.MAX_VALUE) {
                            totalCost = Integer.MAX_VALUE;
                            break;
                        } else {
                            totalCost += min;
                        }
                    }
                }

                if (totalCost <= q) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            if (r <= 100000) {
                System.out.println("Case #" + t + ": " + r);
            } else {
                System.out.println("Case #" + t + ": IMPOSSIBLE");
            }
        }
    }
}

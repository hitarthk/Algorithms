
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
public class C_334_C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        char[] a = in.nextLine().toCharArray();
        //System.out.println(Arrays.toString(a));
        int[][] tree = new int[4 * n][1 + 2 + 2];
        build(1, 0, n - 1, tree, a);
        System.out.println(Math.min(n, tree[1][4] + 2));
    }

    static void build(int node, int s, int e, int[][] tree, char[] a) {
        if (s == e) {
            tree[node][4] = 1;
            if (a[s] == '0') {
                tree[node][0] = 1;
            } else {
                tree[node][3] = 1;
            }
            //System.out.println(s+" "+e+" "+Arrays.toString(tree[node]));
        } else {
            int mid = (s + e) >> 1;
            build(2 * node, s, mid, tree, a);
            build(2 * node + 1, mid + 1, e, tree, a);

            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
            //System.out.println(s+" "+e+" "+Arrays.toString(tree[node]));
        }
    }

    static int[] merge(int[] l, int[] r) {
        int[] ans = new int[5];
        ans[0] = Math.max(l[0], r[0]);
        if (l[0] > 0 && r[2] > 0) {
            ans[0] = Math.max(ans[0], l[0] + r[2]);
        }
        if (l[1] > 0 && r[0] > 0) {
            ans[0] = Math.max(ans[0], l[1] + r[0]);
        }

        ans[1] = Math.max(l[1], r[1]);
        if (l[0] > 0 && r[3] > 0) {
            ans[1] = Math.max(ans[1], l[0] + r[3]);
        }
        if (l[1] > 0 && r[1] > 0) {
            ans[1] = Math.max(ans[1], l[1] + r[1]);
        }

        ans[2] = Math.max(l[2], r[2]);
        if (l[2] > 0 && r[2] > 0) {
            ans[2] = Math.max(ans[2], l[2] + r[2]);
        }
        if (l[3] > 0 && r[0] > 0) {
            ans[2] = Math.max(ans[2], l[3] + r[0]);
        }

        ans[3] = Math.max(l[3], r[3]);
        if (l[2] > 0 && r[3] > 0) {
            ans[3] = Math.max(ans[3], l[2] + r[3]);
        }
        if (l[3] > 0 && r[1] > 0) {
            ans[3] = Math.max(ans[3], l[3] + r[1]);
        }

        ans[4] = Math.max(l[4], r[4]);
        for (int i = 0; i < 4; i++) {
            ans[4] = Math.max(ans[4], ans[i]);
        }
        return ans;
    }

}


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
public class C_290_B {

    static char[][] a;
    static int n;
    static int m;
    static int[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        a = new char[n + 2][m + 2];
        in.nextLine();
        for (int i = 1; i <= n; i++) {
            String s = " " + in.nextLine() + " ";
            a[i] = s.toCharArray();
        }
        //System.out.println(Arrays.deepToString(a));
        boolean check = true;

        for (int i = 0; i < 26; i++) {
            boolean f = dfs(i);
            if (f) {
                check = false;
                break;
            }
        }

        if (check) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    static boolean dfs(int color) {
        v = new int[n + 2][m + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(v[i], -1);
        }
        boolean ret = false;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (v[i][j] == -1 && a[i][j] - 'A' == color) {
                    ret |= visit(color, 0, i, j);
                }
            }
        }
        return ret;
    }

    static boolean visit(int color, int time, int r, int c) {
        v[r][c] = time;
        boolean ret = false;
        for (int i = 0; i < dx.length; i++) {
            if (a[r + dy[i]][c + dx[i]] - 'A' == color) {
                if (v[r + dy[i]][c + dx[i]] == -1) {
                    ret |= visit(color, time + 1, r + dy[i], c + dx[i]);
                } else {
                    if (v[r + dy[i]][c + dx[i]] - time >= 3) {
                        //System.out.println("color "+color+" "+r+" "+c);
                        return true;
                    }
                }
            }
        }
        return ret;
    }

}

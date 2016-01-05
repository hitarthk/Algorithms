/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author hitarthk
 */
public class MO {

    static int[] a;
    static int[] cnt;
    static int answer;
    static int[] ans;

    public static void solve(Query[] q, int m) {
        int currentL = 0, currentR = 0;
        for (int i = 0; i < m; i++) {
            int L = q[i].L, R = q[i].R;
            while (currentL < L) {
                remove(currentL);
                currentL++;
            }
            while (currentL > L) {
                add(currentL - 1);
                currentL--;
            }
            while (currentR <= R) {
                add(currentR);
                currentR++;
            }
            while (currentR > R + 1) {
                remove(currentR - 1);
                currentR--;
            }
            ans[q[i].id] = answer;
        }

    }

    static class Query implements Comparable<Query> {

        int id;
        int L;
        int R;

        public Query(int id, int l, int r) {
            this.id = id;
            this.L = l;
            this.R = r;
        }

        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int tb = this.L / 320;
            int ob = o.L / 320;
            if (tb == ob) {
                return this.R - o.R;
            } else {
                return tb - ob;
            }
        }
    }

    static void add(int position) {
        cnt[a[position]]++;
        if (cnt[a[position]] == 1) {
            answer++;
        }
    }

    static void remove(int position) {
        cnt[a[position]]--;
        if (cnt[a[position]] == 0) {
            answer--;
        }
    }

}

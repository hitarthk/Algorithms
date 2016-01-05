
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template i+n the editor.
 */
/**
 *
 * @author hitarthk
 */
public class C_310_D {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] l = new long[n];
        long[] r = new long[n];
        for (int i = 0; i < n; i++) {
            l[i] = in.nextLong();
            r[i] = in.nextLong();
        }
        Bridge[] bridges = new Bridge[m];
        for (int i = 0; i < m; i++) {
            bridges[i] = new Bridge();
            bridges[i].length = in.nextLong();
            bridges[i].id = i + 1;
        }
        Arrays.sort(bridges);
        Pair[] p = new Pair[n - 1];
        for (int i = 0; i < n - 1; i++) {
            p[i] = new Pair();
            p[i].l = l[i + 1] - r[i];
            p[i].r = r[i + 1] - l[i];
            p[i].id=i;
        }
        Arrays.sort(p);
        //System.out.println(Arrays.toString(p));
        TreeSet<Pair> t = new TreeSet<>(new Comp());
        int add = 0;
        boolean check = true;
        Arrays.sort(bridges);
        for (int i = 0; i < m; i++) {
            while (add < n - 1 && p[add].l <= bridges[i].length) {
                t.add(p[add]);
                add++;
            }

            if (t.isEmpty() && add == n - 1) {
                break;
            }
            if (!t.isEmpty()) {
                Pair cur = t.first();
                //System.out.println(cur);
                if (cur.r >= bridges[i].length) {
                    cur.bid = bridges[i].id;
                    t.remove(cur);
                } else {
                //check=false;
                    //System.out.println("Here "+i);
                    break;
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (p[i].bid == 0) {
                check = false;
                break;
            }
        }
        if (check) {
            System.out.println("Yes");
            int[] ans=new int[n-1];
            for (int i = 0; i < n - 1; i++) {
                ans[p[i].id]=p[i].bid;
            }
            for(int i=0;i<n-1;i++){
                System.out.print(ans[i]+" ");
            }
        } else {
            System.out.println("No");
        }
    }

    static class Comp implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (o1.r == o2.r) {
                if (o1.l == o2.l) {
                    return o1.id - o2.id;

                } else if (o1.l < o2.l) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (o1.r < o2.r) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }

    }

    static class Bridge implements Comparable<Bridge> {

        long length;
        int id;

        @Override
        public int compareTo(Bridge o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (this.length == o.length) {
                return this.id - o.id;
            } else {
                if (this.length < o.length) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }

    }

    static class Pair implements Comparable<Pair> {

        long l;
        long r;
        int id;
        int bid;

        @Override
        public int compareTo(Pair o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (this.l == o.l) {
                if (this.r == o.r) {
                    return this.id - o.id;
                } else if (this.r < o.r) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (this.l < o.l) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }

        public String toString() {
            return l + " " + r;
        }
    }

}

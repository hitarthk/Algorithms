
import java.util.HashMap;
import java.util.Map;
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
public class C_332_C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        int[] sufmin = new int[n + 2];
        sufmin[n + 1] = Integer.MAX_VALUE;
        for (int i = n; i >= 1; i--) {
            sufmin[i] = Math.min(sufmin[i + 1], a[i]);
        }
        int count=0;
        for (int i = 1; i <= n; i++) {
            int arraymax = a[i];
            i++;
            while (i <= n && sufmin[i] < arraymax) {
                arraymax = Math.max(arraymax, a[i]);
                i++;
            }
            count++;

            if (i == n+1) {
                break;
            }
            i--;
        }
        System.out.println(count);
    }

    static class Pair implements Comparable<Pair> {

        int pos;
        int value;

        public Pair(int value, int pos) {
            this.value = value;
            this.pos = pos;
        }

        @Override
        public int compareTo(Pair o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (this.value == o.value) {
                return this.pos - o.pos;
            } else {
                return this.value - o.value;
            }
        }

    }

}

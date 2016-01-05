package APAC;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class E {

    public static void main(String cd[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println("Case #" + (i + 1) + ":");
            int n = in.nextInt();
            int m = in.nextInt();
            BigDecimal a[] = new BigDecimal[n];
            for (int j = 0; j < n; j++) {
                a[j] = new BigDecimal(new BigInteger(in.next()));
                if (j != 0) {
                    a[j] = a[j].multiply(a[j - 1]);
                }
            }
            for (int j = 0; j < m; j++) {
                int l = in.nextInt();
                int r = in.nextInt();
                if (l != 0) {
                    BigDecimal ans = a[r].divide(a[l - 1]);
                    String strDouble = String.format("%.9f", nthroot(r - l + 1, ans));
                    System.out.println(strDouble);

                } else {
                    BigDecimal ans = a[r];
                    String strDouble = String.format("%.9f", nthroot(r - l + 1, ans));
                    System.out.println(strDouble);

                }
            }
        }
    }

    public static double nthroot(int n, BigDecimal x) {
        return nthroot(n, x, new BigDecimal(0.00001));
    }

    public static double nthroot(int n, BigDecimal x, BigDecimal p) {
        if (x.doubleValue() < 0) {
            System.err.println("Negative!");
            return -1;
        }
        if (x.doubleValue() == 0) {
            return 0;
        }
        BigDecimal x1 = x;
        BigDecimal x2 = x.divide(new BigDecimal(n),5,RoundingMode.UP);
        while ((x1.subtract(x2).abs()).compareTo(p) > 0) {
            x1 = x2;
            //x2 = (new BigDecimal((n - 1.0)).multiply(x2).add(x.divide((x2.pow(n - 1).divide(new BigDecimal(n))))));
            BigDecimal f=new BigDecimal(n-1.0).multiply(x2);
            //System.out.println(x2.pow(n-1));
            BigDecimal s=x.divide(x2.pow(n-1), 5, RoundingMode.UP);
            
            x2=f.add(s).divide(new BigDecimal(n+0.0),5,RoundingMode.UP);
        }
        return x2.doubleValue();
    }
}

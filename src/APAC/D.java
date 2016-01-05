package APAC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class D {

    public static void main(String cd[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/home/hitarthk/Desktop/B-small-attempt0.in"));
        int T = in.nextInt();
        PrintWriter out=new PrintWriter("output");
        for (int t = 1; t <=T; t++) {
            out.println("Case #" + (t) + ":");
            int n = in.nextInt();
            int m = in.nextInt();
            BigDecimal a[] = new BigDecimal[n];
            for (int j = 0; j < n; j++) {
                a[j] = new BigDecimal(new BigInteger(in.next()));
                if (j != 0) {
                    a[j] = a[j].multiply(a[j - 1]);
                }
            }
            for (int j = 1; j <= m; j++) {
                int l = in.nextInt();
                int r = in.nextInt();
                double v;
                if (l != 0) {
                    v = a[r].divide(a[l - 1]).doubleValue();
                    

                } else {
                    v = a[r].doubleValue();

                }
                String answer = String.format("%.9f", solve(r - l + 1, v,0.0000001));
                out.println(answer);
            }
        }
        out.close();
    }

    
    public static double solve(int n, double x, double p) {
        if (x < 0) {
            System.err.println("Negative!");
            return -1;
        }
        if (x == 0) {
            return 0;
        }
        double x1 = x;
        double x2 = x / n;
        while (Math.abs(x1 - x2) > p) {
            x1 = x2;
            x2 = ((n - 1.0) * x2 + x / Math.pow(x2, n - 1.0)) / n;
        }
        return x2;
    }
}


		


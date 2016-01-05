
import java.util.ArrayList;
import java.util.HashSet;
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
class GW6 {

    

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        long finalAnswer = 0;
        if (a == 0 && b == 0) {
            System.out.println(0);
            return;
        } else if (a == 0 || b == 0) {
            System.out.println(1);
            return;
        } else {
            HashSet<Integer> h = finduniquePrimes(a + b);
            makeTable(a + b, mod);
            for (int segmentSize = 2; segmentSize <= a; segmentSize++) {
                if (h.contains(segmentSize) && a % segmentSize == 0 && b % segmentSize == 0) {
                    int t = (a + b) / segmentSize;
                    finalAnswer = (finalAnswer + findncrprod(t, a / segmentSize, mod)) % mod;
                }
            }
            System.out.println(finalAnswer);
        }
        
    }

    public static long ncr[][];
    static int mod=10000001;
    private static void makeTable(long z, long p) {
        ncr = new long[(int) z + 1][(int) z + 1];
        ncr[0][0] = 1;
        for (int i = 1; i <= z; i++) {
            ncr[i][0] = 1;
            ncr[0][i] = 0;
        }
        for (int i = 1; i <= z; i++) {
            for (int j = 1; j <= z; j++) {
                ncr[i][j] = (ncr[i - 1][j] + ncr[i - 1][j - 1]) % p;
            }
        }
    }

    private static long findncrprod(long n, long k, long p) {

        long ans = 1;

        while (n > 0) {
            int tn = (int) (n % p);
            int tk = (int) (k % p);
            
            if (tk > tn) {
                return 0;
            }
            ans *= (ncr[tn][tk]);
            n /= p;
            k /= p;
        }
        return ans % p;
    }
    
    
    public static HashSet<Integer> finduniquePrimes(int n) {
        return new HashSet<Integer>(findPrimes(n));
    }
    
    public static ArrayList<Integer> findPrimes(int n) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }

    

}

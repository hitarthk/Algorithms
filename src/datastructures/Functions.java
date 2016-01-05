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
public class Functions {
    static long modPow( long a, long b, long mod )
    {
    	long res=1,pow=a;
    	while( b>0 )
    	{
    		if( (b&1)==1 )
    		{
    			res = (pow*res)%mod;
    		}
    		pow = (pow*pow)%mod;
    		b=b/2;
    	}
    	return res;
    }
    
    public static long modInverse(long a, long p) {
		// calculates the modular multiplicative inverse of a mod m.
        // (assuming p is prime).
        return modPow(a, p - 2, p);
    }

    public static long ncr[][];
    private static void generateNCR( long z, long p )
    {
    	ncr = new long[(int)z+1][(int)z+1];
    	ncr[0][0] = 1;
    	for(int i=1; i<=z; i++)
    	{
    		ncr[i][0] = 1;
    		ncr[0][i] = 0;
    	}
        for(int i=1; i<=z; i++)
        {
            for(int j=1; j<=z; j++)
            {
                ncr[i][j] = (ncr[i-1][j] + ncr[i-1][j-1]) % p;
            }
        }
    }
    
    static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
}

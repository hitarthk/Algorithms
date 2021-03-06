
//import static datastructures.Functions.ncr;
import java.util.HashMap;
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
public class GW {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int a=in.nextInt();
        int b=in.nextInt();
        
        
        int g=gcd(a,b);
        long mod=(long)1e7+1;
        
         if((a==0 && b==0) || (a==1&& b==0)|| (a==1&& b==0))
        {
            System.out.println(0);
            return;
        }
        if(g==1)
            System.out.println(0);
        else {
            System.out.println(g);
            
            g=Math.min(g, (a+b)/g);
            System.out.println(g);
            generateNCR((long)((a+b)/g), 10000001);
            System.out.println(ncr[a/g + b/g][a/g]);
        }
        
    }
    static long[][] ncr;
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

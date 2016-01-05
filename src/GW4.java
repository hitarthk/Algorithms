
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


 

class NewClass
{
	static int mod=10000001;
	
    public static void main(String[] args) throws Exception
    {
        Scanner in=new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        long res=0;
        if( a==0 && b==0 )
        	res=0;
        else if( a==0 || b==0 )
        {
        	res=1;
        }
        else
        {
        	HashSet<Integer> h = uniquePrimeFactors(a+b);
            generateNCR( a+b,mod );
            for( int i=2 ; i<=a ; i++ )
            {
            	if( h.contains(i) && a%i==0 && b%i==0 )
            	{
            		int t = (a+b)/i;
            		res = (res+lucas(t,a/i,mod))%mod;
            	}
            }
        }
        System.out.println(res);
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

    
    private static long lucas(long n, long k, long p) 
    {
    
        long ans = 1;
        
        while(n>0)
        {
            int N = (int)(n%p);
            int K = (int)(k%p);
            if(K>N)
                return 0;
            ans *= (ncr[N][K] );
            n /= p;
            k /= p;
        }
        return ans % p;
    }
    
	public static ArrayList<Integer> primeFactors( int n )
  	{
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for( int i=2 ; i<=n/i ; i++ ) 
		{
			while( n%i==0 ) 
			{
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) 
		{
			factors.add(n);
		}
		return factors;
	}
  	
  	public static HashSet<Integer> uniquePrimeFactors(int n) 
  	{
		return new HashSet<Integer>(primeFactors(n));
	}
   
 
    
}
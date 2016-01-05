import java.util.Arrays;
import java.util.Scanner;

 class q3 
{

	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		String a=s.nextLine();
		String b=s.nextLine();
		int fa[]=new int[300];
		int fb[]=new int[300];
		String sa[]=a.split(" ");
		
	//System.out.println(a+" "+b);
		String sb[]=b.split(" ");
		String tmpa="";
		for(int i=0;i<sa.length;i++)
			tmpa+=sa[i];
		
		String tmpb="";
		for(int i=0;i<sb.length;i++)
			tmpb+=sb[i];
		
		dp=new int[a.length()+1][b.length()+1];
		System.out.println(editDist(a, b, a.length(), b.length()));
//		System.out.println(Arrays.toString(sa)+" "+Arrays.toString(sb));
//		for(int k=0;k<sa.length;k++)
//		for(int i=0;i<sa[k].length();i++)
//			fa[sa[k].charAt(i)]++;
//		
//		for(int k=0;k<sb.l ength;k++)
//		for(int i=0;i<sb[k].length();i++)
//			fb[sb[k].charAt(i)]++;
//		
//		
//		long ans=0;
//		
//		for(int i=0;i<300;i++)
//			ans+=Math.abs(fa[i]-fb[i]);
//		
//		System.out.println(ans);
	}
	
	public static int min(int x, int y, int z) 
	{
	   return Math.min(Math.min(x, y), z);
	}
	static int dp[][];
	public static int editDist(String str1 , String str2 , int m ,int n)
	{
	    // If first string is empty, the only option is to
	    // insert all characters of second string into first
	    if (m == 0) return n;

	    // If second string is empty, the only option is to
	    // remove all characters of first string
	    if (n == 0) return m;
	    if(dp[m][n]!=0)
	    	return dp[m][n];
	    // If last characters of two strings are same, nothing
	    // much to do. Ignore last characters and get count for
	    // remaining strings.
	    if (str1.charAt(m-1) == str2.charAt(n-1))
	        return editDist(str1, str2, m-1, n-1);

	    // If last characters are not same, consider all three
	    // operations on last character of first string, recursively
	    // compute minimum cost for all three operations and take
	    // minimum of three values.
	    dp[m][n]= 1 + min ( editDist(str1,  str2, m, n-1),    // Insert
	                     editDist(str1,  str2, m-1, n),   // Remove
	                    (1+editDist(str1,str2,m-1,n-1)) // Replace                     
	                   );
	    return dp[m][n];
	}
	
}
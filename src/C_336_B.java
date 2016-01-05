
import java.util.Arrays;
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
public class C_336_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        char[] a=(" "+in.nextLine()).toCharArray();
        char[] b=(" "+in.nextLine()).toCharArray();
        int lena=a.length-1;
        int lenb=b.length-1;
        //System.out.println(Arrays.toString(a)+" "+Arrays.toString(b));
//        for(int i=0;i<a.length;i++){
//            a[i]-='0';
//        }
//        for(int i=0;i<b.length;i++){
//            b[i]-='0';
//        }
        int[] prefix=new int[lenb+2];
        prefix[0]=0;
        for(int i=1;i<=lenb;i++)
            prefix[i]=prefix[i-1]+b[i]-'0';
        //System.out.println(Arrays.toString(prefix)+" "+Arrays.toString(a)+" "+Arrays.toString(b) );
        long ans=0;
        for(int i=1;i<=lena;i++){
            if(a[i]=='1'){
                ans+=lenb-lena+1-(prefix[lenb-((lena-i))]-prefix[i-1]);
            }
            else{
                ans+=prefix[lenb-(lena-i)]-prefix[i-1];
            }
        }
        System.out.println(ans);
        
        
    }
}

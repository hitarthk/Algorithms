
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
public class c_275_b {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        long cnt1=in.nextLong();
        long cnt2=in.nextLong();
        long x=in.nextLong();
        long y=in.nextLong();
        long l=0;
        long r=(long)1e10;
        while(r-l>1){
            long m=(l+r)/2;
            long n1=m-m/x;
            long n2=m-m/y;
            long lcm;
            if(x==y){
                lcm=x;
            }
            else{
                lcm=x*y;
            }
            long total=n1+n2-(m-(m/x+m/y-m/(lcm)));
            //System.out.println(m+" "+total+" "+n1 +" "+n2);
            if(total>=cnt1+cnt2 && n1>=cnt1 && n2>=cnt2){
                r=m;
            }
            else
                l=m;
        }
        System.out.println(r);
    }
}


import java.math.BigInteger;
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
public class c_328_c {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        BigInteger t=new BigInteger(in.next());
        BigInteger a=new BigInteger(in.next());
        BigInteger b=new BigInteger(in.next());
        BigInteger lcm=a.multiply(b);
        lcm=lcm.divide(a.gcd(b));
        BigInteger mul=t.divide(lcm);
        BigInteger ans=new BigInteger("0");
        //System.out.println("multiples "+mul);
        if(mul.compareTo(new BigInteger("1"))>=0){
            ans=ans.add(mul.subtract(new BigInteger("1")).multiply(a.min(b)));
        }
        //System.out.println(ans);
        BigInteger rem=t.remainder(lcm);
        rem=rem.add(new BigInteger("1"));
        //System.out.println("Reamiander "+rem);
        BigInteger min=a.min(b);
        //System.out.println("min "+min);
        if(mul.compareTo(new BigInteger("0"))>0){
        if(rem.compareTo(min)>=0){
            ans=ans.add(min);
        }
        else{
            ans=ans.add(rem);
        }
        }
        //System.out.println(ans);
        {
            if(t.compareTo(min.subtract(new BigInteger("1")))>=0){
                ans=ans.add(min.subtract(new BigInteger("1")));
            }
            else{
                ans=ans.add(t);
            }
        }
        //System.out.println(ans);
        BigInteger p=ans.divide(ans.gcd(t));
        BigInteger q=t.divide(ans.gcd(t));
        System.out.println(p+"/"+q);
    }
    
    static long gcd(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        while (b > 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
}

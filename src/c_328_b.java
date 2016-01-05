
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
public class c_328_b {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        long n=in.nextLong();
        long ans=(n-3)*(n-1)+1;
        System.out.println(ans);
    }
}

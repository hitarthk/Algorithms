
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
public class PracticeC {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        double h1=in.nextDouble();
        double t1=in.nextDouble();
        double h2=in.nextDouble();
        double t2=in.nextDouble();
        double d=(Math.sqrt(h1)*t2-Math.sqrt(h2)*t1)/(Math.sqrt(h1)-Math.sqrt(h2));
        System.out.println(d);
    }
}


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
public class C_334_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        double[] m=new double[5];
        double[] w=new double[5];
        double[] x={500,1000,1500,2000,2500};
        for(int i=0;i<5;i++){
            m[i]=in.nextDouble();
        }
        for(int i=0;i<5;i++){
            w[i]=in.nextDouble();
        }
        int hs=in.nextInt();
        int hf=in.nextInt();
        double ans=0;
        for(int i=0;i<5;i++){
            ans+=Math.max(0.3*x[i], x[i]-(m[i]*x[i])/250 - 50*w[i]);
        }
        ans+=hs*100-hf*50;
        int fans=(int)ans;
        System.out.println(fans);
    }
}

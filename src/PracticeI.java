
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
public class PracticeI {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int[] a=new int[1827];
        int n=in.nextInt();
        for(int i=0;i<n;i++){
            int start=in.nextInt();
            int end=in.nextInt();
            for(int j=start;j<=end;j++){
                a[j]=1;
            }
        }
        int[] sum=new int[1827];
        for(int i=1;i<=1826;i++){
            sum[i]=sum[i-1]+a[i];
        }
        boolean check=true;
        for(int i=180;i<=1826;i++){
            //System.out.println(sum[i]-sum[i-180]);
            if(sum[i]-sum[i-180]>90){
                check=false;
                break;
            }
        }
        if(check){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
}

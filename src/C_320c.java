
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
public class C_320c {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        double a=in.nextDouble();
        double b=in.nextDouble();
        double min=Integer.MAX_VALUE;
        if(a>b){
            double f=(a-b)/2;
            //double p=Math.ceil(f);
            if(b==(a-b)/2){
                double ans=(a-b)/2;
                //System.out.println(ans);
                min=ans;
            }
            
            else{
//                int l=1;
//                int r=Integer.MAX_VALUE;
//                while(r-l>1){
//                    int mid=l+(r-l)/2;
//                    if((a-b)/(2*mid)==b){
//                        l=mid;
//                        break;
//                    }
//                    else if((a-b)/(2*mid)>b){
//                        l=mid;
//                    }
//                    else{
//                        r=mid;
//                    }
//                    
//                }
//                //System.out.println((a-b)/(2*l));
//                min=f/l;
                int p=(int)((a-b)/(2*b));
                min=f/p;
            }
                
        }
         if(a==b){
            System.out.println(a);
            System.exit(0);
        }
         {
            double f=(a+b)/2;
            if(b==(a+b)/2){
                //System.out.println((a+b)/2);
                if((a+b)/2<min)
                    min=(a+b)/2;
            }
            else if(b>f){
                System.out.println(-1);
                System.exit(0);
            }
            else{
//                int l=1;
//                int r=Integer.MAX_VALUE;
//                while(r-l>1){
//                    int mid=l+(r-l)/2;
//                    if((a+b)/(2*mid)==b){
//                        l=mid;
//                        break;
//                    }
//                    else if((a+b)/(2*mid)>b){
//                        l=mid;
//                    }
//                    else{
//                        r=mid;
//                    }
//                    
//                }
                //System.out.println((f/l));
                int p=(int)((a+b)/(2*b));
                if(f/p<min)
                    min=f/p;
            }
            
        }
         System.out.println(min);
    }
}


import java.io.PrintWriter;
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
public class ZenefitsQueueValo {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        int b=m+1;
        PrintWriter out=new PrintWriter(System.out);
        double[] dp=new double[(int)(Math.pow(b, k))];
        dp[0]=1;
        double ans=0;
        for(int i=1;i<=n;i++){
            double tans=ans;
            double[] temp=new double[dp.length];
            for(int j=0;j<dp.length;j++){
                int[] a=val(j, b, k);
                int min=Integer.MAX_VALUE;
                for(int l=0;l<a.length;l++){
                    if(a[l]<min){
                        min=a[l];
                    }
                }
                tans+=min*dp[j];
                for(int l=0;l<a.length;l++){
                    a[l]-=min;
                }
                int count=0;
                for(int l=0;l<a.length;l++){
                    if(a[l]==0){
                        count++;
                    }
                }
                for(int l=0;l<a.length;l++){
                    if(a[l]==0){
                        for(int g=1;g<=m;g++){
                            a[l]=g;
                            int index=index(a, b);
                            temp[index]+=dp[j]/(m*count);
                        }
                        a[l]=0;
                    }
                }
            }
            //System.out.println(Arrays.toString(dp));
            out.println(tans);
            ans=tans;
            dp=temp;
            //System.out.println(Arrays.toString(dp));
        }
        out.close();
    }
    
    static int[] val(int ans,int b,int k){
        int[] a=new int[k];
        for(int i=0;i<a.length;i++){
            a[i]=ans%b;
            ans/=b;
        }
        return a;
    }
    
    static int index(int[] a,int b){
        int mul=1;
        int ans=0;
        for(int i=0;i<a.length;i++){
            ans+=a[i]*mul;
            mul*=b;
        }
        return ans;
    }
    
}

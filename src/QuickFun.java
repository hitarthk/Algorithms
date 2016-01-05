
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
public class QuickFun {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[][] a=new int[n*n][2];
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int x=in.nextInt()-1;
                a[x][0]=i;
                a[x][1]=j;
            }
        }
        for(int i=1;i<n*n;i++){
            ans+=20*(Math.abs(a[i][0]-a[i-1][0])+Math.abs(a[i][1]-a[i-1][1]));
        }
        int hrs=ans/3600;
        ans=ans%3600;
        int min=ans/60;
        ans=ans%60;
        int sec=ans;
        String s="";
        if(hrs>=10)
            s+=hrs;
        else
            s+="0"+hrs;
        s+=":";
        if(min>=10)
            s+=min;
        else
            s+="0"+min;
        s+=":";
        if(sec>=10)
            s+=sec;
        else
            s+="0"+sec;
        System.out.println(s);
    }
}

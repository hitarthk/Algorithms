
import java.io.PrintWriter;
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
public class PracticeA {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int b=in.nextInt();
        PrintWriter out=new PrintWriter(System.out);
        int[][] a=new int[(int)Math.ceil(Math.sqrt(b))][2];
        int c=0;
        for(int i=1;i*i<=b-1;i++){
            if((b-1)%i==0){
                a[c][0]=i;
                a[c][1]=(b-1)/i;
                c++;
            }
            
        }
        for(int i=0;i<c;i++){
            out.print(a[i][0]+" ");
        }
        int i;
        if(a[c-1][0]==a[c-1][1]){
            i=c-2;
        }
        else{
            i=c-1;
        }
        for(;i>=0;i--){
            out.print(a[i][1]+" ");
        }
        out.close();
    }
}

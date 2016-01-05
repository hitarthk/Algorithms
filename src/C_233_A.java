
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
public class C_233_A {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int p=in.nextInt();
        int k=in.nextInt();
        StringBuilder s=new StringBuilder("("+p+") ");
        int i;
        for(i=1;i<=k && p-i>=1;i++){
            s.insert(0, p-i+" ");
        }
        if(i>k && p-i>0){
            s.insert(0, "<< ");
        }
        for(i=1;i<=k && p+i<=n;i++ ){
            s.append((p+i)+" ");
        }
        if(i>k && p+i<=n){
            s.append(">>");
        }
        System.out.println(s);
    }
}

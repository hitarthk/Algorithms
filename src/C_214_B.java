
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarsthk
 */
public class C_214_B {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        ArrayList[] a=new ArrayList[n];
        for(int i=0;i<n;i++){
            int s=in.nextInt();
            a[i]=new ArrayList<Integer>(s);
            for(int j=0;j<s;j++){
                int x=in.nextInt();
                a[i].add(x);
            }
        }
        
        for(int i=0;i<n;i++){
            boolean check=true;
            for(int j=0;j<n;j++){
                if(i!=j){
                    if(a[i].containsAll(a[j])){
                        System.out.println("NO");
                        check=false;
                        break;
                    }
                }
            }
            if(check){
                System.out.println("YES");
            }
        }
        
    }
}

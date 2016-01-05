
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class c_330_c {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=0;i<n;i++){
            int x=in.nextInt();
            a.add(x);
        }
        f(a,true);
    }
    
    static int[] f(ArrayList a,boolean turn){
        int[] finalans=new int[2];
        if(a.size()==2){
            finalans[0]=(int) a.get(0);
            finalans[1]=(int) a.get(1);
            return finalans;
        }
        if(turn){
            int min=Integer.MAX_VALUE;
            for(int i=0;i<a.size();i++){
                ArrayList<Integer> temp=new ArrayList<>();
                copy(a, temp);
                temp.remove(i);
                int[] ans=f(temp,!turn);
                if(ans[1]-ans[0]<min){
                    min=ans[1]-ans[0];
                    finalans[0]=ans[0];
                    finalans[1]=ans[1];
                }
            }
            System.out.println("Warrior "+a+" "+Arrays.toString(finalans));
        }
        else{
            int max=0;
            for(int i=0;i<a.size();i++){
                ArrayList<Integer> temp=new ArrayList<>(a.size());
                copy(a, temp);
//                Collections.copy(temp, a);
                temp.remove(i);
                int[] ans=f(temp,!turn);
                if(ans[1]-ans[0]>max){
                    max=ans[1]-ans[0];
                    finalans[0]=ans[0];
                    finalans[1]=ans[1];
                }
            }
            System.out.println("Archer "+a+" "+Arrays.toString(finalans));
        }
        return finalans;
        
    }
    
    static void copy(ArrayList<Integer> src,ArrayList<Integer> dest){
        for(int i=0;i<src.size();i++){
            dest.add(src.get(i));
        }
    }
    
}

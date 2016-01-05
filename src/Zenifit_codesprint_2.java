
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
public class Zenifit_codesprint_2 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t-->0){
            String p=in.next();
            String e=in.nextLine();
            //System.out.println(p+" "+e);
            int min=Integer.MAX_VALUE;
            for(int i=0;i<26;i++){
                int count=0;
                for(int j=0;j<p.length();j++){
                    //System.out.println(i+" "+j+" "+((p.charAt(j)-'a'+i)%26+'a')+" "+(int)e.charAt(j+1));
                    if((p.charAt(j)-'a'+i)%26+'a'!=e.charAt(j+1)){
                        count++;
                    }
                }
                //System.out.println(i+" "+count);
                if(count<min){
                    min=count;
                }
            }
            System.out.println(min);
        }
    }
}

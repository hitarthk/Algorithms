
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
public class C_325_C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int t=in.nextInt();
        in.nextLine();
        char[] s1=in.nextLine().toCharArray();
        char[] s2=in.nextLine().toCharArray();
        char[] s3=new char[n];
        int mis=0;
        for(int i=0;i<n;i++){
            if(s1[i]!=s2[i])
                mis++;
        }
        if(t>=mis){
            for(int i=0;i<n;i++){
                if(s1[i]!=s2[i]){
                    char c;
                    for(c='a';c<='z';c++){
                        if(c!=s1[i] && c!=s2[i]){
                            s3[i]=c;
                            break;
                        }
                    }
                    t--;
                }
            }
            if(t>(n-mis)/2){
                System.out.println(-1);
                return;
            }
            else{
                boolean w=true;
                int i=0;
                while(t>0 && i<n){
                    if(s3[i]=='\0'){
                        if(w){
                            char c;
                            for(c='a';c<='z';c++){
                                if(c!=s1[i]){
                                    s3[i]=c;
                                    break;
                                }
                            }
                        }
                        else{
                            char c;
                            for(c='a';c<='z';c++){
                                if(c!=s2[i]){
                                    s3[i]=c;
                                    break;
                                }
                            }
                        }
                        w=!w;
                        t--;
                    }
                    i++;
                }
                i=0;
                while(i<n){
                    if(s3[i]=='\0'){
                        s3[i]=s1[i];
                    }
                    i++;
                }
                System.out.println(Arrays.toString(s3));
            }
        }
        else{
            if(t<Math.ceil(mis/2)){
                
            }
            else{
                boolean w=true;
                int i=0;
                while(t>mis/2){
                    if(s1[i]!=s2[i]){
                        char c;
                        for(c='a';c<='z';c++){
                            if(c!=s1[i] && c!=s2[i]){
                                s3[i]=c;
                                break;
                            }
                        }
                        mis--;
                        t--;
                    }
                    i++;
                }
                while(t>0){
                    if(s1[i]!=s2[i]){
                        if(w){
                            s3[i]=s1[i];
                        }
                        else{
                            s3[i]=s2[i];
                        }
                        w=!w;
                        t--;
                    }
                    i++;
                }
                i=0;
                
            }
        }
    }
}

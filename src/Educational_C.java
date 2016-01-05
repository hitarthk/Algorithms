
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
public class Educational_C {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Pair[] p=new Pair[n];
        for(int i=0;i<n;i++){
            double x=in.nextDouble();
            double y=in.nextDouble();
            p[i]=new Pair();
            p[i].id=i+1;
            p[i].x=x;
            p[i].y=y;
        }
        Arrays.sort(p);
        System.out.println(Arrays.toString(p));
        int id1=-1;
        int id2=-1;
        float min=(float) ((float)3*Math.PI);
        for(int i=0;i<n;i++){
            double angle1=Math.atan2(p[i].y,p[i].x);
            if(angle1<0){
                angle1+=Math.PI*2;
            }
            double angle2=Math.atan2(p[(i+1)%n].y, p[(i+1)%n].x);
            if(angle2<0){
                angle2+=Math.PI*2;
            }
            float diff=(float)(angle2-angle1);
            if(diff<0){
                diff+=2*Math.PI;
            }
            System.out.println(diff);
            if(diff<=min){
                min=diff;
                id1=p[i].id;
                id2=p[(i+1)%n].id;
            }
        }
        System.out.println(id1+" "+id2);
    }
    
    static class Pair implements Comparable<Pair>{
        double x;
        double y;
        int id;
        @Override
        public int compareTo(Pair o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            double angle1=Math.atan2(y, x);
            if(angle1<0){
                angle1+=2*Math.PI;
            }
            double angle2=Math.atan2(o.y,o.x);
            if(angle2<0){
                angle2+=2*Math.PI;
            }
            return Double.compare(angle1, angle2);
        }
        public String toString(){
            return id+"";
        }
    }
    
    
    
}

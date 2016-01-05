/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class LengthUnitCalculator {

    public double calc(int amount, String fromUnit, String toUnit) {
        double[] a = {12, 3, 1760};
        int from = 0;
        int to = 0;
        if(fromUnit.equals("in")){
            from=0;
        }
        if(fromUnit.equals("ft")){
            from=1;
        }
        if(fromUnit.equals("yd")){
            from=2;
        }
        if(fromUnit.equals("mi")){
            from=3;
        }
        
        if(toUnit.equals("in")){
            to=0;
        }
        if(toUnit.equals("ft")){
            to=1;
        }
        if(toUnit.equals("yd")){
            to=2;
        }
        if(toUnit.equals("mi")){
            to=3;
        }
        
        double ans=amount;
        
        if(from<to){
            while(from<to){
                ans/=a[from];
                from++;
            }
        }
        else{
            while(from>to){
                ans*=a[from-1];
                from--;
            }
        }
        return ans;
    }
}

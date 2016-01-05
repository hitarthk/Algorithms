
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
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
public class Input {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in=new Scanner(new File("/home/hitarthk/Academics/hpc/jor.txt"));
        String[] t=in.nextLine().split(" ");
        double d=Double.parseDouble(t[1]);
        while(in.hasNext()){
            String[] s=in.nextLine().split(" ");
            System.out.println(s[0]+" "+(d/(Double.parseDouble(s[1]))));
            //System.out.printf("%s %.3lf",s[0],(d/(Double.parseDouble(s[1])));
        }
    }
            
}

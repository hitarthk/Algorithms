/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author hitarthk
 */
/**
 * Java Program to Implement Rope
 **/
 
import java.util.Scanner;
 
/** Class RopeNode **/
class RopeNode
{
    RopeNode left, right;
    String data;
    int weight;    
    /** Constructor **/
    public RopeNode(String data)
    {
        this.data = data;
        left = null;
        right = null;
        weight = data.length();
    }
    /** Constructor **/
    public RopeNode()
    {
        data = null;
        left = null;
        right = null;
        weight = 0;
    }
}
 
/** Class Rope **/
class Rope
{
    RopeNode root;
 
    /** Constructor **/
    public Rope()
    {
        root = new RopeNode("");
    }
    /** Function to clear rope **/
    public void makeEmpty()
    {
        root = new RopeNode("");
    }
    /** Function to concat an element **/
    public void concat(String str)
    {
        RopeNode nptr = new RopeNode(str);
        RopeNode newRoot = new RopeNode();
        newRoot.left = root;
        newRoot.right = nptr;
        newRoot.weight = newRoot.left.weight ;
        if (newRoot.left.right != null)
            newRoot.weight += newRoot.left.right.weight;
        root = newRoot;
    }
    /** Function get character at a paricular index **/
    public char indexAt(int ind)
    {
        RopeNode tmp = root;
        if (ind > tmp.weight)
        {
            ind -= tmp.weight;
            return tmp.right.data.charAt(ind);
        }
        while (ind < tmp.weight)
            tmp = tmp.left;
        ind -= tmp.weight;
        return tmp.right.data.charAt(ind);            
    }
    /** Function get substring between two indices **/
    public String substring(int start, int end)
    {
        String str = "";
        boolean found = false;
        RopeNode tmp = root;
        if (end > tmp.weight)
        {
            found = true;
            end -= tmp.weight;
            if (start > tmp.weight)
            {
                start -= tmp.weight;
                str = tmp.right.data.substring(start, end);
                return str;
            }
            else
                str = tmp.right.data.substring(0, end);            
        }        
        if (!found)
        {
            while (end <= tmp.weight)
                tmp = tmp.left;
            end -= tmp.weight;
            if (start >= tmp.weight)
            {
                start -= tmp.weight;
                str = tmp.right.data.substring(start, end) + str;
                return str;
            }
            str = tmp.right.data.substring(0, end);            
        }    
        tmp = tmp.left;
        while (start < tmp.weight)
        {
            str = tmp.right.data + str;
            tmp = tmp.left;
        }
        start -= tmp.weight;
        str = tmp.right.data.substring(start) + str;    
 
        return str;        
    }
    /** Function to print Rope **/
    public void print()
    {
        print(root);
        System.out.println();
    }
    private void print(RopeNode r)
    {
        if (r != null)
        {
            print(r.left);
            if (r.data != null)
                System.out.print(r.data);
            print(r.right);
        }
    }    
}
 
/** Class RopeTest **/
public class RopeTest
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        /** Creating object of Rope **/
        Rope r = new Rope(); 
        System.out.println("Rope Test\n");          
        char ch;
        /**  Perform rope operations  **/
        do    
        {    
            System.out.println("\nRope Operations\n");
            System.out.println("1. concat ");
            System.out.println("2. get character at index");
            System.out.println("3. substring");
            System.out.println("4. clear");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter string to concat");
                r.concat( scan.next() );                     
                break;                          
            case 2 : 
                System.out.println("Enter index");
                System.out.println("Character at index = "+ r.indexAt(scan.nextInt()));
                break;                         
            case 3 : 
                System.out.println("Enter integer start and end limit");
                System.out.println("Substring : "+ r.substring( scan.nextInt(), scan.nextInt() ));
                break;                                          
            case 4 :  
                System.out.println("\nRope Cleared\n");
                r.makeEmpty();
                break;            
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /**  Display rope  **/ 
            System.out.print("\nRope : ");
            r.print();
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y'); 
    }
}

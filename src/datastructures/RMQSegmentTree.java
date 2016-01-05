/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Arrays;

/**
 *
 * @author hitarthk
 */
//Here we assume 0 based indexing ofthe array.
public class RMQSegmentTree {
    int[] a;
    int[] min;
    int size;
    public RMQSegmentTree(int[] a){
        this.a=a;
        size=2*(int)Math.pow(2,Math.ceil(Math.log(a.length)/Math.log(2)))-1;
        min=new int[size];
        construct(0,a.length-1,0);
        System.out.println(Arrays.toString(min));
    }
    
    private void construct(int l,int r,int id){
        if(l==r){
            min[id]=l;
            return;
        }
        int mid=(l+r)/2;
        construct(l, mid, 2*id+1);
        construct(mid+1, r, 2*id+2);
        if(a[min[2*id+1]]<a[min[2*id+2]]){
            min[id]=min[2*id+1];
        }
        else{
            min[id]=min[2*id+2];
        }
    }
    
    public int minIndex(int l,int r){
        return minIndexUtil(0, l, r, 0, a.length-1);
    }
    
    private int minIndexUtil(int id,int l,int r,int idl,int idr){
        if(l<=idl && r>=idr){
            return min[id];
        }
        if(r<idl || l>idr){
            return -1;
        }
        int mid=(idl+idr)/2;
        int lmi=minIndexUtil(2*id+1, l, r, idl, mid);
        int rmi=minIndexUtil(2*id+2, l, r, mid+1, idr);
        if(lmi==-1 && rmi==-1){
            return -1;
        }
        if(lmi==-1){
            return rmi;
        }
        if(rmi==-1){
            return lmi;
        }
        else{
            if(a[lmi]<=a[rmi]){
                return lmi;
            }
            else{
                return rmi;
            }
        }
    }
    
    public void updateValue(int p,int value){
        int minIndex=updateValueUtil(0, 0, a.length-1, p, value);
        if(a[minIndex]<a[min[0]]){
            min[0]=minIndex;
        }
    }
    
    public int updateValueUtil(int id,int idl,int idr,int p,int value){
        int mid=(idl+idr)/2;
        int minIndex=-1;
        if(idl==idr){
            a[p]=value;
            return p;
        }
        if(p>mid){
            minIndex=updateValueUtil(2*id+2, mid+1, idr, p, value);
        }
        else{
            minIndex=updateValueUtil(2*id+1, idl, mid, p, value);
        }
        if(minIndex!=-1 && a[minIndex]<a[min[id]]){
            min[id]=minIndex;
        }
        return minIndex;
    }
    
    public static void main(String[] args) {
        int[] a={3,6,1,2,7,8,9};
        RMQSegmentTree tree=new RMQSegmentTree(a);
        System.out.println(tree.minIndex(0, 6));
    }
    
}

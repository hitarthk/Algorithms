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
public class GCDSegmentTree {
    long[] a;
    long[] t;
    int size;
    public GCDSegmentTree(long[] a){
        this.a=a;
        size=a.length-1;
        t=new long[4*size];
        build(1,1,size);
    }
    
    public void build(int id,int l,int r){
        if(l==r){
            t[id]=a[l];
            return;
        }
        int mid=(l+r)/2;
        build(2*id,l,mid);
        build(2*id+1,mid+1,r);
        t[id]=gcd(t[2*id],t[2*id+1]);
    }
    
    public long query(int id,int s,int e,int l,int r){
        if(s>=l && e<=r){
            return t[id];
        }
        if((r<s)||l>e){
            return -1;
        }
        int mid=(s+e)/2;
        long lans=query(2*id,s,mid,l,r);
        long rans=query(2*id+1,mid+1,e,l,r);
        if(lans==-1){
            lans=rans;
        }
        if(rans==-1){
            rans=lans;
        }
        return gcd(lans,rans);
    }
    
    public long gcd(long a,long b){
        if(a<b){
            long temp=a;
            a=b;
            b=temp;
        }
        
        while(b>0){
            long temp=a%b;
            a=b;
            b=temp;
        }
        return a;
    }
    
}

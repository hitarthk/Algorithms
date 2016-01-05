
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class CMSEG2 {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        while(t>0){
            t--;
            int n=in.nextInt();
            int q=in.nextInt();
            long[] a=in.nextLongArray(n);
            SegTree tree=new SegTree(n, a);
            
            while(q>0){
                q--;
                String[] query=in.nextLine().split(" ");
                int x=Integer.parseInt(query[1]);
                int y=Integer.parseInt(query[2]);
                if(query[0].equals("0")){
                    x--;
                    y--;
                    out.println(tree.lis(x, y));
                }
                else{
                    x--;
                    tree.update(1, 0, n-1, x, (long)y);
                }
                //System.out.println("||||||||");
            }
            
        }
        out.close();
    }
    
    static class Node{
        int start;
        int end;
        int sofe;
        int eofs;
        int ls;
        int le;
        public Node(Node n){
            this.sofe=n.sofe;
            this.start=n.start;
            this.end=n.end;
            this.eofs=n.eofs;
            this.le=n.le;
            this.ls=n.ls;
        }
        public Node(){
            
        }
    }
    
    public static class SegTree{
        
        Node[] tree;
        long[] a;
        int n;
        
        public SegTree(int n,long[] a){
            this.n=n;
            this.a=a;
            tree=new Node[4*n];
            build(1, 0, n-1);
        }
        
        void build(int node,int start,int end){
            if(start==end){
                tree[node]=new Node();
                tree[node].ls=start;
                tree[node].le=end;
                tree[node].eofs=start;
                tree[node].sofe=start;
                tree[node].start=start;
                tree[node].end=end;
                return;
            }
            if(start>end){
                return;
            }
            
            int mid=(start+end)/2;
            build(2*node,start,mid);
            build(2*node+1, mid+1, end);
            tree[node]=new Node();
            tree[node].start=start;
            tree[node].end=end;
            tree[node].eofs=tree[2*node].eofs;
            tree[node].sofe=tree[2*node+1].sofe;
            if(tree[2*node].eofs==mid){
                if(a[tree[2*node].eofs]<a[mid+1]){
                    tree[node].eofs=tree[2*node+1].eofs;
                }
            }
            if(tree[2*node+1].sofe==mid+1){
                if(a[tree[2*node+1].sofe]>a[mid]){
                    tree[node].sofe=tree[2*node].sofe;
                }
            }
            //System.out.println(node+" "+(-tree[2*node].sofe+tree[2*node+1].eofs+1));
            if(a[mid]<a[mid+1] && -tree[2*node].sofe+tree[2*node+1].eofs+1 > (tree[2*node].le-tree[2*node].ls+1) && -tree[2*node].sofe+tree[2*node+1].eofs+1 > (tree[2*node+1].le-tree[2*node+1].ls+1))
            {
                //System.out.println("Here");
                tree[node].ls=tree[2*node].sofe;
                tree[node].le=tree[2*node+1].eofs;
            }
            else{
                if(tree[2*node].le-tree[node].ls>tree[2*node+1].le-tree[2*node+1].ls){
                    tree[node].ls=tree[2*node].ls;
                    tree[node].le=tree[2*node].le;
                }
                else{
                    tree[node].ls=tree[2*node+1].ls;
                    tree[node].le=tree[2*node+1].le;
                }
            }
            //System.out.println(tree[node].le-tree[node].ls+1);
        }
        
        void update(int node,int start,int end,int p,long inc){
            if(start==end){
                a[p]+=inc;
                return;
            }
            if(start>end){
                return;
            }
            
            int mid=(start+end)/2;
            if(p<=mid){
                update(2*node, start,mid, p, inc);
            }
            else{
                update(2*node+1, mid+1, end, p, inc);
            }
            tree[node]=new Node();
            tree[node].start=start;
            tree[node].end=end;
            tree[node].eofs=tree[2*node].eofs;
            tree[node].sofe=tree[2*node+1].sofe;
            if(tree[2*node].eofs==mid){
                if(a[tree[2*node].eofs]<a[mid+1]){
                    tree[node].eofs=tree[2*node+1].eofs;
                }
            }
            if(tree[2*node+1].sofe==mid+1){
                if(a[tree[2*node+1].sofe]>a[mid]){
                    tree[node].sofe=tree[2*node].sofe;
                }
            }
            //System.out.println(node+" "+(-tree[2*node].sofe+tree[2*node+1].eofs+1));
            if(a[mid]<a[mid+1] && -tree[2*node].sofe+tree[2*node+1].eofs+1 > (tree[2*node].le-tree[2*node].ls+1) && -tree[2*node].sofe+tree[2*node+1].eofs+1 > (tree[2*node+1].le-tree[2*node+1].ls+1))
            {
                //System.out.println("Here");
                tree[node].ls=tree[2*node].sofe;
                tree[node].le=tree[2*node+1].eofs;
            }
            else{
                if(tree[2*node].le-tree[node].ls>tree[2*node+1].le-tree[2*node+1].ls){
                    tree[node].ls=tree[2*node].ls;
                    tree[node].le=tree[2*node].le;
                }
                else{
                    tree[node].ls=tree[2*node+1].ls;
                    tree[node].le=tree[2*node+1].le;
                }
            }
            
        }
        
        int lis(int l,int r){
            Node ans=lis(1, 0, n-1, l, r);
            //System.out.println(ans.start+" "+ans.end);
            return ans.le-ans.ls+1;
        }
        
        Node lis(int node,int start,int end,int l,int r){
            Node ans=new Node();
            if(end<l || start>r){
                ans.ls=-1;
            }
            else{
                if(l<=start && r>=end){
                    ans=new Node(tree[node]);
                }
                else{
                    int mid=(start+end)/2;
                    Node left=lis(2*node, start, mid, l, r);
                    Node right=lis(2*node+1, mid+1, end, l, r);
                    if(left.ls==-1 && right.ls==-1){
                        ans.ls=-1;
                    }
                    else if(left.ls==-1){
                        ans=right;
                    }
                    else if(right.ls==-1){
                        ans=left;
                    }
                    else{
                        ans.start=Math.max(l,left.start);
                        ans.end=Math.min(r, right.end);
                        ans.eofs=Math.max(l, left.eofs);
                        ans.sofe=Math.min(r, right.sofe);
                        if (left.eofs == mid) {
                            if (a[left.eofs] < a[mid + 1]) {
                                ans.eofs = Math.min(r, right.eofs);
                            }
                        }
                        if (right.sofe == mid + 1) {
                            if (a[right.sofe] > a[mid]) {
                                ans.sofe = Math.max(l, left.sofe);
                            }
                        }
                        if (a[mid] < a[mid + 1] && -Math.max(l,left.sofe) + Math.min(r, right.eofs) + 1 > (Math.max(l,left.le) - Math.min(r, left.ls) + 1) && -Math.max(l,left.sofe) + Math.min(r, right.eofs) + 1 > (Math.max(l,right.le) - Math.min(r,right.ls) + 1)) {
                            ans.ls = Math.max(l,left.sofe);
                            ans.le = Math.min(r, right.eofs);
                        } else {
                            if (Math.max(l, left.le) - Math.min(r, left.ls) > Math.max(l,right.le) - Math.min(r, right.ls)) {
                                ans.ls = Math.max(l,left.ls);
                                ans.le = Math.min(r,left.le);
                            } else {
                                ans.ls = Math.max(l, right.ls);
                                ans.le = Math.min(r,right.le);
                            }
                        }
                    }
                }
            }
            return ans;
        }
        
    }
    
    public static class FasterScanner {

        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerrank;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 *
 * @author hitarthk
 */
public class coyote4 {

    static class AVLNode {

        AVLNode left, right;
        int data;
        int height;
        int count;
        /* Constructor */
        public AVLNode() {
            left = null;
            right = null;
            data = 0;
            height = 0;
            count=1;
        }
        /* Constructor */

        public AVLNode(int n) {
            left = null;
            right = null;
            data = n;
            height = 0;
            count=1;
        }
    }

    /* Class AVLTree */
    static class AVLTree {

        private AVLNode root;

        /* Constructor */
        public AVLTree() {
            root = null;
        }
        /* Function to check if tree is empty */

        public boolean isEmpty() {
            return root == null;
        }
        /* Make the tree logically empty */

        public void makeEmpty() {
            root = null;
        }
        /* Function to insert data */

        public void insert(int data) {
            root = insert(data, root);
        }
        /* Function to get height of node */

        private int height(AVLNode t) {
            return t == null ? -1 : t.height;
        }
        /* Function to max of left/right node */

        private int max(int lhs, int rhs) {
            return lhs > rhs ? lhs : rhs;
        }
        /* Function to insert data recursively */

        private AVLNode insert(int x, AVLNode t) {
            if (t == null) {
                t = new AVLNode(x);
            } else if (x < t.data) {
                t.left = insert(x, t.left);
                if (height(t.left) - height(t.right) == 2) {
                    if (x <= t.left.data) {
                        t = rotateWithLeftChild(t);
                    } else {
                        t = doubleWithLeftChild(t);
                    }
                }
            } else if (x >= t.data) {
                t.right = insert(x, t.right);
                if (height(t.right) - height(t.left) == 2) {
                    if (x > t.right.data) {
                        t = rotateWithRightChild(t);
                    } else {
                        t = doubleWithRightChild(t);
                    }
                }
            } // Duplicate; do nothing
            t.height = max(height(t.left), height(t.right)) + 1;
            t.count = (1+count(t.left)+count(t.right));
            return t;
        }
        /* Rotate binary tree node with left child */

        private AVLNode rotateWithLeftChild(AVLNode k2) {
            AVLNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = max(height(k2.left), height(k2.right)) + 1;
            k2.count=1+count(k2.left)+count(k2.right);
            k1.height = max(height(k1.left), k2.height) + 1;
            k1.count=1+count(k1.left)+count(k2);
            return k1;
        }

        /* Rotate binary tree node with right child */
        private AVLNode rotateWithRightChild(AVLNode k1) {
            AVLNode k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            k1.height = max(height(k1.left), height(k1.right)) + 1;
            k1.count = 1+count(k1.left)+count(k1.right);
            k2.height = max(height(k2.right), k1.height) + 1;
            k2.count=1+count(k2.right)+count(k1);
            return k2;
        }

        /**
         * Double rotate binary tree node: first left child with its right
         * child; then node k3 with new left child
         */
        private AVLNode doubleWithLeftChild(AVLNode k3) {
            k3.left = rotateWithRightChild(k3.left);
            return rotateWithLeftChild(k3);
        }

        /**
         * Double rotate binary tree node: first right child with its left
         * child; then node k1 with new right child
         */
        private AVLNode doubleWithRightChild(AVLNode k1) {
            k1.right = rotateWithLeftChild(k1.right);
            return rotateWithRightChild(k1);
        }
        /* Functions to count number of nodes */

        public int countNodes() {
            return countNodes(root);
        }
        
        public int count(AVLNode n){
            return n==null?0:n.count;
        }
        
        public int elementAt(int rank,AVLNode n){
            //System.out.println(rank+" "+n.data);
            if(count(n.left)==rank-1){
                return n.data;
            }
            if(count(n.left)>rank-1){
                return elementAt(rank,n.left);
            }
            else{
                return elementAt(rank-count(n.left)-1, n.right);
            }
        }
        
        private int countNodes(AVLNode r) {
            if (r == null) {
                return 0;
            } else {
                int l = 1;
                l += countNodes(r.left);
                l += countNodes(r.right);
                return l;
            }
        }
        /* Functions to search for an element */

        public boolean search(int val) {
            return search(root, val);
        }

        private boolean search(AVLNode r, int val) {
            boolean found = false;
            while ((r != null) && !found) {
                int rval = r.data;
                if (val < rval) {
                    r = r.left;
                } else if (val > rval) {
                    r = r.right;
                } else {
                    found = true;
                    break;
                }
                found = search(r, val);
            }
            return found;
        }
        /* Function for inorder traversal */

        public void inorder() {
            inorder(root);
        }

        private void inorder(AVLNode r) {
            if (r != null) {
                inorder(r.left);
                System.out.print(r.data + " "+r.count+" ");
                inorder(r.right);
            }
        }
        /* Function for preorder traversal */

        public void preorder() {
            preorder(root);
        }

        private void preorder(AVLNode r) {
            if (r != null) {
                System.out.print(r.data + " ");
                preorder(r.left);
                preorder(r.right);
            }
        }
        /* Function for postorder traversal */

        public void postorder() {
            postorder(root);
        }

        private void postorder(AVLNode r) {
            if (r != null) {
                postorder(r.left);
                postorder(r.right);
                System.out.print(r.data + " ");
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        
        int t=in.nextInt();
        int n=0;
        while(t>0){
            t--;
            String[] s=in.nextLine().split(" ");
            if(s.length==2){
                int x=Integer.parseInt(s[1]);
                tree.insert(x);
                n++;
            }
            else{
                if(n<3)
                    out.println("Not enough walks");
                else{
                    int rank=n-n/3 +1;
                    
                    //System.out.println("rank "+rank);
                    
                    out.println(tree.elementAt(rank, tree.root));
                }
            }
        }
        out.close();
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

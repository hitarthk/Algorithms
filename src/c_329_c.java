
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hitarthk
 */
public class c_329_c {
    public static void main(String[] args) {
        FasterScanner in=new FasterScanner();
        PrintWriter out=new PrintWriter(System.out);
        int n=in.nextInt();
        int m=in.nextInt();
        int[][] arr=new int[n][2];
        for(int i=0;i<n;i++){
            int s=in.nextInt();
            int e=in.nextInt();
            arr[i][0]=s;
            arr[i][1]=e;
        }
        Query[] q=new Query[m];
        for(int i=0;i<m;i++){
            int x=in.nextInt();
            int y=in.nextInt();
            int a=in.nextInt();
            int b=in.nextInt();
            q[i]=new Query(i, x, y, a, b);
        }
        Query.n=n;
        Query.sn=(int)Math.sqrt(n);
        Arrays.sort(q);
        int answer=0;
        int[] ans=new int[m];
        AvlTree[] h=new AvlTree[100010];
        for(int i=0;i<=100009;i++){
            h[i]=new AvlTree();
        }
        int currentL = 0, currentR = 0;
        int currentA = 0;
        int currentB = 0;
        for (int i = 0; i < m; i++) {
            int L = q[i].x, R = q[i].y;
            int A=q[i].a, B=q[i].b;
            while (currentL < L) {
                //remove(currentL);
                h[arr[currentL][0]].remove(arr[currentL][1]);
                h[arr[currentL][1]].remove(arr[currentL][0]);
                currentL++;
            }
            while (currentL > L) {
                //add(currentL - 1);
                h[arr[currentL-1][0]].insert(arr[currentL-1][1]);
                h[arr[currentL-1][1]].insert(arr[currentL-1][0]);
                currentL--;
            }
            while (currentR <= R) {
                //add(currentR);
                h[arr[currentR][0]].insert(arr[currentR][1]);
                h[arr[currentR][1]].insert(arr[currentR][0]);
                currentR++;
            }
            while (currentR > R + 1) {
                //remove(currentR - 1);
                h[arr[currentR-1][0]].remove(arr[currentR-1][1]);
                h[arr[currentR-1][1]].remove(arr[currentR-1][0]);
                currentR--;
            }
            
            ans[q[i].id] = answer;
        }
    } 
    
    
    
    static class Query implements Comparable<Query>
    {
        int x;
        int y;
        int a;
        int b;
        int id;
        static int n;
        static int sn;
        public Query(int id,int x,int y,int a,int b){
            this.id=id;
            this.x=x;
            this.y=y;
            this.a=a;
            this.b=b;
        }
        
        @Override
        public int compareTo(Query o) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.x/sn<o.x/sn){
                return -1;
            }
            else if(this.x/sn>o.x/sn){
                return 1;
            }
            else{
                if(this.y<o.y){
                    return -1;
                }
                else if(this.y>o.y){
                    return 1;
                }
                else{
                    if(this.a/sn<o.a/sn){
                        return -1;
                    }
                    else if(this.a/sn>o.a/sn){
                        return 1;
                    }
                    else{
                        if(this.b<o.b){
                            return -1;
                        }
                        else if(this.b>o.b){
                            return 1;
                        }
                        else{
                            return this.id-o.id;
                        }
                    }
                }
            }
        }
        
    }
    
    
    static class AvlTree {

    public class AvlNode {

        public AvlNode left;
        public AvlNode right;
        public AvlNode parent;
        public int key;
        public int balance;

        public AvlNode(int k) {
            left = right = parent = null;
            balance = 0;
            key = k;
        }

        public String toString() {
            return "" + key;
        }

    }

    protected AvlNode root; // the root node

    /**
     * *************************** Core Functions
     * ***********************************
     */
    /**
     * Add a new element with key "k" into the tree.
     *
     * @param k The key of the new node.
     */
    public void insert(int k) {
        // create new node
        AvlNode n = new AvlNode(k);
        // start recursive procedure for inserting the node
        insertAVL(this.root, n);
    }

    /**
     * Recursive method to insert a node into a tree.
     *
     * @param p The node currently compared, usually you start with the root.
     * @param q The node to be inserted.
     */
    public void insertAVL(AvlNode p, AvlNode q) {
        // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
        if (p == null) {
            this.root = q;
        } else {

            // If compare node is smaller, continue with the left node
            if (q.key < p.key) {
                if (p.left == null) {
                    p.left = q;
                    q.parent = p;

                    // Node is inserted now, continue checking the balance
                    recursiveBalance(p);
                } else {
                    insertAVL(p.left, q);
                }

            } else if (q.key > p.key) {
                if (p.right == null) {
                    p.right = q;
                    q.parent = p;

                    // Node is inserted now, continue checking the balance
                    recursiveBalance(p);
                } else {
                    insertAVL(p.right, q);
                }
            } else {
                // do nothing: This node already exists
            }
        }
    }

    /**
     * Check the balance for each node recursivly and call required methods for
     * balancing the tree until the root is reached.
     *
     * @param cur : The node to check the balance for, usually you start with
     * the parent of a leaf.
     */
    public void recursiveBalance(AvlNode cur) {

        // we do not use the balance in this class, but the store it anyway
        setBalance(cur);
        int balance = cur.balance;

        // check the balance
        if (balance == -2) {

            if (height(cur.left.left) >= height(cur.left.right)) {
                cur = rotateRight(cur);
            } else {
                cur = doubleRotateLeftRight(cur);
            }
        } else if (balance == 2) {
            if (height(cur.right.right) >= height(cur.right.left)) {
                cur = rotateLeft(cur);
            } else {
                cur = doubleRotateRightLeft(cur);
            }
        }

        // we did not reach the root yet
        if (cur.parent != null) {
            recursiveBalance(cur.parent);
        } else {
            this.root = cur;
            System.out.println("------------ Balancing finished ----------------");
        }
    }

    /**
     * Removes a node from the tree, if it is existent.
     */
    public void remove(int k) {
        // First we must find the node, after this we can delete it.
        removeAVL(this.root, k);
    }

    /**
     * Finds a node and calls a method to remove the node.
     *
     * @param p The node to start the search.
     * @param q The KEY of node to remove.
     */
    public void removeAVL(AvlNode p, int q) {
        if (p == null) {
            // der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
            return;
        } else {
            if (p.key > q) {
                removeAVL(p.left, q);
            } else if (p.key < q) {
                removeAVL(p.right, q);
            } else if (p.key == q) {
                // we found the node in the tree.. now lets go on!
                removeFoundNode(p);
            }
        }
    }

    /**
     * Removes a node from a AVL-Tree, while balancing will be done if
     * necessary.
     *
     * @param q The node to be removed.
     */
    public void removeFoundNode(AvlNode q) {
        AvlNode r;
        // at least one child of q, q will be removed directly
        if (q.left == null || q.right == null) {
            // the root is deleted
            if (q.parent == null) {
                this.root = null;
                q = null;
                return;
            }
            r = q;
        } else {
            // q has two children --> will be replaced by successor
            r = successor(q);
            q.key = r.key;
        }

        AvlNode p;
        if (r.left != null) {
            p = r.left;
        } else {
            p = r.right;
        }

        if (p != null) {
            p.parent = r.parent;
        }

        if (r.parent == null) {
            this.root = p;
        } else {
            if (r == r.parent.left) {
                r.parent.left = p;
            } else {
                r.parent.right = p;
            }
            // balancing must be done until the root is reached.
            recursiveBalance(r.parent);
        }
        r = null;
    }

    /**
     * Left rotation using the given node.
     *
     *
     * @param n The node for the rotation.
     *
     * @return The root of the rotated tree.
     */
    public AvlNode rotateLeft(AvlNode n) {

        AvlNode v = n.right;
        v.parent = n.parent;

        n.right = v.left;

        if (n.right != null) {
            n.right.parent = n;
        }

        v.left = n;
        n.parent = v;

        if (v.parent != null) {
            if (v.parent.right == n) {
                v.parent.right = v;
            } else if (v.parent.left == n) {
                v.parent.left = v;
            }
        }

        setBalance(n);
        setBalance(v);

        return v;
    }

    /**
     * Right rotation using the given node.
     *
     * @param n The node for the rotation
     *
     * @return The root of the new rotated tree.
     */
    public AvlNode rotateRight(AvlNode n) {

        AvlNode v = n.left;
        v.parent = n.parent;

        n.left = v.right;

        if (n.left != null) {
            n.left.parent = n;
        }

        v.right = n;
        n.parent = v;

        if (v.parent != null) {
            if (v.parent.right == n) {
                v.parent.right = v;
            } else if (v.parent.left == n) {
                v.parent.left = v;
            }
        }

        setBalance(n);
        setBalance(v);

        return v;
    }

    /**
     *
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    public AvlNode doubleRotateLeftRight(AvlNode u) {
        u.left = rotateLeft(u.left);
        return rotateRight(u);
    }

    /**
     *
     * @param u The node for the rotation.
     * @return The root after the double rotation.
     */
    public AvlNode doubleRotateRightLeft(AvlNode u) {
        u.right = rotateRight(u.right);
        return rotateLeft(u);
    }

    /**
     * *************************** Helper Functions
     * ***********************************
     */
    /**
     * Returns the successor of a given node in the tree (search recursivly).
     *
     * @param q The predecessor.
     * @return The successor of node q.
     */
    public AvlNode successor(AvlNode q) {
        if (q.right != null) {
            AvlNode r = q.right;
            while (r.left != null) {
                r = r.left;
            }
            return r;
        } else {
            AvlNode p = q.parent;
            while (p != null && q == p.right) {
                q = p;
                p = q.parent;
            }
            return p;
        }
    }

    /**
     * Calculating the "height" of a node.
     *
     * @param cur
     * @return The height of a node (-1, if node is not existent eg. NULL).
     */
    private int height(AvlNode cur) {
        if (cur == null) {
            return -1;
        }
        if (cur.left == null && cur.right == null) {
            return 0;
        } else if (cur.left == null) {
            return 1 + height(cur.right);
        } else if (cur.right == null) {
            return 1 + height(cur.left);
        } else {
            return 1 + maximum(height(cur.left), height(cur.right));
        }
    }

    /**
     * Return the maximum of two integers.
     */
    private int maximum(int a, int b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Only for debugging purposes. Gives all information about a node.
     *
     * @param n The node to write information about.
     */
    public void debug(AvlNode n) {
        int l = 0;
        int r = 0;
        int p = 0;
        if (n.left != null) {
            l = n.left.key;
        }
        if (n.right != null) {
            r = n.right.key;
        }
        if (n.parent != null) {
            p = n.parent.key;
        }

        System.out.println("Left: " + l + " Key: " + n + " Right: " + r + " Parent: " + p + " Balance: " + n.balance);

        if (n.left != null) {
            debug(n.left);
        }
        if (n.right != null) {
            debug(n.right);
        }
    }

    private void setBalance(AvlNode cur) {
        cur.balance = height(cur.right) - height(cur.left);
    }

    /**
     * Calculates the Inorder traversal of this tree.
     *
     * @return A Array-List of the tree in inorder traversal.
     */
    final protected ArrayList<AvlNode> inorder() {
        ArrayList<AvlNode> ret = new ArrayList<AvlNode>();
        inorder(root, ret);
        return ret;
    }

    /**
     * Function to calculate inorder recursivly.
     *
     * @param n The current node.
     * @param io The list to save the inorder traversal.
     */
    final protected void inorder(AvlNode n, ArrayList<AvlNode> io) {
        if (n == null) {
            return;
        }
        inorder(n.left, io);
        io.add(n);
        inorder(n.right, io);
    }
}

    
    static class FasterScanner {

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

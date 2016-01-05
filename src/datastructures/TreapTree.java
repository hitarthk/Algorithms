/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Random;

/**
 *
 * @author hitarthk
 */
public class TreapTree {

    private TreapNode root;
    private TreapNode nil;
    Random rand = new Random();

    public TreapTree() {
        nil = new TreapNode();
        root = nil;
    }

    public boolean isEmpty() {
        return root == nil;
    }

    public void clear() {
        root = nil;
    }

    public int size() {
        return root.size;
    }

    public void insert(int x) {
        root = insert(root, x);
    }

    public TreapNode insert(TreapNode curr, int x) {
        if (curr == nil) {
            return new TreapNode(x, nil, nil);
        }
        if (x < curr.value) {
            curr.left = insert(curr.left, x);
            if (curr.left.priority < curr.priority) {
                return rotateRight(curr);
            }
            update(curr);
        } else if (x > curr.value) {
            curr.right = insert(curr.right, x);
            if (curr.right.priority < curr.priority) {
                return rotateLeft(curr);
            }
            update(curr);
        } else {
            curr.cnt++;
            curr.size++;
        }
        return curr;
    }

    public TreapNode rotateLeft(TreapNode curr) {
        if (curr.right.value == 0) {
            return nil;
        }
        TreapNode right = curr.right;
        curr.right = right.left;
        right.left = curr;
        update(curr);
        update(right);
        return right;
    }

    public TreapNode rotateRight(TreapNode curr) {
        if (curr.left.value == 0) {
            return nil;
        }
        TreapNode left = curr.left;
        curr.left = left.right;
        left.right = curr;
        update(curr);
        update(left);
        return left;
    }

    //count values in treap <= r
    public int query(int r) {
        return query(root, r);
    }

    //count which values <= r
    public int query(TreapNode curr, int r) {
        if (curr.value == 0) {
            return 0;
        } else if (curr.value <= r) {
            return curr.cnt + curr.left.size + query(curr.right, r);
        } else {
            return query(curr.left, r);
        }
    }

    public static void update(TreapNode curr) {
        if (curr.value == 0) {
            return;
        }
        curr.size = curr.cnt + curr.left.size + curr.right.size;
    }

    public class TreapNode {

        TreapNode left, right;
        int priority, value, size, cnt;

        public TreapNode() {
            value = 0;
            size = 0;
            cnt = 0;
            priority = Integer.MAX_VALUE;
            left = right = this;
        }

        public TreapNode(int val, TreapNode left, TreapNode right) {
            value = val;
            cnt = 1;
            size = 1 + left.size + right.size;
            this.left = left;
            this.right = right;
            priority = rand.nextInt();
        }

        public String toString() {
            return value + "";// + " " + size + " " + left.value + " "+ right.value + " " + left.size + " " + right.size;
        }
    }

}

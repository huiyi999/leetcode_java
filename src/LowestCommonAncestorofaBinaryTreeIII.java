/**
 * 1650. Lowest Common Ancestor of a Binary Tree III
 * <p>
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * <p>
 * Each node will have a reference to its parent node.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 */

public class LowestCommonAncestorofaBinaryTreeIII {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    ;

    public Node lowestCommonAncestor(Node p, Node q) {
        // Same as 160: intersection-of-two-linked-lists
        Node a = p, b = q;
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }

        return a;

    }
}
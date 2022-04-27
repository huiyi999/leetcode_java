/**
 * 1644. Lowest Common Ancestor of a Binary Tree II
 * <p>
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 * <p>
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LowestCommonAncestorofaBinaryTreeII {

    boolean pExist = false;
    boolean qExist = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = LCA(root, p, q);
        return pExist && qExist ? lca : null;
        // If either node p or q does not exist in the tree, return null.
    }

    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return root;

        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);

        // traverse entire tree to check p and q whether exist
        if (root == p) {
            pExist = true;
            return root;
        }

        if (root == q) {
            qExist = true;
            return root;
        }

        return left == null ? right : right == null ? left : root;
    }

// Time Complexity: O(N)
// Space Complexity: O(H), H is the height of the tree
}
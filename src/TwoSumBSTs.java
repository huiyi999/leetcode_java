/**
 * 1214. Two Sum BSTs
 * Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TwoSumBSTs {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;

        boolean res = traverse(root2, target - root1.val);

        if (!res) {
            res = twoSumBSTs(root1.left, root2, target) ||
                    twoSumBSTs(root1.right, root2, target);
        }

        return res;
    }

    public boolean traverse(TreeNode node, int rest) {
        if (node != null) {
            if (rest == node.val)
                return true;
            else if (rest < node.val) return traverse(node.left, rest);
            else return traverse(node.right, rest);
        }
        return false;
    }
}

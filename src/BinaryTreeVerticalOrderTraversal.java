import java.util.*;
/**
 * 314. Binary Tree Vertical Order Traversal
 *
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        nodes.add(root);
        cols.add(0);

        int left = 0, right = 0;

        while(!nodes.isEmpty()){
            TreeNode n = nodes.poll();
            int col = cols.poll();

            map.computeIfAbsent(col,k->new ArrayList<Integer>()).add(n.val);

            if(n.left!=null){
                nodes.add(n.left);
                cols.add(col-1);
                left = Math.min(left, col-1);
            }
            if(n.right!=null){
                nodes.add(n.right);
                cols.add(col+1);
                right = Math.max(right, col+1);
            }
        }

        for(int i = left; i<=right; i++){
            ans.add(map.get(i));
        }


        return ans;


    }

    class Node {
        TreeNode root;
        int hd;
        public Node(TreeNode root, int hd) {
            this.root = root;
            this.hd = hd;
        }
    }
}

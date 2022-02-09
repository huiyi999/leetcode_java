import java.util.ArrayList;
import java.util.List;


//        Given a binary tree, each node has value 0 or 1.
//        Each root-to-leaf path represents a binary number starting with the most significant bit.
//        For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
//        For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
//        Return the sum of these numbers.

//        Input: [1,0,1,0,1,0,1]
//        Output: 22
//        Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22


public class SumofRootToLeafBinaryNumbers {
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

    /**
     * approach 1  time-consuming
     */

    public int sumRootToLeaf(TreeNode root) {
        List<String> list = new ArrayList<String>();
        String str = "";
        List<String> result = treeLeaf(root, list, str);
        int sum = 0;
        System.out.println("length: " + result.size());
        for (String s : result) {
            int item = Integer.parseInt(s, 10);
            sum += item;
        }
        System.out.println("sum: " + sum);

        return 0;
    }

    public List<String> treeLeaf(TreeNode root, List<String> list, String str) {

        if (root == null) {
            return list;
        } else {
            str += Integer.toString(root.val);
            System.out.println("str: " + str);
            List<String> leftTree = treeLeaf(root.left, list, str);
            List<String> rightTree = treeLeaf(root.right, list, str);
            if (root.left == null && root.right == null) {
                list.addAll(leftTree);
                list.addAll(rightTree);
                list.add(str);
                return list;
            } else {
                list.addAll(leftTree);
                list.addAll(rightTree);
                return list;
            }
        }
    }

    /**
     * approach 2
     */
    public int sumRootToLeaf2(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null)
            return 0;
        sum = sum * 2 + root.val; // sum = (sum << 1) + root.val;
        return (root.left == null && root.right == null) ? sum : dfs(root.left, sum) + dfs(root.right, sum);
    }

    public static void main(String[] args) {
        int item = Integer.parseInt("101", 2);
        System.out.println("item: " + item);

    }

}

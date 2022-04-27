import java.util.Stack;

/**
 * 536. Construct Binary Tree from String
 * <p>
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * <p>
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 * <p>
 * You always start to construct the left child node of the parent first if it exists.
 */
public class ConstructBinaryTreefromString {
    public TreeNode str2tree(String s) {
        //if(s == null || s.length() == 0) return null;

        Stack<TreeNode> stack = new Stack<>();
        int sign = 1, i = 0;
        TreeNode parent = null, curr = null;

        while (i < s.length()) {
            if (s.charAt(i) == ')') {
                curr = stack.pop();
                parent = stack.peek();
                if (parent.left != null) {
                    parent.right = curr;
                } else
                    parent.left = curr;
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (s.charAt(i) == '(') {
                i++;
            } else {
                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                num *= sign;
                sign = 1;   // reset sign
                stack.push(new TreeNode(num));
            }
        }
        // if input is empty string, stack is empty, then return parent which is null
        return stack.isEmpty() ? parent : stack.pop();


    }
    // 1. stack
//We usually utilize stack when we face problems related to parenthesis.
// In this case, the element we popped should be left child of the element on top of the stack if there is no left child yet, or else, it will be the right child.
// Please note that if there is only one node, i.e., the root of the Binary Tree, then the element on top of the stack is the root. Or else, the pointer parent points to the root in the last.
// if there is only one node: "4";  stack:new TreeNode(4)
    //Time: O(n), space: O(h)

    // 2. recursion
    private int idx = 0;
    private int len = 0;

    public TreeNode str2tree1(String s) {
        if (s == null || s.length() == 0) return null;
        len = s.length();
        idx = 0;
        return buildNode(s);
    }

    private TreeNode buildNode(String s) {
        if (idx >= len) return null;
        int dim = 1, val = 0;
        while (idx < len) {
            char c = s.charAt(idx);
            if (c == '-') {
                dim = -1;
            } else if (Character.isDigit(c)) {
                val = val * 10 + (c - '0') * dim;
            } else {
                break;
            }
            idx++;
        }
        TreeNode node = new TreeNode(val);
        if (idx < len && s.charAt(idx) == '(') {
            idx++;
            node.left = buildNode(s);
        }
        if (idx < len && s.charAt(idx) == ')') {
            idx++;
            return node;
        }
        if (idx < len && s.charAt(idx) == '(') {
            idx++;
            node.right = buildNode(s);
        }
        if (idx < len && s.charAt(idx) == ')') {
            idx++;
            return node;
        }
        return node;
    }
}

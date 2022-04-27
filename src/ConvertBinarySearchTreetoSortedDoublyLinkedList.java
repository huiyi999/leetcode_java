/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * <p>
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * <p>
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 * <p>
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 */


public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }


    // step1: inorder tranversal by recursion to connect the original BST
    // step2: connect the head and tail to make it circular

    Node prev = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);

        //connect
        // pre =5
        prev.right = dummy.right;
        // dummy.right = 1; 1.left = 5
        dummy.right.left = prev;
        return dummy.right;
    }

    void helper(Node curr) {
        if (curr == null) return;
        helper(curr.left);
        prev.right = curr;  // head.right = the leftmost node
        curr.left = prev;
        prev = curr;     // pre: 1 -> 2 -> 3 -> 4 -> 5
        helper(curr.right);
    }
}

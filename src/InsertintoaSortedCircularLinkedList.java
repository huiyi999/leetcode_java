public class InsertintoaSortedCircularLinkedList {

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;


    // edge cases
    // especially when start is null, single node or has duplicates
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal, null);

        // If the list is empty
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        /* 3 cases
            case 1: insertVal is between 2 nodes
            e.g. 1->2->4, insert 3
            e.g. 1->2->2, insert 2
            condition: insertVal >= n.val && insertVal <= n.next.val

            case 2: insertVal is >= largest node value or <= smalles node value
            e.g. 1->2->4, insert 0 or 1->2->4, insert 5
            condition: n.next.val < n.val && (insertVal >= n.val || insertVal <= n.next.val)
            (n.next.val < n.val) to find the min and max node, cuz list is sorted in ascending order


            case 3: all the nodes in the tree have same value
            e.g. 1->1->1, insert 2
            condition: n.next == head
        */


        Node n = head;

        while (true) {
            if ((insertVal >= n.val && insertVal <= n.next.val) ||
                    (n.next.val < n.val && (insertVal >= n.val || insertVal <= n.next.val)) ||
                    (n.next == head)) {
                newNode.next = n.next;
                n.next = newNode;
                break;
            }
            n = n.next;

        }
        return head;

        // Time O(N) Space O(1)
    }
}

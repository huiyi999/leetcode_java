public class IntersectionofTwoLinkedLists {
    //    Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

//设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
//        当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；
//        同样地，当访问 B 链表的指针访问到链表尾部时，令它从链表 A 的头部开始访问链表 A。
//        这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;

    }

    public static void main(String[] args) {

    }
}

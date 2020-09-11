/**
 * Q206. 反转链表
 * 反转一个单链表
 */
public class Q206_ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {

            ListNode next = cur.next;
            cur.next = pre;

            if (pre == head) {
                pre.next = null;
            }

            pre = cur;
            cur = next;

        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        Q206_ReverseList solution = new Q206_ReverseList();
        ListNode node = solution.reverseList(l1);

        while (node != null){
            System.out.print (node.val + " ");
            node = node.next;
        }

    }
}

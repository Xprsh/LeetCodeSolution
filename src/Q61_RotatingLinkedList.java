/**
 * Q61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，
 * 其中 k 是非负数。
 */
public class Q61_RotatingLinkedList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k < 0) {
            return null;
        }

        if (k == 0) {
            return head;
        }

        int length  = 1;

        ListNode cur = head;

        while (cur.next != null){
            length++;
            cur = cur.next;
        }

        ListNode tail = cur;

        // 实际需要移动的位置
        k = k % length;

        if(k == 0){
            return head;
        }

        // 移动 length - k 步
        ListNode pre = null;
        cur = head;

        for (int i = 0; i < (length - k); i++) {
            pre = cur;
            cur = cur.next;
        }

        tail.next = head;
        head = cur;
        pre.next = null;

        return head;
    }

    public static void main(String[] args) {
        Q61_RotatingLinkedList rotate = new Q61_RotatingLinkedList();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode head = rotate.rotateRight(l1, 6);
        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }

    }
}

/**
 * Q19.删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 */
public class Q19_DeleteTheNthNodeFromTheBottom {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) {
            throw new IllegalArgumentException("ListNode Is Null");
        }

        if (n == 0) {
            throw new IllegalArgumentException("n Is Wrong");
        }


        ListNode pre = head;
        ListNode cur = head;


        // 让一个指针先走 n-1 步
        for (int i = 0; i < n - 1; i++) {
            cur = cur.next;
        }

        // 存储被删除节点的前一节点
        ListNode last = null;

        // 双指针同时走动，直至先走指针到达链表末尾
        while (cur.next != null) {
            last = pre;
            pre = pre.next;
            cur = cur.next;
        }

        // 删除 pre 所指向节点
        if (last == null) {
            // 待删除节点为头节点，返回头节点的下一节点
            return pre.next;
        } else {
            last.next = pre.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode head = new Q19_DeleteTheNthNodeFromTheBottom()
                .removeNthFromEnd(l1, 3);

        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }

    }
}

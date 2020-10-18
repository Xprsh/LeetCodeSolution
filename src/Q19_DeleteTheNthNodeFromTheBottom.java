/**
 * Q19.删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 */
public class Q19_DeleteTheNthNodeFromTheBottom {
    /**
     * 2020.10.18 重新写了一遍，更加清晰
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 双指针指针
        ListNode slow = head;
        ListNode fast = head;

        // 待删除节点的上一个结点
        ListNode pre = null;

        // 快指针先走一步
        for(int i = 0;i < n - 1;i++){
            fast = fast.next;
        }

        while(fast.next != null){
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }

        // 删除头节点
        if(pre == null){
            return head.next;
        }

        // 如果fast == slow，说明删除末尾节点
        if(fast == slow){
            pre.next = null;
            return head;
        }

        // 其余的将pre指向slow.next即可删除要删除的节点
        pre.next = slow.next;
        return head;
    }
    public ListNode removeNthFromEnd_1(ListNode head, int n) {

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

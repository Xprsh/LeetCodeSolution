/**
 * Q141. 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 */
public class Q141_HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (true) {
            // 满指针追上快指针，说明有环
            if (slow == fast) {
                return true;
            }

            if (fast.next == null || fast.next.next == null) {
                return false;
            }

            // 快指针走两步
            fast = fast.next.next;
            // 慢指针走一步
            slow = slow.next;
        }
    }


    public static void main(String[] args) {
        Q141_HasCycle obj = new Q141_HasCycle();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l2;

        System.out.println(obj.hasCycle(l1));
    }
}

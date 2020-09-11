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


        ListNode fast = head;
        ListNode slow = head;

        // 快慢指针，快指针每次走两步，慢指针走一步
        while (true) {
            for (int i = 0; i < 2; i++) {
                fast = fast.next;
                if (fast == slow) {
                    return true;
                }
                if (fast == null) {
                    return false;
                }
            }
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

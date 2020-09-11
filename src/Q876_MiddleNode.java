/**
 * Q876. 链表的中间结点
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class Q876_MiddleNode {
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (true){
            if(fast.next == null || fast.next.next == null){
                return slow.next;
            }else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
    }

    public static void main(String[] args) {
        Q876_MiddleNode obj = new Q876_MiddleNode();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);

        l1.next = l2;

        System.out.println(obj.middleNode(l1).val);
    }
}

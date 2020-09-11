import java.util.Stack;

/**
 * Q25. K 个一组翻转链表
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 */
public class Q25_KFlipList {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            throw new IllegalArgumentException("Head Node Is Null");
        }

        if (k < 1){
            throw new IllegalArgumentException("k is Wrong");
        }

        // k 为 1，无需翻转
        if(k == 1){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<>();
        Boolean isFirst = true;

        while (cur != null) {
            stack.add(cur);

            // 达到一组反转条件
            if(stack.size() == k){
                // 首次反转，头节点改变
                if (isFirst){
                    head = cur;
                    isFirst = false;
                }

                // 反转最后一个节点的下一个节点对应此时 cur 的下一个节点
                ListNode nextStart = cur.next;

                // 当前操作反转的节点
                ListNode op = null;
                pre.next = stack.peek();
                while(stack.size() > 1){
                   op = stack.pop();
                   op.next = stack.peek();
                }

                op = stack.pop();
                pre = op;
                op.next = nextStart;
                cur = nextStart;
            } else {
                cur = cur.next;
            }
        }

        return head;
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

        ListNode head = new Q25_KFlipList().reverseKGroup(l1, 4);

        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

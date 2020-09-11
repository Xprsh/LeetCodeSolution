public class Q2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;

        while (l1 != null || l2 != null) {
            // 判定该位是否为空，是则代表0
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;

            carry = sum / 10;
            cur.next = new ListNode(sum % 10);

            // 移动当前位指针
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // 循环结束后判断首位是否需要进位
        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return pre.next;
    }

    public static void main(String[] args) {

    }
}

/**
 * Q21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Q21_MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 用于指向真正链表头的结点
        ListNode newHead = new ListNode(-1);
        merge(l1, l2, newHead);
        return newHead.next;
    }

    private void merge(ListNode l1, ListNode l2, ListNode newHead) {
        if (l1 == null && l2 == null) {
            return;
        }

        if (l1 == null) {
            newHead.next = new ListNode(l2.val);
            l2 = l2.next;
        } else if (l2 == null) {
            newHead.next = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            if (l1.val <= l2.val) {
                newHead.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                newHead.next = new ListNode(l2.val);
                l2 = l2.next;
            }
        }

        merge(l1, l2, newHead.next);
    }

    public static void main(String[] args) {
        Q21_MergeTwoLists obj = new Q21_MergeTwoLists();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        ListNode node = obj.mergeTwoLists(l1, l5);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}

/**
 * Q23. 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Q23_MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return merge(lists,0,lists.length-1);
    }

    // 分治
    private ListNode merge(ListNode[] lists,int left, int right) {
        if (left == right){
            return lists[left];
        }

        int mid = left + (right - left)/2;

        ListNode a = merge(lists,left,mid);
        ListNode b = merge(lists,mid+1, right);

        return mergeTwoLists(a,b);
    }


    // 合并两个有序列表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }

        if (l2 == null){
            return l1;
        }

        if (l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}

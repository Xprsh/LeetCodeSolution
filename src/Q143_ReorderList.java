import java.util.*;

/**
 * Q143. 重排链表
 *
 * @author Xprsh
 * @Description TODO
 * @createTime 2020/10/20/ 09:20:16
 */
public class Q143_ReorderList {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return ;
        }

        Deque<ListNode> deque =new ArrayDeque<>();
        ListNode node = head.next;
        while(node != null){
            deque.add(node);
            node = node.next;
        }

        boolean flag = true;
        node = head;
        while(node != null){
            if(flag){
                node.next = deque.pollLast();
            }else{
                node.next = deque.pollFirst();
            }


            if(deque.isEmpty()){
                node.next.next = null;
                break;
            }

            node= node.next;
            flag = !flag;
        }

    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        new Q143_ReorderList().reorderList(n1);

    }
}

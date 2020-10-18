import java.util.HashSet;
import java.util.Set;

/**
 * Q142. 环形链表 Ⅱ
 *
 * @author Xprsh
 * @Description 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * @createTime 2020/10/10/ 09:51:11
 */
public class Q142_DetectCycle {
    /**
     * 方法一： Set集合
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while(node != null){
            if(set.contains(node)){
                return node;
            }else{
                set.add(node);
            }
            node = node.next;
        }

        return null;
    }

    public ListNode detectCycle(ListNode head) {


        return null;
    }
}

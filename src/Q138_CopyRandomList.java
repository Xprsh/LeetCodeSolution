import java.util.HashMap;
import java.util.Map;

/**
 * Q138. 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 */
public class Q138_CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 存储新旧节点信息
        Map<Node, Node> map = new HashMap<>();

        Node cur = head;

        // 先进行顺序链表的复制
        Node newHead = null;
        Node preNew = null;
        Node preOrigin = null;

        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.random = cur.random;


            if (cur == head) {
                newHead = newNode;
            }

            if (preNew != null) {
                preNew.next = newNode;
                map.put(preOrigin, preNew);
            }

            preOrigin = cur;
            preNew = newNode;
            // 最后一个节点
            if (cur.next == null) {
                map.put(cur, newNode);
            }
            cur = cur.next;
        }


        // 将随机指针指向新的链表节点
        cur = newHead;
        while (cur != null) {
            if (cur.random != null) {
                cur.random = map.get(cur.random);
            }
            cur = cur.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n1.random = null;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;

        Q138_CopyRandomList solution = new Q138_CopyRandomList();
        Node node = solution.copyRandomList(n1);

        while (node != null) {
            System.out.print(node.val + " ");
            System.out.println(node.random == null ? "null" : node.random.val);
            node = node.next;
        }
    }
}

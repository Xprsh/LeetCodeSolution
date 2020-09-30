import java.util.LinkedList;

/**
 * Q117. 填充每个节点的下一个右侧节点指针 II
 *
 * 日期：2020年9月28日
 */
public class Q117_Connect {
    public Node connect(Node root) {
        LinkedList<Node> list = new LinkedList<>();
        if(root == null){
            return root;
        }

        list.add(root);
        int size = list.size();
        while(size > 0){
            for(int i = 0;i < size; i++){
                Node node = list.removeFirst();

                if(node.left != null){
                    list.add(node.left);
                }

                if(node.right != null){
                    list.add(node.right);
                }
                // 该层最后一个元素
                if(i == size - 1){
                    node.next = null;
                }else{
                    node.next = list.peek();
                }
            }

            size = list.size();
        }
        return root;
    }

    /**
     * 结点 Node 定义
     */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

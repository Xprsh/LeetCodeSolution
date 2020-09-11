import java.util.ArrayList;
import java.util.List;

/**
 * Q94. 二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。
 */
public class Q94_InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();

        if (root == null) {
            return order;
        }

        return inOrder(root, order);
    }

    private List<Integer> inOrder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return order;
        }

        order.addAll(inOrder(root.left, new ArrayList<>()));
        order.add(root.val);
        order.addAll(inOrder(root.right, new ArrayList<>()));
        return order;
    }
}

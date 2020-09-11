import java.util.ArrayList;
import java.util.List;

/**
 * Q145. 二叉树的后序遍历
 * <p>
 * 给定一个二叉树，返回它的 后序 遍历。
 */
public class Q145_PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();

        if (root == null) {
            return order;
        }

        return postOrder(root, order);
    }

    private List<Integer> postOrder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return order;
        }
        order.addAll(postOrder(root.left, new ArrayList<>()));
        order.addAll(postOrder(root.right, new ArrayList<>()));
        order.add(root.val);
        return order;
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Q144. 二叉树的前序遍历
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历。
 */
public class Q144_PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> order = new ArrayList<>();

        if (root == null) {
            return order;
        }

        return preOrder(root, order);
    }

    private List<Integer> preOrder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return order;
        }
        order.add(root.val);
        order.addAll(preOrder(root.left, new ArrayList<>()));
        order.addAll(preOrder(root.right, new ArrayList<>()));
        return order;
    }
}

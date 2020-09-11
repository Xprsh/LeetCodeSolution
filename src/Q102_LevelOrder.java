import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Q102. 二叉树的层序遍历
 */
public class Q102_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> order = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                order.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(order);
        }
        return ans;
    }

}

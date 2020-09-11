import java.util.*;

public class Q107_LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                list.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null){
                    queue.add(node.right);
                }
            }

            // 将得到的一层结点加入结果集
            ans.add(0,list);
        }

        return ans;
    }
}

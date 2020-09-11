/**
 * Q104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 */
public class Q104_MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return depth(root, 0);
    }

    private int depth(TreeNode root, int level) {
        if (root == null) {
            return level;
        }
        return  Math.max(depth(root.left, level+1), depth(root.right, level+1));
    }


}

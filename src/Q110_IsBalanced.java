import jdk.nashorn.internal.ir.IfNode;

/**
 * Q110. 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 */
public class Q110_IsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }

}

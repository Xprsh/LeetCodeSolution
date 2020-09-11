/**
 * Q101. 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class Q101_IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }

        return dfs(root.left,root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null){
            return true;
        }

        if (left == null || right == null){
            return false;
        }

        if (left.val != right.val){
            return false;
        }

        return dfs(left.left,right.right) && dfs(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(2);
        TreeNode l4 = new TreeNode(3);
        TreeNode l5 = new TreeNode(4);
        TreeNode l6 = new TreeNode(4);
        TreeNode l7 = new TreeNode(4);
        l1.left = l2;
        l1.right = l3;
        l2.left = l4;
        l2.right = l5;
        l3.left = l6;
        l3.right = l7;

        System.out.println(new Q101_IsSymmetric().isSymmetric(l1));
    }
}

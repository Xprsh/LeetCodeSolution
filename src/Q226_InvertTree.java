/**
 * Q226. 反转二叉树
 *
 * 翻转一棵二叉树。
 */
public class Q226_InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null||(root.left == null && root.right == null)){
            return root;
        }else {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

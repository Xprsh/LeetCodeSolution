/**
 * Q538. 把二叉搜索树转换为累加树
 *
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 */
public class Q538_convertBST {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if(root != null){
            // 后序遍历可以达到遍历二叉搜索树从大到小排序，从而实现累加
            convertBST(root.right);
            root.val += sum;
            sum = root.val;
            convertBST(root.left);
        }

        return root;
    }
}

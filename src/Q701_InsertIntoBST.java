/**
 * Q701. 二叉搜索树中的插入操作
 *
 * @author Xprsh
 * @ClassName Q701_InsertIntoBST
 * @Description 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * @createTime 2020/09/30/ 09:05:49
 */
public class Q701_InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        TreeNode node = root;
        while(true){
            if(val > node.val){
                if(node.right == null){
                    node.right = new TreeNode(val);
                    break;
                }else{
                    node = node.right;
                    continue;
                }
            }

            if(val < node.val){
                if(node.left == null){
                    node.left = new TreeNode(val);
                    break;
                }else{
                    node = node.left;
                    continue;
                }
            }

            if(val == node.val){
                if(node.left == null){
                    node.left = new TreeNode(val);
                    break;
                }

                if(node.right == null){
                    node.right = new TreeNode(val);
                    break;
                }else{
                    node = node.right;
                    continue;
                }
            }
        }

        return root;
    }
}

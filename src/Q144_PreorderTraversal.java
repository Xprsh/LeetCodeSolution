import java.util.ArrayList;
import java.util.List;

/**
 * Q144. 二叉树的前序遍历
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 */
public class Q144_PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        return preorder(root,new ArrayList<Integer>());
    }

    List<Integer> preorder(TreeNode node, List<Integer> list){
        if(node == null){
            return list;
        }

        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right,list);

        return list;
    }

}

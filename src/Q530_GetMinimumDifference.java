import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q530. 二叉搜索树的最小绝对差
 *
 * @author Xprsh
 * @Description 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * @createTime 2020/10/12/ 09:28:38
 */
public class Q530_GetMinimumDifference {
    static int min;
    static Deque<Integer> queue = new ArrayDeque<>(1);
    public int getMinimumDifference(TreeNode root) {
        // 初始化min，根据题意不会报错
        if(root.left != null){
            min = Math.abs(root.left.val - root.val);
        }else{
            min = Math.abs(root.right.val - root.val);
        }

        inorder(root);

        return min;

    }

    void inorder(TreeNode node){
        if(node == null){
            return ;
        }
        inorder(node.left);
        if (!queue.isEmpty()) {
            int num = Math.abs(node.val - queue.poll());
            min = Math.min(num, min);
        }
        queue.add(node.val);
        inorder(node.right);

    }

}

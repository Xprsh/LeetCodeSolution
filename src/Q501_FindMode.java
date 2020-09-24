import java.util.ArrayList;
import java.util.List;

/**
 * Q501. 二叉搜索树中的众数
 */
public class Q501_FindMode {

    int cur;
    int curCount;
    int maxCount;
    List<Integer> answer = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        order(root);
        int[] result = new int[answer.size()];

        for(int i = 0;i<answer.size();i++){
            result[i] = answer.get(i);
        }

        return result;
    }

    public void order(TreeNode root){
        if(root == null){
            return ;
        }
        order(root.left);
        update(root.val);
        order(root.right);
    }

    // 众数比较
    public void update(int x){
        if (x == cur){
            curCount++;
        }else{
            curCount = 1;
            cur = x;
        }

        if(curCount == maxCount){
            answer.add(cur);
        }

        if (curCount > maxCount){
            maxCount = curCount;
            answer.clear();
            answer.add(cur);
        }
    }

    public static void main(String[] args) {
        Q501_FindMode obj = new Q501_FindMode();
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        n4.right = n5;

    }
}

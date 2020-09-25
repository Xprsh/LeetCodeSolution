/**
 * Q106. 从中序与后续遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 */
public class Q106_BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null){
            return null;
        }

        if(postorder.length == 0 || inorder.length != postorder.length){
            return null;
        }


        int len = postorder.length;

        // 根节点下标
        int headIndex = -1;

        for(int i = 0;i < len;i++){
            if(postorder[len-1] == inorder[i]){
                headIndex = i;
                break;
            }
        }


        // 根节点
        TreeNode head = new TreeNode(postorder[len-1]);

        // 左子树元素长度
        int left = headIndex;
        // 右子树元素长度
        int right = len-left-1;

        int[] leftInorder = new int[left];
        int[] leftPostorder = new int[left];

        int[] rightInorder = new int[right];
        int[] rightPostorder = new int[right];
        if(left != 0){
            System.arraycopy(inorder,0,leftInorder,0,left);
            System.arraycopy(postorder,0,leftPostorder,0,left);
        }

        if(right != 0){
            System.arraycopy(inorder,headIndex+1,rightInorder,0,right);
            System.arraycopy(postorder,left,rightPostorder,0,right);
        }

        head.left = buildTree(leftInorder,leftPostorder);
        head.right = buildTree(rightInorder,rightPostorder);
        return head;
    }
}

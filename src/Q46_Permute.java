import java.util.ArrayList;
import java.util.List;

/**
 * Q46. 全排列
 *
 * @author Xprsh
 * @ClassName Q46_Permute
 * @Description 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * @createTime 2020/09/30/ 15:30:40
 */
public class Q46_Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if(nums == null || nums.length == 0){
            return ans;
        }

        traceback(nums, nums.length, 0, new ArrayList<>(), new boolean[nums.length],ans);

        return ans;
    }

    public void traceback(int[] nums, int len, int depth, List<Integer> path,boolean[] used,   List<List<Integer>> ans){
        if(len == depth){
            // 一条路径搜索完毕，添加到答案中
            ans.add(new ArrayList<Integer>(path));
            return ;
        }

        // 在非叶子结点处，产生不同的分支,即：在还未选择的数中依次选择一个元素作为下一个位置的元素
        for(int i = 0;i<len;i++){
            // 该元素还未被选中过
            if(!used[i]){
                path.add(nums[i]);
                // 变更为已用
                used[i] = true;
                // dfs 继续搜索
                traceback(nums,len,depth+1,path,used,ans);
                // 回退，还原状态
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}

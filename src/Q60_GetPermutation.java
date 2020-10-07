import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @Description TODO
 * @createTime 2020/10/02/ 11:35:21
 */
public class Q60_GetPermutation {
    public String getPermutation(int n, int k) {

        int[] nums = new int[n];

        for(int i = 0; i < n;i++){
            nums[i] = i + 1;
        }

        List<Integer> ans = dfs(nums, n, 0, k, new boolean[n], new ArrayList<>());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n;i++){
            sb.append(ans.get(i));
        }

        return sb.toString();
    }

    int count  = 0;

    List<Integer> dfs(int[] nums, int len, int depth, int k, boolean[] used, List<Integer> path){
        if(len == depth){
            count++;
            // 第 k 个排列
            if(count == k){
                return path;
            }
        }
        List<Integer> ans = null;

        for(int i = 0;i < len; i++){
            if(!used[i]){
                path.add(nums[i]);
                used[i] = true;
                ans = dfs(nums, len, depth + 1, k, used, path);

                if (ans != null){
                    return ans;
                }

                // 回退
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Q60_GetPermutation().getPermutation(3,3));
    }
}

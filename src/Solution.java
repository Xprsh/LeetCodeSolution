import java.util.ArrayList;
import java.util.List;

/**
 * 仅用于调式程序，不属于题解范围
 */
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 1 || k > n) {
            return ans;
        }


        dfs(n, k, 1, new ArrayList<>(), ans);
        return ans;

    }

    public void dfs(int n, int k, int cur, List<Integer> path, List<List<Integer>> ans) {
        if (k == path.size()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i <= n; i++) {
            path.add(i);
            dfs(n, k, i + 1, path, ans);

            // 回退
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combine(4, 2));

    }
}

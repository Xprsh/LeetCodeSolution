import java.util.ArrayList;
import java.util.List;

/**
 * Q78. 子集
 * <p>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 */
public class Q78_Subsets {

    /**
     * 方法一： 从后向前递归（逐渐减少）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());

        if (nums == null || nums.length == 0) {
            return ans;
        }

        ans = sub(nums, ans);

        return ans;
    }

    List<List<Integer>> sub(int[] nums, List<List<Integer>> ans) {
        if (nums.length == 0) {
            return ans;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        if (!ans.contains(list)) {
            ans.add(list);
        } else {
            return ans;
        }

        int[] n = new int[nums.length - 1];

        for (int i = 0; i < nums.length; i++) {
            int index = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    n[index] = nums[j];
                    index++;
                }
            }
            sub(n, ans);
        }

        return ans;
    }

    /**
     * 方法二：递归（从前往后，由空逐渐增加）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());

        if (nums == null || nums.length == 0) {
            return ans;
        }

        for (Integer num : nums) {
            List<List<Integer>> newList = new ArrayList<>();
            for (List<Integer> cur : ans){
                newList.add(new ArrayList<Integer>(cur){{add(num);}});
            }

            ans.addAll(newList);
        }

        return ans;
    }

    public static void main(String[] args) {
        Q78_Subsets obj = new Q78_Subsets();
        System.out.println(
                obj.subsets(new int[]{
                        1,2,3
                }));
    }
}

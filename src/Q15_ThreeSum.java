import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class Q15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();

        // 元素从小到大排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 去重,跟以前的比，不是以后的
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // nums[i] > 0，意味着数组中已无满足条件的数组
            if (nums[i] > 0) {
                break;
            }

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++; // 去重
                    while (l < r && nums[r] == nums[r - 1]) r--; // 去重
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q15_ThreeSum obj = new Q15_ThreeSum();
        List<List<Integer>> lists = obj.threeSum(new int[]{-1, 0, 1, 2, -1, -4});

        System.out.println(lists);
    }
}

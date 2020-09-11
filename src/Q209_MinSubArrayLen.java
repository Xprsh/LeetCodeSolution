/**
 * Q209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0。
 */
public class Q209_MinSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;
        int sum = nums[0];

        for (int start = 0, end = 0; end < nums.length; ) {

            if (sum >= s) {
                ans = ans == 0 ? end - start + 1 : Math.min(ans, end - start + 1);
                // 已找到最佳答案
                if (ans == 1) {
                    return ans;
                }
                sum -= nums[start++];
            } else {
                if (end == nums.length - 1) {
                    break;
                }
                sum += nums[++end];
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        Q209_MinSubArrayLen obj = new Q209_MinSubArrayLen();
        System.out.println(obj.minSubArrayLen(15, new int[]{5, 1, 3, 5, 10, 7, 4, 9, 2, 8}));
    }
}

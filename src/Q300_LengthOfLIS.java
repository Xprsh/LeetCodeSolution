/**
 * Q300. 最长上升子序列
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 */
public class Q300_LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len];
        dp[0] = 1;
        int max = 1;

        for (int i = 1; i < len; i++) {
            int maxDp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxDp = Math.max(maxDp, dp[j]);
                }
            }
            dp[i] = maxDp + 1;
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Q300_LengthOfLIS obj = new Q300_LengthOfLIS();
        System.out.println(obj.lengthOfLIS(
                new int[]{
                        1,3,6,7,9,4,10,5,6
                }
        ));
    }
}

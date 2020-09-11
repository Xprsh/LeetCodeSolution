/**
 * Q53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class Q53_MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null){
            throw new IllegalArgumentException("Nums Is Null!");
        }

        if (nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }

        int len = nums.length;
        int max = nums[0];
        // 初始化 dp
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        Q53_MaxSubArray obj = new Q53_MaxSubArray();
        System.out.println(obj.maxSubArray(new int[]{
                -2,1,-3,4,-1,2,1,-5,4
        }));

    }
}

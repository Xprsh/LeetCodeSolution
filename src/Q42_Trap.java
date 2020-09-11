/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Q42_Trap {
    public int trap(int[] height) {
        if(height == null || height.length <= 2){
            return 0;
        }

        int len = height.length;
        int ans = 0;

        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];

        // 当前柱子左边最长的柱子
        for (int i = 1; i < len - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        // 当前柱子右边最长的柱子
        for (int i = len - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        // 柱子两端存不住水，不考虑
        for (int i = 1; i < len - 1; i++) {
            ans = Math.min(maxLeft[i], maxRight[i]) > height[i] ? ans + Math.min(maxLeft[i], maxRight[i]) - height[i] : ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        Q42_Trap obj = new Q42_Trap();
        System.out.println(obj.trap(
                new int[]{0,1,0,2,1,0,1,3,2,1,2,1}
        ));
    }
}

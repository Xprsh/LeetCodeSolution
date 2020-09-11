import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 */
public class Q16_ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Nums is Illegally!");
        }

        int ans = nums[0] + nums[1] + nums[2];

        if(nums.length == 3){
            return ans;
        }

        // 元素从小到大排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 去重,跟以前的比，不是以后的
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                // 找到值一样结果，直接返回target
                if (sum == target) {
                    return target;
                }

                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }

                // 移动指针
                if (sum - target < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        Q16_ThreeSumClosest obj = new Q16_ThreeSumClosest();

        System.out.println(obj.threeSumClosest(
                new int[]{13,2,0,-14,-20,19,8,-5,-13,-3,20,15,20,5,13,14,-17,-7,12,-6,0,20,-19,-1,-15,-2,8,-2,-9,13,0,-3,-18,-9,-9,-19,17,-14,-19,-4,-16,2,0,9,5,-7,-4,20,18,9,0,12,-1,10,-17,-11,16,-13,-14,-3,0,2,-18,2,8,20,-15,3,-13,-12,-2,-19,11,11,-10,1,1,-10,-2,12,0,17,-19,-7,8,-19,-17,5,-5,-10,8,0,-12,4,19,2,0,12,14,-9,15,7,0,-16,-5,16,-12,0,2,-16,14,18,12,13,5,0,5,6}, -59
        ));
    }
}

/**
 * Q26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，
 * 返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */

public class Q26_RemoveDuplicates {
    public int removeDuplicates(int[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException("Nums Is Null!");
        }

        if (nums.length <= 1) {
            return nums.length;
        }

        int i = 0;
        int j = 0;
        int len = nums.length;
        int ans = 0;

        while (j < len - 1) {
            // 倒数第二个数字
            if (j == len - 2) {
                // 重复复制该数字
                if (nums[j] == nums[j + 1]) {
                    nums[i] = nums[j];
                    ans = i + 1;
                } else {
                    nums[i] = nums[j];
                    nums[i + 1] = nums[j + 1];
                    ans = i + 2;
                }
            }

            if (nums[j] == nums[j + 1]) {
                j++;
            } else {
                nums[i++] = nums[j++];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q26_RemoveDuplicates obj = new Q26_RemoveDuplicates();
        System.out.println(
                obj.removeDuplicates(new int[]{0,0,0,1,2})
        );
    }
}

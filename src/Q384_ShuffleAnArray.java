import java.util.Arrays;
import java.util.Random;

/**
 * Q384. 打乱数组
 * <p>
 * 打乱一个没有重复元素的数组。
 */
public class Q384_ShuffleAnArray {
    final int[] original;
    int[] nums;

    // 判题需要修改 Class

    public Q384_ShuffleAnArray(int[] nums) {
        this.nums = nums;
        // 不加的话会复制 nums 的引用
        this.original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return original;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int index = random.nextInt(nums.length);

            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;

        }
        return nums;
    }

    public static void main(String[] args) {
        Q384_ShuffleAnArray obj = new Q384_ShuffleAnArray(
                new int[]{1, 2, 3}
        );

        System.out.println(Arrays.toString(obj.shuffle()));
        System.out.println(Arrays.toString(obj.reset()));
    }
}

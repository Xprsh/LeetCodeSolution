import java.util.Arrays;

/**
 * Q581. 最短无序连续子数组
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，
 * 那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 */
public class Q581_FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        if (Arrays.equals(sorted, nums)) {
            return 0;
        }

        int i = 0;
        while (nums[i] == sorted[i]) {
            i++;
        }

        int j = nums.length - 1;

        while (nums[j] == sorted[j]) {
            j--;
        }

        return j - i + 1;
    }


    public static void main(String[] args) {
        Q581_FindUnsortedSubarray obj = new Q581_FindUnsortedSubarray();
        System.out.println(obj.findUnsortedSubarray(
                new int[]{1, 2, 3}
        ));
    }
}

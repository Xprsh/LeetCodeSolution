import java.util.Arrays;

/**
 * Q215. 数组中的第K个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，
 * 而不是第 k 个不同的元素。
 */
public class Q215_FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
